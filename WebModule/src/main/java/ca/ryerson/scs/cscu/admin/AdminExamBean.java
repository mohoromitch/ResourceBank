package ca.ryerson.scs.cscu.admin;

import ca.ryerson.scs.cscu.ejb.database.Courses.CourseBean;
import ca.ryerson.scs.cscu.ejb.database.Courses.Exams.ExamBean;
import ca.ryerson.scs.cscu.entities.Course;
import ca.ryerson.scs.cscu.entities.Exam;

import javax.annotation.ManagedBean;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.Part;
import java.io.*;
import java.nio.file.Files;
import java.util.Calendar;

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
    private Exam.Semester semester; //Winter, Spring, Summer, Fall :)
    private Exam.Type type; //Exam, Test, Practice Test, Practice Exam
    private Part uploadedFile;
    private Exam exam;

    private final int MINIMUM_YEAR = 1995;

    public void setYear(short year) {
        this.year = year;
    }

    public void setSemester(Exam.Semester semester) {
        this.semester = semester;
    }

    public void setType(Exam.Type type) {
        this.type = type;
    }

    public short getYear() {
        return year;
    }

    public Exam.Semester getSemester() {
        return semester;
    }

    public Exam.Type getType() {
        return type;
    }

    public Exam newExam(String courseCode) {
        Exam returnExam = new Exam(this.getYear(), this.getSemester(), this.getType());
        returnExam.setOwnerCourse(courseBean.getCourseByCourseCode(courseCode));
        returnExam.setFile(this.toByteArray(this.getUploadedFile()));
        return returnExam;
    }

    public void buildNewExam(String uriCourseCode) throws IOException {
        courseBean.addExamToCourse(uriCourseCode, examBean.addExam(this.newExam(uriCourseCode)));
        FacesContext.getCurrentInstance().getExternalContext().redirect("/courses/" + uriCourseCode);
    }

    private byte[] toByteArray(Part file) {
        InputStream input;
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

    public Exam getExamById(int id) {
        return examBean.getExamById(id);
    }

    public Exam.Type[] getTypes() {
        return Exam.Type.values();
    }

    public Exam.Semester[] getSemesters() {
        return Exam.Semester.values();
    }

    public int getCurrentYear() {
        return Calendar.getInstance().get(Calendar.YEAR);
    }

    public int[] getYearRange() {
        int size = this.getCurrentYear() - this.MINIMUM_YEAR + 1;
        int returnArray[] = new int[size];
        returnArray[0] = this.MINIMUM_YEAR;
        for (int i = 1; i < size; i++) {
            returnArray[i] = returnArray[i-1]+1;
        }
        return returnArray;
    }

    public String setEditable(Exam e) {
        e.setEditable(true);
        return null;
    }

    public String editExam(String uriExamId) throws IOException {
        int intExamId = Integer.parseInt(uriExamId);
        examBean.setExamSemester(intExamId, this.getExam().getSemester());
        examBean.setExamType(intExamId, this.getExam().getType());
        examBean.setExamYear(intExamId, this.getExam().getYear());
        String ownerCourseCode = getExamById(Integer.parseInt(uriExamId)).getOwnerCourse().getCourseCode();
        FacesContext.getCurrentInstance().getExternalContext().redirect("/courses/" + ownerCourseCode);
        return String.valueOf(Integer.parseInt(uriExamId));
    }

    public void setExamType(int examId, Exam.Type type) {
        examBean.setExamType(examId, type);
    }

    public Exam getExam() {
        if(this.exam == null) {
            this.exam = new Exam();
        }
        return exam;
    }

    public void setExam(Exam exam) {
        this.exam = exam;
    }
}
