package ca.ryerson.scs.cscu.ejb.database.Faculty;

import ca.ryerson.scs.cscu.entities.Faculty;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by mitchellmohorovich on 15-07-17.
 */

@Startup
@Singleton
public class FacultyBeanImp implements FacultyBean {
	@PersistenceContext(unitName = "jdbcPU")
	EntityManager em;

	@Override
	@PostConstruct
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
		this.addFaculty(new Faculty(name));
	}

	@Override
	public void removeFaculty(int id) {
		Faculty faculty = em.find(Faculty.class, id);
		em.remove(faculty);
	}

	@Override
	public List<Faculty> getAllFaculties() {
		Query query = em.createNamedQuery("getAllFaculties");
		return query.getResultList();
	}

	@Override
	public Faculty findFacultyById(int id) {
		return em.find(Faculty.class, id);
	}

	@Override
	public Faculty findFacultyByName(String name) {
		Query query = em.createNamedQuery("getFacultyByName");
		query.setParameter("name", name);
		Faculty returnFaculty;
		try {
			returnFaculty = (Faculty) query.getSingleResult();
		} catch (Exception e) {
			returnFaculty = new Faculty("unknown");
		}
		return returnFaculty;
	}
}
