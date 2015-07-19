package ca.ryerson.scs.cscu.admin;

import ca.ryerson.scs.cscu.ejb.database.Faculty.FacultyBean;
import ca.ryerson.scs.cscu.entities.Faculty;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;

/**
 * Created by mitchellmohorovich on 15-07-17.
 */

@Named("adminFacultyBean")
@RequestScoped
public class AdminFacultyBean {
    @EJB
    FacultyBean facultyBean;
    private String name;
    private List<Faculty> facultyList;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void addFaculty() throws IOException {
        facultyBean.addFaculty(this.getName());
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        ec.redirect( ((HttpServletRequest) ec.getRequest()).getRequestURI());
    }

    void removeFaculty(String name) {
        //facultyBean.removeFaculty();
        //TODO: add method to remove elements by name, rather than id
        //or find out how to get the id's into the table;
    }

    public List<Faculty> getFacultyList() {
        if(this.facultyList == null) {
            this.facultyList = facultyBean.getAllFaculties();
        }
        return facultyList;
    }
}
