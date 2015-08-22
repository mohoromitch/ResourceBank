package ca.ryerson.scs.cscu.downloads.cmfs;

import ca.ryerson.scs.cscu.ejb.database.Courses.CourseBean;
import ca.ryerson.scs.cscu.ejb.database.Courses.CourseManagementForms.CourseManagementFormBean;
import ca.ryerson.scs.cscu.entities.CourseManagementForm;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

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
        int cmfID = Integer.parseInt(request.getParameter("id"));

        CourseManagementForm cmf = courseManagementFormBean.getCourseManagementFormById(cmfID);

        String filename = String.format("CMF_%s_%d_%s",
                courseBean.getCourseById(cmf.getOwnerCourse().getId()).getCourseCode(),
                cmf.getYear(),
                cmf.getSemester());

        response.setContentType(cmf.getContentType());
        response.setHeader("Content-Disposition", "inline;filename=" + filename + ".pdf");

        OutputStream os = response.getOutputStream();

        byte[] cmfFile = cmf.getFile();
        os.write(cmfFile);
        os.flush();
        os.close();
    }
}
