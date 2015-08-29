package ca.ryerson.scs.cscu.ejb.database.Programs;

import ca.ryerson.scs.cscu.ejb.database.Courses.CourseBean;
import ca.ryerson.scs.cscu.ejb.database.Courses.CourseManagementForms.CourseManagementFormBean;
import ca.ryerson.scs.cscu.ejb.database.Courses.Exams.ExamBean;
import ca.ryerson.scs.cscu.ejb.database.Faculty.FacultyBean;
import ca.ryerson.scs.cscu.entities.*;
import ca.ryerson.scs.cscu.enums.Semester;

import javax.annotation.PostConstruct;
import javax.ejb.*;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by mitchellmohorovich on 15-07-14.
 */

@Startup
@Singleton
public class ProgramBeanImpl implements ProgramBean {
    @PersistenceContext(unitName = "jdbcPU")
    EntityManager em;

    @EJB
    FacultyBean facultyBean;

    @EJB
    CourseBean courseBean;

    @EJB
    ExamBean examBean;

    @EJB
    CourseManagementFormBean courseManagementFormBean;

    @Override
    @PostConstruct
    public void initializeDefaults() {
        Program cs = new Program("Computer Science", "CS", facultyBean.findFacultyByName("Science"));
        cs.addCourse(courseBean.getCourseByCourseCode("CPS109"));
        cs.addCourse(courseBean.getCourseByCourseCode("CPS209"));
        this.addProgram(cs);

        Exam e = examBean.addExam(new Exam((short) 2015, Semester.winter, Exam.Type.exam));
        e.setOwnerCourse(courseBean.getCourseByCourseCode("CPS590"));
        courseBean.getCourseByCourseCode("CPS590").addExam(e);
        cs.addCourse(courseBean.getCourseByCourseCode("CPS590"));

        CourseManagementForm testCMF = new CourseManagementForm((short) 1995, Semester.fall, "Dr. Derpanis", courseBean.getCourseByCourseCode("CPS109"));
        courseManagementFormBean.addCourseManagementForm(testCMF);
        courseBean.addCourseManagementFormToCourse(testCMF, courseBean.getCourseByCourseCode("CPS109").getId());

        e = examBean.addExam(new Exam((short) 2014, Semester.fall, Exam.Type.midterm));
        e.setOwnerCourse(courseBean.getCourseByCourseCode("CPS590"));
        courseBean.getCourseByCourseCode("CPS590").addExam(e);
        //this.addProgram(new Program("Computer Science", "CS", facultyBean.findFacultyByName("Science")));
        //this.addProgram(new Program("Electrical Engineering", "EE", facultyBean.findFacultyByName("Engineering")));
        //this.addProgram(new Program("Biology", "Bio", facultyBean.findFacultyByName("Science")));
    }

    @Override
    public void removeProgram(int id) {
        Program program = this.em.find(Program.class, id);
        em.remove(program);
    }

    @Override
    public List<Program> getAllPrograms() {
        List<Program> programs;
        Query query = em.createNamedQuery("getAllPrograms");
        programs = query.getResultList();
        return programs;
    }

    @Override
    public List<Program> getProgramsByFaculty(Faculty f) {
        return null;
    }

    @Override
    public Program getProgramByShortName(String shortName) {
        Query query = em.createNamedQuery("getProgramByShortName");
        query.setParameter("shortName", shortName);
        Program returnProgram = null;
        try {
            returnProgram = (Program) query.getSingleResult();
        } catch (NoResultException e) {
        } finally {
            return returnProgram;
        }
    }

    @Override
    public Program getProgramById(int id) {
        return em.find(Program.class, id);
    }

    @Override
    public void addProgram(Program program) {
        try {
            this.em.persist(program);
        } catch (EJBException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addProgram(String name, String shortName) {
        this.addProgram(new Program(name, shortName, null));
    }

    public void addProgram(String name, String shortName, Faculty faculty) {
        this.addProgram(new Program(name, shortName, faculty));
    }

    @Override
    public void addCourseToProgramByShortName(int courseId, String shortName) {
        Program program = this.getProgramByShortName(shortName);
        program.addCourse(courseBean.getCourseById(courseId));
        em.persist(program);
    }

    @Override
    public boolean entityManagerExists() {
        return (em != null);
    }


}
