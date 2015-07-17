package ca.ryerson.scs.cscu;

import ca.ryerson.scs.cscu.ejb.MessageBean;
import ca.ryerson.scs.cscu.ejb.database.ProgramBean;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.sql.DataSource;
import java.io.Serializable;

/**
 * Created by mitchellmohorovich on 15-07-11.
 */

@Named("helloBean")
@SessionScoped
public class HelloBean implements Serializable {

    @Resource(name = "jdbc/ResourceBank")
    DataSource ds;


    @EJB
    MessageBean mb;

    @EJB
    ProgramBean pb;

    String message = "message was no overridden";
    String dataSource;

    public String getMessage() {
        if(mb != null)
            this.setMessage(mb.getMessage());
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDataSource() {
        if(this.dataSource == null) {
            if(this.ds == null) {
                this.setDataSource("Nope. :(");
            } else {
                this.setDataSource("Yes!");
            }
        }
        return dataSource;
    }

    public String testConnection() {
        if(pb == null)
            return "ProgramBean is null.";
        else
            return String.valueOf(pb.entityManagerExists());
    }

    public void setDataSource(String dataSource) {
        this.dataSource = dataSource;
    }
}
