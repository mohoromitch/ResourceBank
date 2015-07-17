package ca.ryerson.scs.cscu.admin;

import ca.ryerson.scs.cscu.ejb.database.ProgramBean;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.io.Serializable;

/**
 * Created by mitchellmohorovich on 15-07-16.
 */

@Named("adminProgramBean")
@RequestScoped
public class AdminProgramBean implements Serializable {
    @EJB()
    ProgramBean pb;

    String name;
    String shortName;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getshortName() {
        return shortName;
    }

    public void setshortName(String shortName) {
        this.shortName = shortName;
    }

    public void addProgram() {
        pb.addProgram(this.getName(), this.getshortName());
    }
}
