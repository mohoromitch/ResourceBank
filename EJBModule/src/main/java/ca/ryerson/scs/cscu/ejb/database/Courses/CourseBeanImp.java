package ca.ryerson.scs.cscu.ejb.database.Courses;

import ca.ryerson.scs.cscu.ejb.database.Programs.ProgramBean;
import ca.ryerson.scs.cscu.entities.Course;
import ca.ryerson.scs.cscu.entities.CourseManagementForm;
import ca.ryerson.scs.cscu.entities.Exam;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by mitchellmohorovich on 15-07-19.
 */
@Startup
@Singleton
public class CourseBeanImp implements CourseBean {
    @PersistenceContext(unitName = "jdbcPU")
    EntityManager em;

    @EJB
    ProgramBean programBean;

    @Override
    @PostConstruct
    public void initializeDefaults() {
        this.addCourse(new Course("CPS109", "Introduction to Computer Science I"));
        this.addCourse(new Course("CPS209", "Introduction to Computer Science II"));
        this.addCourse(new Course("CPS590", "Introduction to Operating Systems"));
    }

    @Override
    public void removeCourse(int id) {
        Course course = this.em.find(Course.class, id);
        em.remove(course);
    }

    @Override
    public List<Course> getAllCourses() {
        List<Course> courses;
        Query query = em.createNamedQuery("getAllCourses");
        courses = query.getResultList();
        return courses;
    }

    @Override
    public Course getCourseById(int id) {
        return em.find(Course.class, id);
    }

    @Override
    public Course getCourseByCourseCode(String courseCode) {
        if(courseCode == null) return null;
        Query query = em.createNamedQuery("getCourseByCourseCode");
        query.setParameter("courseCode", courseCode);
        Course returnCourse = null;
        try {
            returnCourse = (Course) query.getSingleResult();
        } catch (Exception e) {
            returnCourse = new Course("unknown", "n/a");
        } finally {
            return returnCourse;
        }
    }

    @Override
    public void addCourse(Course c) {
        em.persist(c);
    }

    @Override
    public boolean entityManagerExists() {
        return (em != null);
    }

    @Override
    public void addExamToCourse(int id, Exam exam) {
        this.getCourseById(id).addExam(exam);
    }

    @Override
    public void addExamToCourse(String courseCode, Exam exam) {
        this.getCourseByCourseCode(courseCode).addExam(exam);
    }

    @Override
    public void addCourseManagementFormToCourse(CourseManagementForm cmf, int id) {
        this.getCourseById(id).addCourseManagementForm(cmf);
    }
}
