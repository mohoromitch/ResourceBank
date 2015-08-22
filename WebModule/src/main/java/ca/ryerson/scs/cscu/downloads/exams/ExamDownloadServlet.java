package ca.ryerson.scs.cscu.downloads.exams;

/**
 * Created by mitchellmohorovich on 15-07-29.
 */

import ca.ryerson.scs.cscu.ejb.database.Courses.CourseBean;
import ca.ryerson.scs.cscu.ejb.database.Courses.Exams.ExamBean;
import ca.ryerson.scs.cscu.entities.Exam;

import java.awt.datatransfer.DataFlavor;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
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
        //TODO: Redirect if the passed id is invalid (NaN)
        int examID = Integer.parseInt(request.getParameter("id"));

        Exam exam = examBean.getExamById(examID);
        //TODO: Redirect if the passed id doesn't correspond to a proper row.
        byte[] examFile = exam.getFile();

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


        OutputStream os = response.getOutputStream();

        //TODO: Write the exam buffered.
        os.write(examFile);
        os.flush();
        os.close();
    }
}
