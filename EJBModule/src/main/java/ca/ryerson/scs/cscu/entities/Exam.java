package ca.ryerson.scs.cscu.entities;

import javax.persistence.*;
import java.io.File;
import java.io.Serializable;

/**
 * Created by mitchellmohorovich on 15-07-19.
 */

@Entity
@NamedQueries({
        @NamedQuery(
                name = "getAllExams",
                query = "select e from Exam e"
        )
})
public class Exam implements Serializable {
    @Id
    @GeneratedValue
    private int id;
    private short year;
    private String semester; //Winter, Spring, Summer, Fall :)
    private String type; //Exam, Test, Practice Test, Practice Exam
    private byte[] file;
    @ManyToOne
    private Course ownerCourse;

    public Exam() {
    }

    public Exam(short year, String semester, String type) {
        this();
        this.year = year;
        this.semester = semester;
        this.type = type;
    }

    public Exam(short year, String semester, String type, byte[] file) {
        this(year, semester, type);
        this.file = file;
    }

    public short getYear() {
        return year;
    }

    public void setYear(short year) {
        this.year = year;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Course getOwnerCourse() {
        return ownerCourse;
    }

    public void setOwnerCourse(Course ownerCourse) {
        this.ownerCourse = ownerCourse;
    }

    public byte[] getFile() {
        return file;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }
}
