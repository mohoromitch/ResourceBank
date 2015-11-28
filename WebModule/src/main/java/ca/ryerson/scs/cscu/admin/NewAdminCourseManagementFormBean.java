package ca.ryerson.scs.cscu.admin;

import ca.ryerson.scs.cscu.abstractClasses.TimeDocumentHandler;
import ca.ryerson.scs.cscu.ejb.database.Courses.CourseBean;
import ca.ryerson.scs.cscu.ejb.database.Courses.CourseManagementForms.CourseManagementFormBean;
import ca.ryerson.scs.cscu.entities.CourseManagementForm;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.IOException;
import java.util.Calendar;

/**
 * Created by mitchellmohorovich on 15-08-22.
 * Used to add a create new CMF's
 */

@Named("newAdminCMFBean")
@RequestScoped
public class NewAdminCourseManagementFormBean extends TimeDocumentHandler {
    @EJB
    CourseBean courseBean;

    @EJB
    CourseManagementFormBean courseManagementFormBean;

    /**
     * The courseCode parameter that is sent as a GET parameter.
     * It is populated in the cmfs/add.xhtml viewParam tag.
     */
    private String courseCode;
    private String professor;

    public void buildNewCMF() throws IOException {
        CourseManagementForm cmf = new CourseManagementForm(
                this.getYear(),
                this.getSemester(),
                this.getProfessor(),
                this.courseBean.getCourseByCourseCode(this.getCourseCode()));
        cmf.setFile(this.toByteArray(this.getUploadedFile()));
        cmf.setContentType(this.getUploadedFile().getContentType());
        courseManagementFormBean.addCourseManagementForm(cmf);
        courseBean.addCourseManagementFormToCourse(cmf, courseBean.getCourseByCourseCode(this.getCourseCode()).getId());
        FacesContext.getCurrentInstance().getExternalContext().redirect("/courses/" + courseCode);
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public int getCurrentYear() { return Calendar.getInstance().get(Calendar.YEAR);
    }

    public String getProfessor() {
        return professor;
    }

    public void setProfessor(String professor) {
        this.professor = professor;
    }
}
