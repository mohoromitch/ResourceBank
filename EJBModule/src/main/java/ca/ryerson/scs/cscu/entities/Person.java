package ca.ryerson.scs.cscu.entities;

import ca.ryerson.scs.cscu.enums.Title;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by mitchellmohorovich on 2015-11-01.
 */

@Entity
public abstract class Person {
	@Id
	@GeneratedValue
	private long id;

	private String firstName;
	private String lastName;
	private Title title;

	public Person() {
		this.firstName = null;
		this.lastName = null;
		this.title = Title.other;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Title getTitle() {
		return title;
	}

	public void setTitle(Title title) {
		this.title = title;
	}
}
