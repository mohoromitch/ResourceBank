package ca.ryerson.scs.cscu.ejb.database.People;

import ca.ryerson.scs.cscu.entities.Professor;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by mitchellmohorovich on 2015-11-01.
 */
public class ProfessorBeanImpl implements ProfessorBean {
	@PersistenceContext(unitName = "jdbcPU")
	EntityManager em;

	@Override
	public void initializeDefaults() {
	}

	@Override
	public void removeProfessor(long id) {
		em.remove(this.getProfessorById(id));
	}

	@Override
	public List<Professor> getAllProfessors() {
		return em.createNamedQuery("getAllProfessors").getResultList();
	}

	@Override
	public Professor getProfessorById(long id) {
		return em.find(Professor.class, id);
	}

	@Override
	public void addProfessor(Professor professor) {
		em.persist(professor);
	}
}
