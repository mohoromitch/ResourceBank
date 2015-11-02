package ca.ryerson.scs.cscu.ejb;

import javax.ejb.Stateless;

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
