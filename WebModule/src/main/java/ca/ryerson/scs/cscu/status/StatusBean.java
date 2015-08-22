package ca.ryerson.scs.cscu.status;

import ca.ryerson.scs.cscu.ejb.database.Faculty.FacultyBean;
import ca.ryerson.scs.cscu.ejb.database.Programs.ProgramBean;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.sql.DataSource;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by mitchellmohorovich on 15-07-17.
 */

@Named("statusBean")
@RequestScoped
public class StatusBean implements Serializable {

    @Resource(name = "jdbc/ResourceBank")
    DataSource ds;

    @EJB
    ProgramBean programBean;

    @EJB
    FacultyBean facultyBean;

    Date date;

    public boolean dataSourceInjected() {
        return ds != null;
    }

    public boolean programBeanExists() {
        return this.programBean != null;
    }

    public boolean programBeanEntityManagerExists() {
        if(this.programBeanExists()) {
            return this.programBean.entityManagerExists();
        } else {
            return false;
        }
    }

    public boolean facultyBeanExists() {
        return this.facultyBean != null;
    }
}
