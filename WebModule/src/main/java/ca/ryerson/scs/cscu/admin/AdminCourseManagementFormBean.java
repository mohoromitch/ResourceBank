package ca.ryerson.scs.cscu.admin;

import ca.ryerson.scs.cscu.ejb.database.Courses.CourseManagementForms.CourseManagementFormBean;
import ca.ryerson.scs.cscu.entities.CourseManagementForm;

import javax.ejb.EJB;

/**
 * Created by mitchellmohorovich on 15-08-21.
 */
public class AdminCourseManagementFormBean {
    @EJB
    CourseManagementFormBean courseManagementFormBean;
    private final int MINIMUM_YEAR = 1995;
    private CourseManagementForm cmf;
}
