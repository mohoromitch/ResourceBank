package ca.ryerson.scs.cscu.ejb.database.Programs;

import ca.ryerson.scs.cscu.entities.Faculty;
import ca.ryerson.scs.cscu.entities.Program;

import javax.ejb.Local;
import java.util.List;

/**
 * Created by mitchellmohorovich on 15-07-14.
 */

@Local
public interface ProgramBean {
	void initializeDefaults();

	void removeProgram(int id);

	List<Program> getAllPrograms();

	List<Program> getProgramsByFaculty(Faculty f);

	Program getProgramByShortName(String shortName);

	Program getProgramById(int id);

	void addProgram(Program program);

	void addProgram(String name, String shortName);

	void addProgram(String name, String shortName, Faculty faculty);

	void addCourseToProgramByShortName(int courseId, String shortName);

	boolean entityManagerExists();
}
