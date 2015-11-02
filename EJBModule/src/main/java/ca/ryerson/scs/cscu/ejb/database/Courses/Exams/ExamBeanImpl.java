package ca.ryerson.scs.cscu.ejb.database.Courses.Exams;

import ca.ryerson.scs.cscu.entities.Course;
import ca.ryerson.scs.cscu.entities.Exam;
import ca.ryerson.scs.cscu.enums.Semester;

import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by mitchellmohorovich on 15-07-23.
 */
@Singleton
@Startup
public class ExamBeanImpl implements ExamBean {
	@PersistenceContext(unitName = "jdbcPU")
	EntityManager em;

	@Override
	public List<Exam> getAllExams() {
		List<Exam> exams;
		Query query = em.createNamedQuery("getAllExams");
		exams = query.getResultList();
		return exams;
	}

	@Override
	public Exam getExamById(int id) {
		return em.find(Exam.class, id);
	}

	@Override
	public Exam addExam(Exam e) {
		em.persist(e);
		return e;
	}

	@Override
	public void persistExam(int id) {
		em.persist(this.getExamById(id));
	}

	@Override
	public Exam setExamType(int id, Exam.Type type) {
		Exam exam = em.find(Exam.class, id);
		exam.setType(type);
		em.persist(exam);
		return exam;
	}

	@Override
	public Exam setExamYear(int id, short year) {
		Exam exam = em.find(Exam.class, id);
		exam.setYear(year);
		em.persist(exam);
		return exam;
	}

	@Override
	public Exam setExamSemester(int id, Semester semester) {
		Exam exam = em.find(Exam.class, id);
		exam.setSemester(semester);
		em.persist(exam);
		return exam;
	}

	@Override
	public Exam setExamOwnerCourse(int id, Course course) {
		Exam exam = em.find(Exam.class, id);
		exam.setOwnerCourse(course);
		em.persist(exam);
		return exam;
	}

	@Override
	public void deleteExam(int id) {
		Exam exam = this.em.find(Exam.class, id);
		exam.getOwnerCourse().removeExam(exam);
		exam.setOwnerCourse(null);
		em.remove(exam);
	}
}
