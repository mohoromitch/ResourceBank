package ca.ryerson.scs.cscu.entities;

import ca.ryerson.scs.cscu.enums.Semester;

import javax.persistence.*;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by mitchellmohorovich on 15-08-22.
 * A superclass to Exams and Courses.
 * Made to remove the redundancies shared between the entities,
 * and all future documents that are organized by time.
 */
@MappedSuperclass
public abstract class TimeDocument {
	@Id
	@GeneratedValue
	protected int id;
	@Enumerated(EnumType.ORDINAL) //
	protected Semester semester; //Winter, Spring, Summer, Fall :)
	protected short year;
	protected byte[] file;
	protected String contentType;
	@Temporal(TemporalType.TIMESTAMP)
	protected Date uploadDate;

	TimeDocument() {
		this.uploadDate = new Date();
	}

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

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public Date getUploadDate() {
		return uploadDate;
	}

	public void setUploadDate(Date uploadDate) {
		this.uploadDate = uploadDate;
	}

	public String getFormattedUploadDate() {
		String st = DateFormat.getDateInstance(DateFormat.SHORT, Locale.CANADA).format(this.getUploadDate());
		return st;
	}
}
