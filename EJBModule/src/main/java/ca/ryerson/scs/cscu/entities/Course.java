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
    @OneToMany
    private Collection<Exam> exams;

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

    public void addExam(Exam e) {
        this.getExams().add(e);
    }

    public int getId() {
        return id;
    }

    public Collection<Exam> getExams() {
        return exams;
    }
}
