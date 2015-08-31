package ca.ryerson.scs.cscu.validation;

import ca.ryerson.scs.cscu.ejb.database.Programs.ProgramBean;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.inject.Named;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by mitchellmohorovich on 15-08-29.
 */
//@FacesValidator("programShortNameValidator") when JSF 2.3 comes out this will be usable.
@Named
@ApplicationScoped
public class ProgramShortNameValidator implements Validator {
    private static final String regex = "[A-Z]{2,}";
    private Pattern pattern;
    private Matcher matcher;

    @EJB
    ProgramBean programBean;

    @PostConstruct
    void init() {
        pattern = Pattern.compile(regex);
    }

    public ProgramShortNameValidator() {
        pattern = Pattern.compile(regex);
    }

    /**
     * This validator checks to see if the user entered short code is valid.
     * Valid short codes use the [A-Z]{2,} regex, which  means any capital letter of at least 2 letters in length.
     * This validator also checks to see if the short name already exists.
     * @param facesContext The FacesContext instance.
     * @param uiComponent The UIComponent... lol
     * @param o Is the user entered string.
     * @throws ValidatorException Which will be the outputted error.
     */
    @Override
    public void validate(FacesContext facesContext, UIComponent uiComponent, Object o) throws ValidatorException {
        String input = o.toString();
        matcher = pattern.matcher(input);
        if(!matcher.matches()) {
            FacesMessage message = new FacesMessage("Short name validation failed: Invalid format.", "Invalid short code format.");
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(message);
        }

        if(programBean.getProgramByShortName(input) != null) {
            FacesMessage message = new FacesMessage("Short name validation failed: Short name already exists.", "Short name already exists.");
            message.setSeverity((FacesMessage.SEVERITY_ERROR));
            throw new ValidatorException(message);
        }
    }
}
