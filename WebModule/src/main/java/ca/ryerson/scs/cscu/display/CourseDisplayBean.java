package ca.ryerson.scs.cscu.display;

import ca.ryerson.scs.cscu.ejb.database.Courses.CourseBean;
import ca.ryerson.scs.cscu.entities.Course;
import ca.ryerson.scs.cscu.interfaces.DisplayBean;

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
 * This class is used for the public facing part of the site, so roles don't get mixed up.
 */
@Named("courseDisplayBean")
@RequestScoped
public class CourseDisplayBean implements DisplayBean<Course> {
    @EJB
    CourseBean courseBean;

    @Override
    public List<Course> getAllEntities() {
        return courseBean.getAllCourses();
    }

    @Override
    public Course findEntityById(int id) {
        return courseBean.getCourseById(id);
    }

    public Course findCourseByCourseCode(String courseCode) {
        return courseBean.getCourseByCourseCode(courseCode);
    }
}
