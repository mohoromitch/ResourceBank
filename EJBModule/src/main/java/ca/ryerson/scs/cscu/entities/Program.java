package ca.ryerson.scs.cscu.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mitchellmohorovich on 15-07-14.
 * An entity that represents a program.
 * Programs have a ManyToOne relationship with faculties.
 * Programs use their shortName to find them in URLs.
 * ex. /programs/CS
 */

@Entity
@NamedQueries({
        @NamedQuery(
                name = "getAllPrograms",
                query = "select p from Program p"
        ),
        @NamedQuery(
                name = "getProgramByShortName",
                query = "select p from Program p where p.shortName = :shortName"
        )
})
public class Program implements Serializable {
    @Id
    @GeneratedValue
    private int id;
    private String name;
    private String shortName;
    @ManyToOne
    private Faculty faculty;
    @ManyToMany
    private List<Course> courses;

    public Program() {
        courses = new ArrayList<>();
    }

    public Program(String name, String shortName) {
        this();
        this.name = name;
        this.shortName = shortName;
    }

    public Program(String name, String shortName, Faculty faculty) {
        this();
        this.name = name;
        this.shortName = shortName;
        this.faculty = faculty;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getShortName() {
        return shortName;
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void addCourse(Course course) {
        this.getCourses().add(course);
    }

}
