package ca.ryerson.scs.cscu.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by mitchellmohorovich on 15-07-19.
 */

@Entity
@NamedQueries({
        @NamedQuery(
                name = "getAllCourses",
                query = "select c from Course c"
        ),
        @NamedQuery(
                name = "getCourseByCourseCode",
                query = "select c from Course c where c.courseCode = :courseCode"
        )
})
public class Course implements Serializable {
    @Id
    @GeneratedValue
    private int id;
    private String courseCode; //ex. CPS109
    private String name; //ex. Introduction to Computer Science I
    private String description; //ie. What's on the Ryerson course calendar
    @OneToMany(cascade = CascadeType.REMOVE)
    private Collection<Exam> exams;
    @OneToMany(cascade = CascadeType.REMOVE)
    private Collection<CourseManagementForm> courseManagementForms;

    public Course() {
        this.exams = new ArrayList<>();
    }

    public Course(String courseCode, String name) {
        this();
        this.courseCode = courseCode;
        this.name = name;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public Collection<Exam> getExams() {
        return this.exams;
    }

    public void addExam(Exam e) {
        this.getExams().add(e);
    }

    public void removeExam(Exam exam) {
        this.exams.remove(exam);
    }

    public Collection<CourseManagementForm> getCourseManagementForms() {
        return this.courseManagementForms;
    }

    public void addCourseManagementForm(CourseManagementForm cmf) {
        courseManagementForms.add(cmf);
    }

    public  void removeCourseManagementForm(CourseManagementForm cmf) {
        courseManagementForms.remove(cmf);
    }
}
