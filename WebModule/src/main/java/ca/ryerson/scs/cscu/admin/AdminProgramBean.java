package ca.ryerson.scs.cscu.admin;

import ca.ryerson.scs.cscu.ejb.database.Faculty.FacultyBean;
import ca.ryerson.scs.cscu.ejb.database.Programs.ProgramBean;
import ca.ryerson.scs.cscu.entities.Course;
import ca.ryerson.scs.cscu.entities.Faculty;
import ca.ryerson.scs.cscu.entities.Program;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;

/**
 * Created by mitchellmohorovich on 15-07-16.
 */

@Named("adminProgramBean")
@RequestScoped
public class AdminProgramBean implements Serializable {
    @EJB
    ProgramBean programBean;

    @EJB
    FacultyBean facultyBean;

    String name;
    String shortName;
    int facultyId;
    List<Program> allPrograms;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getshortName() {
        return shortName;
    }

    public void setshortName(String shortName) {
        this.shortName = shortName;
    }

    public void setFacultyId(int facultyId) {
        this.facultyId = facultyId;
    }

    public int getFacultyId() {
        return facultyId;
    }

    public void addProgram() throws IOException {
        Faculty faculty = facultyBean.findFacultyById(this.getFacultyId());
        programBean.addProgram(this.getName(), this.getshortName(), faculty);
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        ec.redirect(((HttpServletRequest) ec.getRequest()).getRequestURI());
    }

    public List<Program> getAllPrograms() {
        if(this.allPrograms == null)
            this.allPrograms = programBean.getAllPrograms();
        return programBean.getAllPrograms();
    }

    public Program getProgramByShortName(String shortName) {
        return programBean.getProgramByShortName(shortName);
    }
}