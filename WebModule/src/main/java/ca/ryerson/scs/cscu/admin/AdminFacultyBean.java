package ca.ryerson.scs.cscu.admin;

import ca.ryerson.scs.cscu.ejb.database.Faculty.FacultyBean;
import ca.ryerson.scs.cscu.entities.Faculty;
import ca.ryerson.scs.cscu.interfaces.AdminBean;
import ca.ryerson.scs.cscu.interfaces.DisplayBean;

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
 * Created by mitchellmohorovich on 15-07-17.
 */

@Named("adminFacultyBean")
@RequestScoped
public class AdminFacultyBean implements AdminBean<Faculty>, DisplayBean<Faculty> {
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

    @Override
    public void persistEntity() throws IOException {
        facultyBean.addFaculty(this.getName());
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        ec.redirect( ((HttpServletRequest) ec.getRequest()).getRequestURI());
    }

    @Override
    public void removeEntityById(int id) {
        //todo: implement this
    }

    @Override
    public List<Faculty> getAllEntities() {
        return facultyBean.getAllFaculties();
    }

    @Override
    public Faculty findEntityById(int id) {
        return facultyBean.findFacultyById(id);
    }
}
