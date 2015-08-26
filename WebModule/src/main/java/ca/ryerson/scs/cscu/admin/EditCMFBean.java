package ca.ryerson.scs.cscu.admin;

import ca.ryerson.scs.cscu.abstractClasses.TimeDocumentHandler;
import ca.ryerson.scs.cscu.ejb.database.Courses.CourseManagementForms.CourseManagementFormBean;
import ca.ryerson.scs.cscu.entities.CourseManagementForm;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by mitchellmohorovich on 15-08-25.
 */
@Named("editCMFBean")
@RequestScoped
public class EditCMFBean extends TimeDocumentHandler {
    @EJB
    CourseManagementFormBean courseManagementFormBean;

    private int id;
    private CourseManagementForm cmf;
    private short year;

    /**
     * Given that the id is valid and returns a cmf, initializes the cmf.
     * Otherwise redirects.
     */
    public void initialize() throws IOException {
        this.setCmf(courseManagementFormBean.getCourseManagementFormById(this.getId()));
        if(this.cmf == null) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.getExternalContext().responseSendError(404, "That course management form does not exist.");
            context.responseComplete();
        }
    }

    public void editCMF(int id) throws IOException {
        courseManagementFormBean.setSemester(id, this.getCmf().getSemester());
        courseManagementFormBean.setYear(id, this.getCmf().getYear());
        FacesContext.getCurrentInstance().getExternalContext().redirect("/courses/" + this.getCourseManagementFormById(id).getOwnerCourse().getCourseCode());
    }

    public void deleteCourseManagementFormById(int id) throws IOException {
        FacesContext.getCurrentInstance().getExternalContext().redirect("/courses/" + courseManagementFormBean.getCourseManagementFormById(id).getOwnerCourse().getCourseCode());
        courseManagementFormBean.deleteCourseManagementForm(id);
    }

    public CourseManagementForm getCmf() {
        if(this.cmf == null) {
            this.cmf = new CourseManagementForm();
        }
        return cmf;
    }

    public void setCmf(CourseManagementForm courseManagementForm) {
        this.cmf = courseManagementForm;
    }

    public CourseManagementForm getCourseManagementFormById(int id) {
        return courseManagementFormBean.getCourseManagementFormById(id);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void validate(FacesContext context, UIComponent uiComponent, Object object) {
        if((Integer) object <= 0) {
            context.getExternalContext().setResponseStatus(404);
        } else if(this.getCourseManagementFormById(this.getId()) == null) {
            context.getExternalContext().setResponseStatus(404);
        }
    }

}
