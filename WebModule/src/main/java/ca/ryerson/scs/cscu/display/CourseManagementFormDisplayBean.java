package ca.ryerson.scs.cscu.display;

import ca.ryerson.scs.cscu.ejb.database.Courses.CourseManagementForms.CourseManagementFormBean;
import ca.ryerson.scs.cscu.entities.CourseManagementForm;
import ca.ryerson.scs.cscu.interfaces.DisplayBean;

import javax.ejb.EJB;
import java.util.List;

/**
 * Created by mitchellmohorovich on 15-08-22.
 */
public class CourseManagementFormDisplayBean implements DisplayBean<CourseManagementForm> {
    @EJB
    CourseManagementFormBean courseManagementFormBean;

    @Override
    public List<CourseManagementForm> getAllEntities() {
        return courseManagementFormBean.getAllCourseManagementForms();
    }

    @Override
    public CourseManagementForm findEntityById(int id) {
        return courseManagementFormBean.getCourseManagementFormById(id);
    }

}
