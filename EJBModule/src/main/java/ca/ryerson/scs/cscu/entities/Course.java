package ca.ryerson.scs.cscu.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by mitchellmohorovich on 15-07-19.
 * Courses are one of the most important entities in the program.
 * They store references to their Exams and CourseManagementForms.
 * Courses have a ManyToMany relationship with Programs since
 * courses can be relevant to many courses (even if they belong to a certain program).
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
    /** The course code that appears in the course calendar. Ex. CPS109. It should be in the format [A-Z]+[0-9]+ */
    private String courseCode;
    /** The full name of the course that appears in the course calendar. Ex. Introduction to Computer Science. */
    private String name; //ex. Introduction to Computer Science I
    /** The description of the course that appears in the course calendar. */
    private String description; //ie. What's on the Ryerson course calendar
    /** The Exams that are owned by the course. When this Course is removed, all of its exams should be removed as well. */
    @OneToMany(cascade = CascadeType.REMOVE)
    private Collection<Exam> exams;
    /** The CourseManagementForms that are owned by the course. When this Course is removed, all of its CMFs must be removed as well. */
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
