package ca.ryerson.scs.cscu.ejb.database.Courses.CourseManagementForms;

import ca.ryerson.scs.cscu.entities.Course;
import ca.ryerson.scs.cscu.entities.CourseManagementForm;
import ca.ryerson.scs.cscu.enums.Semester;

import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by mitchellmohorovich on 15-08-21.
 */
@Startup
@Singleton
public class CourseManagementFormBeanImp implements CourseManagementFormBean {
	@PersistenceContext(unitName = "jdbcPU")
	EntityManager em;

	@Override
	public List<CourseManagementForm> getAllCourseManagementForms() {
		List<CourseManagementForm> cmfs;
		Query query = em.createNamedQuery("getAllCourseManagementForms");
		cmfs = query.getResultList();
		return cmfs;
	}

	@Override
	public CourseManagementForm getCourseManagementFormById(int id) {
		return em.find(CourseManagementForm.class, id);
	}

	@Override
	public void addCourseManagementForm(CourseManagementForm cmf) {
		em.persist(cmf);
	}

	@Override
	public void deleteCourseManagementForm(int id) {
		CourseManagementForm cmf = em.find(CourseManagementForm.class, id);
		cmf.getOwnerCourse().removeCourseManagementForm(cmf);
		cmf.setOwnerCourse(null);
		em.remove(cmf);
	}

	@Override
	public CourseManagementForm setYear(int id, short year) {
		CourseManagementForm cmf = em.find(CourseManagementForm.class, id);
		cmf.setYear(year);
		em.persist(cmf);
		return cmf;
	}

	@Override
	public CourseManagementForm setSemester(int id, Semester semester) {
		CourseManagementForm cmf = em.find(CourseManagementForm.class, id);
		cmf.setSemester(semester);
		em.persist(cmf);
		return cmf;
	}

	@Override
	public CourseManagementForm setProfessor(int id, String professor) {
		CourseManagementForm cmf = em.find(CourseManagementForm.class, id);
		cmf.setProfessor(professor);
		em.persist(cmf);
		return cmf;
	}

}
