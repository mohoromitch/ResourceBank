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
    void addProgram(Program program);
    void addProgram(String name, String shortname);
    void addProgram(String name, String shortname, Faculty faculty);
    boolean entityManagerExists();
}
