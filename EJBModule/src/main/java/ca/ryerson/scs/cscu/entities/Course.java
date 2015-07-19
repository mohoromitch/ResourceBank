package ca.ryerson.scs.cscu.entities;

import javax.persistence.*;
import java.io.Serializable;

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

    public Course() {
    }

    public Course(String courseCode, String name) {
        this.courseCode = courseCode;
        this.name = name;
    }


    public String getCourseCode() {
        return courseCode;
    }

    public String getName() {
        return name;
    }
}
