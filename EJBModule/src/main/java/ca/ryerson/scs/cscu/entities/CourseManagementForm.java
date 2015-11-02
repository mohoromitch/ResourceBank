package ca.ryerson.scs.cscu.entities;

import ca.ryerson.scs.cscu.enums.Semester;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by mitchellmohorovich on 15-08-21.
 * A subclass of TimeDocument.
 * This class extends TimeDocument by adding a Course reference to its parent course.
 * This class also adds a Professor field, to display the professor's name in the field.
 * This professor String has not been used yet, and may be replaced with an entity.
 */
@Entity
@NamedQueries(
	@NamedQuery(
		name = "getAllCourseManagementForms",
		query = "select cmf from CourseManagementForm cmf"
	)
)
public class CourseManagementForm extends TimeDocument implements Serializable {
	@ManyToOne
	private Course ownerCourse;
	private String professor;

	public CourseManagementForm() {
		super();
	}

	public CourseManagementForm(short year, Semester semester, String professor, Course ownerCourse) {
		this();
		this.setYear(year);
		this.setSemester(semester);
		this.setProfessor(professor);
		this.ownerCourse = ownerCourse;
	}

	public String getProfessor() {
		return professor;
	}

	public void setProfessor(String professor) {
		this.professor = professor;
	}

	public Course getOwnerCourse() {
		return ownerCourse;
	}

	public void setOwnerCourse(Course ownerCourse) {
		this.ownerCourse = ownerCourse;
	}
}
