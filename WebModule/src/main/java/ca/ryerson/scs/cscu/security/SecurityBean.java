package ca.ryerson.scs.cscu.security;

import javax.enterprise.context.RequestScoped;
import javax.faces.FacesException;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by mitchellmohorovich on 15-07-12.
 */

@Named(value = "securityBean")
@RequestScoped
public class SecurityBean {
    private static String ADMIN_ROLE = "admin";
    private static String USER_ROLE = "user";

    private HttpServletRequest getHttpServletRequest() {
        return (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
    }

    public String getUserName() {
        HttpServletRequest request = this.getHttpServletRequest();
        if (request.getUserPrincipal() != null) {
            return request.getUserPrincipal().getName();
        } else {
            return "";
        }
    }

    public boolean isUserAdmin() {
        return (this.getHttpServletRequest().isUserInRole(ADMIN_ROLE));
    }

    public boolean isLoggedIn() {
        return (this.getHttpServletRequest().getUserPrincipal() != null);
    }

    public void logout() {
        try {
            this.getHttpServletRequest().logout();
            this.getHttpServletRequest().getSession().invalidate();
        } catch (ServletException e) {
            throw new FacesException("Failure on logout", e);
        }
    }
}
