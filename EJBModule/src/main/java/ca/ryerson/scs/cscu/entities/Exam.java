package ca.ryerson.scs.cscu.entities;

import ca.ryerson.scs.cscu.enums.Semester;

import javax.persistence.*;
import java.io.File;
import java.io.Serializable;

/**
 * Created by mitchellmohorovich on 15-07-19.
 * A subclass of TimeDocument, it extends timeDocument by adding an ownerCourse reference and a Type.
 * Type is an enum that defines what type of test it is.
 */

@Entity
@NamedQueries({
        @NamedQuery(
                name = "getAllExams",
                query = "select e from Exam e"
        )
})
public class Exam extends TimeDocument implements Serializable {
    @Enumerated(EnumType.STRING)
    private Type type; //Exam, Test, Practice Test, Practice Exam
    @ManyToOne
    private Course ownerCourse;

    /** A transient variable used to manage whether an exam in a table row is currently editblae or not.
     * This would make the row labels show fields instead.
     * This variable has not been used yet. */
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


    public Exam() {
        editable = false;
    }

    public Exam(short year, Semester semester, Type type) {
        this();
        this.setYear(year);
        this.setSemester(semester);
        this.type = type;
    }

    public Exam(short year, Semester semester, Type type, byte[] file) {
        this(year, semester, type);
        this.setFile(file);
    }

    public Exam(short year, Semester semester, Type type, byte[] file, String contentType) {
        this(year, semester, type, file);
        this.setContentType(contentType);
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

    public boolean isEditable() {
        return this.editable;
    }

    public void setEditable(boolean editable) {
        this.editable = editable;
    }
}
