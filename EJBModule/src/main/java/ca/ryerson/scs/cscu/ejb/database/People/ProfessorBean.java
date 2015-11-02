package ca.ryerson.scs.cscu.ejb.database.People;

import ca.ryerson.scs.cscu.entities.Professor;

import javax.ejb.Local;
import java.util.List;

/**
 * Created by mitchellmohorovich on 2015-11-01.
 * Used for the web application to interact with database Professor entities
 */
@Local
public interface ProfessorBean {
	void initializeDefaults();

	void removeProfessor(long id);

	List getAllProfessors();

	Professor getProfessorById(long id);

	void addProfessor(Professor professor);
}
