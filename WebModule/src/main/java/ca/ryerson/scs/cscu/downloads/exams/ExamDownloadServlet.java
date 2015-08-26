package ca.ryerson.scs.cscu.downloads.exams;

/**
 * Created by mitchellmohorovich on 15-07-29.
 */

import ca.ryerson.scs.cscu.ejb.database.Courses.CourseBean;
import ca.ryerson.scs.cscu.ejb.database.Courses.Exams.ExamBean;
import ca.ryerson.scs.cscu.entities.Exam;

import java.awt.datatransfer.DataFlavor;
import java.io.*;
import javax.ejb.EJB;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ExamDownloadServlet extends HttpServlet {

    private static final int BYTES_DOWNLOAD = 1024;

    @EJB
    ExamBean examBean;

    @EJB
    CourseBean courseBean;

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws IOException {
        int examID;
        try {
            examID = Integer.parseInt(request.getParameter("id"));
        } catch(NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_NOT_ACCEPTABLE, "The id was invalid or not provided");
            return;
        }


        Exam exam = examBean.getExamById(examID);
        if(exam == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "The exam with the ID " + examID + " does not exist.");
            return;
        }
        byte[] examFile = exam.getFile();
        if(examFile == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "The exam file does not exist.");
            return;
        }

        //CourseCode_Year_Semester_Type
        //Ex.CPS590_2015_Fall_PracticeTest
        String filename = String.format("%s_%d_%s_%s",
                courseBean.getCourseById(exam.getOwnerCourse().getId()).getCourseCode(),
                exam.getYear(),
                exam.getSemester(),
                exam.getType()
        );

        response.setContentType(exam.getContentType());
        response.setHeader("Content-Disposition", "inline;filename=" + filename + ".pdf");

        BufferedOutputStream bos = null;
        BufferedInputStream bis = null;
        try {
            bos = new BufferedOutputStream(response.getOutputStream());
            bis = new BufferedInputStream(new ByteArrayInputStream(examFile));
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

