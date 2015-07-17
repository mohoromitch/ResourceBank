package ca.ryerson.scs.cscu.ejb;

import javax.ejb.Stateless;
import javax.inject.Named;

/**
 * Created by mitchellmohorovich on 15-07-11.
 */

@Stateless
public class MessageBeanImpl implements MessageBean {
    @Override
    public String getMessage() {
        return "Hello from EJB!";
    }
}
