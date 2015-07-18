package ca.ryerson.scs.cscu.web.ejb.database.Faculty;

import ca.ryerson.scs.cscu.web.entities.Faculty;

import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by mitchellmohorovich on 15-07-17.
 */

@Singleton
public class FacultyBeanImp implements FacultyBean {
    @PersistenceContext(unitName = "jdbcPU")
    EntityManager em;

    @Override
    public void initializeDefaults() {
        this.addFaculty("Science");
        this.addFaculty("Engineering");
    }

    @Override
    public void addFaculty(Faculty faculty) {
        em.persist(faculty);
    }

    @Override
    public void addFaculty(String name) {
        this.addFaculty(new Faculty());
    }

    @Override
    public void removeFaculty(int id) {
        Faculty faculty = em.find(Faculty.class, id);
        em.remove(faculty);
    }

    @Override
    public List<Faculty> getAllFaculties() {
        List<Faculty> faculties;
        Query query = em.createNamedQuery("getAllFaculties");
        return query.getResultList();
    }
}
