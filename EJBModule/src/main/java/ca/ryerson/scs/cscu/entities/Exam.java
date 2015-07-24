package ca.ryerson.scs.cscu.entities;

import javax.persistence.*;
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
    private String filename; //prepended with the file location

    public Exam() {
    }

    public Exam(short year, String semester, String type, String filename) {
        this.year = year;
        this.semester = semester;
        this.type = type;
        this.filename = filename;
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

    public String getFilename() {
        return filename;
    }
}
