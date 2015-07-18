package ca.ryerson.scs.cscu.web.ejb.database.Programs;

import ca.ryerson.scs.cscu.web.entities.Program;

import javax.ejb.Local;
import javax.ejb.Remote;
import java.util.List;

/**
 * Created by mitchellmohorovich on 15-07-14.
 */

@Local
public interface ProgramBean {
    void removeProgram(int id);
    List<Program> getPrograms();
    void addProgram(Program program);
    void addProgram(String name, String shortname);
    boolean entityManagerExists();
}
