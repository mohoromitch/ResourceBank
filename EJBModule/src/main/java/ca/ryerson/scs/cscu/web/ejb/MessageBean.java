package ca.ryerson.scs.cscu.web.ejb;

import javax.ejb.Remote;

/**
 * Created by mitchellmohorovich on 15-07-11.
 */

@Remote
public interface MessageBean {
    String getMessage();
}
