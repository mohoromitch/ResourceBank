package ca.ryerson.scs.cscu.web.ejb.database.Faculty;

import ca.ryerson.scs.cscu.web.entities.Faculty;

import javax.ejb.Remote;
import java.util.List;

/**
 * Created by mitchellmohorovich on 15-07-17.
 */

@Remote
public interface FacultyBean {
    void initializeDefaults();
    void addFaculty(Faculty faculty);
    void addFaculty(String name);
    void removeFaculty(int id);
    List getAllFaculties();
}
