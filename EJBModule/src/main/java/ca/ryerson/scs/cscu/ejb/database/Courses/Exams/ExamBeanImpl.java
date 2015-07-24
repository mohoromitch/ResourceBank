package ca.ryerson.scs.cscu.ejb.database.Courses.Exams;

import ca.ryerson.scs.cscu.ejb.database.Courses.CourseBean;
import ca.ryerson.scs.cscu.entities.Exam;

import javax.ejb.EJB;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by mitchellmohorovich on 15-07-23.
 */
public class ExamBeanImpl implements ExamBean {
    @PersistenceContext(unitName = "jdbcPU")
    EntityManager em;

    @EJB
    CourseBean courseBean;

    @EJB
    ExamBean examBean;

    @Override
    public void removeExam() {
        //todo: implement this
    }

    @Override
    public List<Exam> getAllExams() {
        List<Exam> exams;
        Query query = em.createNamedQuery("getAllExams");
        exams = query.getResultList();
        return exams;
    }

    @Override
    public Exam getExamById() {
        return null;
    }

    @Override
    public void addExam() {

    }
}
