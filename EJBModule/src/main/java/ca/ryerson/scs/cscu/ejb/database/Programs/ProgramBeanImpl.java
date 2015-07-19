package ca.ryerson.scs.cscu.ejb.database.Programs;

import ca.ryerson.scs.cscu.ejb.database.Courses.CourseBean;
import ca.ryerson.scs.cscu.ejb.database.Faculty.FacultyBean;
import ca.ryerson.scs.cscu.entities.Faculty;
import ca.ryerson.scs.cscu.entities.Program;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
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

    @Override
    @PostConstruct
    public void initializeDefaults() {
        Program cs = new Program("Computer Science", "CS", facultyBean.findFacultyByName("Science"));
        cs.addCourse(courseBean.getCourseByCourseCode("CPS109"));
        cs.addCourse(courseBean.getCourseByCourseCode("CPS209"));
        cs.addCourse(courseBean.getCourseByCourseCode("CPS590"));
        this.addProgram(cs);
        //this.addProgram(new Program("Computer Science", "CS", facultyBean.findFacultyByName("Science")));
        this.addProgram(new Program("Electrical Engineering", "EE", facultyBean.findFacultyByName("Engineering")));
        this.addProgram(new Program("Biology", "Bio", facultyBean.findFacultyByName("Science")));
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
        return (Program) query.getSingleResult();
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
    public boolean entityManagerExists() {
        return (em != null);
    }


}
