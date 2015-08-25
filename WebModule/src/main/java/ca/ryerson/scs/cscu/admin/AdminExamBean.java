package ca.ryerson.scs.cscu.admin;

import ca.ryerson.scs.cscu.abstractClasses.TimeDocumentHandler;
import ca.ryerson.scs.cscu.ejb.database.Courses.CourseBean;
import ca.ryerson.scs.cscu.ejb.database.Courses.Exams.ExamBean;
import ca.ryerson.scs.cscu.entities.Exam;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.*;

/**
 * Created by mitchellmohorovich on 15-07-24.
 */
@Named("adminExamBean")
@RequestScoped
public class AdminExamBean extends TimeDocumentHandler {
    @EJB
    ExamBean examBean;

    @EJB
    CourseBean courseBean;

    private Exam.Type type; //Exam, Test, Practice Test, Practice Exam
    private Exam exam;
    @ManagedProperty("#{param.courseCode}")
    private String courseCode;

    private final int MINIMUM_YEAR = 1995;

    public void setType(Exam.Type type) {
        this.type = type;
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

    public Exam getExamById(int id) {
        return examBean.getExamById(id);
    }

    public Exam.Type[] getTypes() {
        return Exam.Type.values();
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

    public void deleteExamById(int id) throws IOException {
        FacesContext.getCurrentInstance().getExternalContext().redirect("/courses/" + examBean.getExamById(id).getOwnerCourse().getCourseCode());
        examBean.deleteExam(id);
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }
}
