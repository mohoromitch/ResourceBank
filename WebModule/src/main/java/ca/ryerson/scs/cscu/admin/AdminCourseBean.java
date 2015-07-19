package ca.ryerson.scs.cscu.admin;

import ca.ryerson.scs.cscu.ejb.database.Courses.CourseBean;
import ca.ryerson.scs.cscu.entities.Course;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

/**
 * Created by mitchellmohorovich on 15-07-19.
 */
@Named("adminCourseBean")
@RequestScoped
public class AdminCourseBean {
    @EJB
    CourseBean courseBean;
    private String courseCode;
    private String name;

    private List<Course> masterList;

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addCourse() throws IOException {
        courseBean.addCourse(new Course(this.getCourseCode(), this.getName()));
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        ec.redirect(((HttpServletRequest) ec.getRequest()).getRequestURI());
    }

    public List<Course> getMasterList() {
        if(this.masterList == null) {
            this.setMasterList(courseBean.getAllCourses());
        }
        return this.masterList;
    }

    private void setMasterList(List<Course> list) {
        this.masterList = list;
    }

    public Course getCourseByCourseCode(String courseCode) {
        return courseBean.getCourseByCourseCode(courseCode);
    }
}
