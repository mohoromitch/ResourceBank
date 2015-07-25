package ca.ryerson.scs.cscu.ejb.database.Courses.Exams;

import ca.ryerson.scs.cscu.ejb.database.Courses.CourseBean;
import ca.ryerson.scs.cscu.entities.Exam;

import javax.ejb.EJB;
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
    public Exam getExamById(int id) {
        return em.find(Exam.class, id);
    }

    @Override
    public Exam addExam(Exam e) {
        em.persist(e);
        return e;
    }
}
