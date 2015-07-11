package ca.ryerson.scs.cscu;

import ca.ryerson.scs.cscu.ejb.MessageBean;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;

/**
 * Created by mitchellmohorovich on 15-07-11.
 */

@Named("helloBean")
@SessionScoped
public class HelloBean implements Serializable {

    @EJB
    MessageBean mb;
    String message = "message was no overridden";

    public String getMessage() {
        if(mb != null)
            this.setMessage(mb.getMessage());
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
