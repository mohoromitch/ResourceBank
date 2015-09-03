package ca.ryerson.scs.cscu.ejb.database.Courses;

import ca.ryerson.scs.cscu.entities.Course;
import ca.ryerson.scs.cscu.entities.CourseManagementForm;
import ca.ryerson.scs.cscu.entities.Exam;
import ca.ryerson.scs.cscu.entities.Program;

import java.util.List;

/**
 * Created by mitchellmohorovich on 15-07-19.
 * An EJB used to interact with Courses in the Database.
 */
public interface CourseBean {
    void initializeDefaults();
    void removeCourse(int id);
    List<Course> getAllCourses();
    Course getCourseById(int id);
    Course getCourseByCourseCode(String courseCode);
    void addCourse(Course c);
    boolean entityManagerExists();
    void addExamToCourse(int id, Exam exam);
    void addExamToCourse(String courseCode, Exam exam);
    void addCourseManagementFormToCourse(CourseManagementForm cmf, int id);
}
