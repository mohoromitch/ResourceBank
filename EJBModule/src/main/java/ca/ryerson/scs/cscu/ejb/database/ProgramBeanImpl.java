package ca.ryerson.scs.cscu.ejb.database;

import ca.ryerson.scs.cscu.entities.Program;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by mitchellmohorovich on 15-07-14.
 */

@Singleton
public class ProgramBeanImpl implements ProgramBean {
    @PersistenceContext(unitName = "jdbcPU")
    EntityManager em;

    @Override
    public void removeProgram(int id) {
        /*
        Program program = this.em.find(Program.class, id);
        em.remove(program);
        */
    }

    @Override
    public List<Program> getPrograms() {
        /*
        List<Program> programs;
        Query query = em.createNamedQuery("getAllPrograms");
        programs = query.getResultList();
        return programs;
        */
        return null;
    }

    @Override
    public void addProgram(Program program) {
        /*
        this.em.persist(program);
        */
    }

    @Override
    public void addProgram(String name, String shortname) {
        //this.addProgram(new Program(name, shortname));
    }

    @PostConstruct
    public void initializeDefaultData() {
        //this.addProgram(new Program("this is a name", "this is a shortname!"));
    }

    @Override
    public boolean entityManagerExists() {
        return (em != null);
    }


}
