package ca.ryerson.scs.cscu.admin;

import ca.ryerson.scs.cscu.ejb.database.Courses.CourseBean;
import ca.ryerson.scs.cscu.ejb.database.Courses.Exams.ExamBean;
import ca.ryerson.scs.cscu.entities.Course;
import ca.ryerson.scs.cscu.entities.Exam;

import javax.annotation.ManagedBean;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.Part;
import java.io.*;
import java.nio.file.Files;

/**
 * Created by mitchellmohorovich on 15-07-24.
 */
@Named("adminExamBean")
@RequestScoped
public class AdminExamBean {
    @EJB
    ExamBean examBean;

    @EJB
    CourseBean courseBean;

    private short year;
    private String semester; //Winter, Spring, Summer, Fall :)
    private String type; //Exam, Test, Practice Test, Practice Exam
    private Part uploadedFile;

    public void setYear(short year) {
        this.year = year;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public void setType(String type) {
        this.type = type;
    }

    public short getYear() {
        return year;
    }

    public String getSemester() {
        return semester;
    }

    public String getType() {
        return type;
    }

    public Exam newExam(String courseCode) {
        Exam returnExam = new Exam(this.getYear(), this.getSemester(), this.getType());
        returnExam.setOwnerCourse(courseBean.getCourseByCourseCode(courseCode));
        returnExam.setFile(this.toByteArray(this.getUploadedFile()));
        return returnExam;
    }

    public void buildNewExam(String uriCourseCode) {
        courseBean.addExamToCourse(uriCourseCode, examBean.addExam(this.newExam(uriCourseCode)));
    }

    private byte[] toByteArray(Part file) {
        InputStream input = null;
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        try {
            input = file.getInputStream();
            byte[] buffer = new byte[1024];
            for (int length = 0; (length = input.read(buffer)) > 0; output.write(buffer, 0, length)) ;
        } catch (IOException e) {
            return null;
        }
        return output.toByteArray();
    }

    public void setUploadedFile(Part uploadedFile) {
        this.uploadedFile = uploadedFile;
    }

    public Part getUploadedFile() {
        return uploadedFile;
    }
}
