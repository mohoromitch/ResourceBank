package ca.ryerson.scs.cscu.display;

import ca.ryerson.scs.cscu.ejb.database.Courses.CourseBean;
import ca.ryerson.scs.cscu.ejb.database.Programs.ProgramBean;
import ca.ryerson.scs.cscu.entities.Course;
import ca.ryerson.scs.cscu.entities.Program;
import ca.ryerson.scs.cscu.interfaces.DisplayBean;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedProperty;
import javax.inject.Named;
import java.util.ArrayList;
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

    @EJB
    ProgramBean programBean;

    String refCourse;

    Course referralCourse;

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

    public String getRefCourse() {
        return refCourse;
    }

    public void setRefCourse(String refCourse) {
        this.refCourse = refCourse;
    }

    public void setReferralCourse(Course referralCourse) {
        this.referralCourse = referralCourse;
    }

    public Course getReferralCourse() {
        return referralCourse;
    }

    public List<Breadcrumb> generateBreadcrumbs(String uriCourseCode) {
        List<Breadcrumb> breadcrumbs = new ArrayList<>();
        Program refProgram = this.programBean.getProgramByShortName(this.refCourse);
        if(refProgram == null) {
            breadcrumbs.add(new Breadcrumb("Courses", "/courses/"));
        } else {
            breadcrumbs.add(new Breadcrumb("Programs", "/programs/"));
            breadcrumbs.add(new Breadcrumb(refProgram.getName(), "/programs/" + refProgram.getShortName()));
        }
        breadcrumbs.add(new Breadcrumb(uriCourseCode, null, true));

        return breadcrumbs;
    }
}
