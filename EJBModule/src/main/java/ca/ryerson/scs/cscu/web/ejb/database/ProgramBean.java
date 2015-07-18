package ca.ryerson.scs.cscu.web.ejb.database;

import ca.ryerson.scs.cscu.web.entities.Program;

import javax.ejb.Remote;
import java.util.List;

/**
 * Created by mitchellmohorovich on 15-07-14.
 */

@Remote
public interface ProgramBean {
    void removeProgram(int id);
    List<Program> getPrograms();
    void addProgram(Program program);
    void addProgram(String name, String shortname);
    boolean entityManagerExists();
}
