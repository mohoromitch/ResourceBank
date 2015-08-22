package ca.ryerson.scs.cscu.entities;

import ca.ryerson.scs.cscu.enums.Semester;

import javax.persistence.*;

/**
 * Created by mitchellmohorovich on 15-08-22.
 */
@MappedSuperclass
public class TimeDocument {
    @Id
    @GeneratedValue
    protected int id;
    @Enumerated(EnumType.ORDINAL) //
    protected Semester semester; //Winter, Spring, Summer, Fall :)
    protected short year;
    protected byte[] file;

    public Semester getSemester() {
        return semester;
    }

    public void setSemester(Semester semester) {
        this.semester = semester;
    }

    public short getYear() {
        return year;
    }

    public void setYear(short year) {
        this.year = year;
    }

    public byte[] getFile() {
        return file;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
