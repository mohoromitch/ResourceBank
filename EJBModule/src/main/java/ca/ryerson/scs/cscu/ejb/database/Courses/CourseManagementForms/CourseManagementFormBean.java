package ca.ryerson.scs.cscu.ejb.database.Courses.CourseManagementForms;

import ca.ryerson.scs.cscu.entities.CourseManagementForm;
import ca.ryerson.scs.cscu.entities.Exam;
import ca.ryerson.scs.cscu.enums.Semester;

import java.util.List;

/**
 * Created by mitchellmohorovich on 15-08-21.
 */
public interface CourseManagementFormBean {
    CourseManagementForm getCourseManagementFormById(int id);
    void addCourseManagementForm(CourseManagementForm cmf);
    void deleteCourseManagementForm(int id);
    CourseManagementForm setYear(int id, short year);
    CourseManagementForm setSemester(int id, Semester semester);
    CourseManagementForm setProfessor(int id, String professor);
    List<CourseManagementForm> getAllCourseManagementForms();
}
