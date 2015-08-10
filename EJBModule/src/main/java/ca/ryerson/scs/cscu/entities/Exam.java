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
    @Enumerated(EnumType.ORDINAL) //
    private Semester semester; //Winter, Spring, Summer, Fall :)
    @Enumerated(EnumType.STRING)
    private Type type; //Exam, Test, Practice Test, Practice Exam
    private byte[] file;
    @ManyToOne
    private Course ownerCourse;

    @Transient
    private boolean editable; //Used for editing the entities in the table rows.

    public enum Type {
        exam("Exam"),
        test("Test"),
        midterm("Midterm"),
        praticeTest("Practice Test"),
        practiceProblems("Practice Problems"),
        other("Other");

        private String label;
        Type(String label) {
            this.label = label;
        }
        public String getLabel() {
            return this.label;
        }
    }

    public enum Semester {
        fall("Fall"),
        winter("Winter"),
        spring("Spring"),
        summer("Summer"),;

        private final String label;

        Semester(String label) {
            this.label = label;
        }

        public String getLabel() {
            return label;
        }
    }

    public Exam() {
        editable = false;
    }

    public Exam(short year, Semester semester, Type type) {
        this();
        this.year = year;
        this.semester = semester;
        this.type = type;
    }

    public Exam(short year, Semester semester, Type type, byte[] file) {
        this(year, semester, type);
        this.file = file;
    }

    public short getYear() {
        return year;
    }

    public void setYear(short year) {
        this.year = year;
    }

    public Semester getSemester() {
        return semester;
    }

    public void setSemester(Semester semester) {
        this.semester = semester;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
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

    public int getId() {
        return this.id;
    }

    public boolean isEditable() {
        return this.editable;
    }

    public void setEditable(boolean editable) {
        this.editable = editable;
    }
}
