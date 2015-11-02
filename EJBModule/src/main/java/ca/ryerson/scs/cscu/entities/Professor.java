package ca.ryerson.scs.cscu.entities;

import ca.ryerson.scs.cscu.enums.Title;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by mitchellmohorovich on 2015-11-01.
 */
@Entity
@NamedQueries({
	@NamedQuery(
		name = "getAllProfessors",
		query = "select pr from Professor pr"
	),
	@NamedQuery(
		name = "getProfessorByLastName",
		query = "select pr from Professor pr where pr.lastName = :lastName"
	)
})
public class Professor extends Person implements Serializable {
	@Id
	@GeneratedValue
	private int id;
	@OneToOne
	private Faculty faculty;

	public Professor() {
		super();
	}

	public Faculty getFaculty() {
		return faculty;
	}

	public void setFaculty(Faculty faculty) {
		this.faculty = faculty;
	}
}
