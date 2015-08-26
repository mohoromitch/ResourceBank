package ca.ryerson.scs.cscu.downloads.cmfs;

import ca.ryerson.scs.cscu.ejb.database.Courses.CourseBean;
import ca.ryerson.scs.cscu.ejb.database.Courses.CourseManagementForms.CourseManagementFormBean;
import ca.ryerson.scs.cscu.entities.CourseManagementForm;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * Created by mitchellmohorovich on 15-08-22.
 */
public class CourseManagementFormDownloadServlet extends HttpServlet {

    @EJB
    CourseManagementFormBean courseManagementFormBean;

    @EJB
    CourseBean courseBean;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int cmfID;
        try {
            cmfID = Integer.parseInt(request.getParameter("id"));
        } catch(NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_NOT_ACCEPTABLE, "The id was invalid or not provided");
            return;
        }

        CourseManagementForm cmf = courseManagementFormBean.getCourseManagementFormById(cmfID);
        if(cmf == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "The course management form with the ID " + cmfID + " does not exist");
            return;
        }


        String filename = String.format("CMF_%s_%d_%s",
                courseBean.getCourseById(cmf.getOwnerCourse().getId()).getCourseCode(),
                cmf.getYear(),
                cmf.getSemester());

        byte[] cmfFile = cmf.getFile();
        if(cmfFile == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "The file does not exist.");
            return;
        }

        //content starts
        response.setContentType(cmf.getContentType());
        response.setHeader("Content-Disposition", "inline;filename=" + filename + ".pdf");

        BufferedOutputStream bos = null;
        BufferedInputStream bis = null;
        try {
            bos = new BufferedOutputStream(response.getOutputStream());
            bis = new BufferedInputStream(new ByteArrayInputStream(cmfFile));
            byte[] buffer = new byte[8192];
            for(int i; (i = bis.read(buffer)) > 0;) {
                bos.write(buffer, 0, i);
            }
            bos.flush();
        } finally {
            if(bos != null) {
                try {
                    bos.close();
                } catch (IOException e) {} //nothing we can do now
            }
            if(bis != null) {
                bis.close();
                try {
                    bis.close();
                } catch (IOException e) {} //nothing we can do now
            }
        }
    }
}
