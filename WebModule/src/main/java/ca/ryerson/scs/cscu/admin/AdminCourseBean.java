package ca.ryerson.scs.cscu.admin;

import ca.ryerson.scs.cscu.ejb.database.Courses.CourseBean;
import ca.ryerson.scs.cscu.entities.Course;
import ca.ryerson.scs.cscu.entities.Exam;
import ca.ryerson.scs.cscu.interfaces.AdminBean;
import ca.ryerson.scs.cscu.interfaces.DisplayBean;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Created by mitchellmohorovich on 15-07-19.
 */
@Named("adminCourseBean")
@RequestScoped
public class AdminCourseBean implements AdminBean<Course>, DisplayBean<Course> {
    @EJB
    CourseBean courseBean;
    private String courseCode;
    private String name;

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

    public Course getCourseByCourseCode(String courseCode) {
        return courseBean.getCourseByCourseCode(courseCode);
    }

    @Override
    public void persistEntity() throws IOException {
        courseBean.addCourse(new Course(this.getCourseCode(), this.getName()));
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        ec.redirect(((HttpServletRequest) ec.getRequest()).getRequestURI());
    }

    @Override
    public void removeEntityById(int id) {
        //todo: implement this
    }

    @Override
    public List<Course> getAllEntities() {
        return courseBean.getAllCourses();
    }

    public Map<String, Course> getAllEntitiesAsMap() {
        List<Course> allEntities = this.getAllEntities();
        Map<String, Course> allCourseMap = allEntities.stream().collect(Collectors.toMap(Course::getCourseCode, Function.identity()));
        return allCourseMap;
    }

    @Override
    public Course findEntityById(int id) {
        return courseBean.getCourseById(id);
    }

    public void addExamToCourseByCourseCode(String courseCode, Exam exam) {
        this.getCourseByCourseCode(courseCode).addExam(exam);
    }

    public void addExamToCourseById(int id, Exam exam) {
        this.findEntityById(id).addExam(exam);
    }

    public Map<String, Course> getAllEntitiesDifference(List<Course> courseList) {
        Map<String, Course> allCourseMap = this.getAllEntitiesAsMap();
        for(Course c: courseList) {
            allCourseMap.remove(c.getCourseCode());
        }
        return allCourseMap;
    }

}
