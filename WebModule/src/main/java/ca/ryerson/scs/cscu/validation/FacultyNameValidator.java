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
public class FacultyNameValidator implements Validator {
    private static final String regex = "[\\w ]+";
    private Pattern pattern;
    private Matcher matcher;
    private static final int MIN_LENGTH = 3;
    private static final int MAX_LENGTH = 30;

    @EJB
    ProgramBean programBean;

    @PostConstruct
    void init() {
        pattern = Pattern.compile(regex);
    }

    public FacultyNameValidator() {
        pattern = Pattern.compile(regex);
    }

    /**
     * This validator checks to see if the user entered faculty name is valid.
     * A valid faculty name is one that is at least one word in length.
     * @param facesContext The FacesContext instance.
     * @param uiComponent The UIComponent... lol
     * @param o Is the user entered string.
     * @throws ValidatorException Which will be the outputted error.
     */
    @Override
    public void validate(FacesContext facesContext, UIComponent uiComponent, Object o) throws ValidatorException {
        String input = o.toString();
        matcher = pattern.matcher(input);


        if(input.length() == 0) {
            FacesMessage message = new FacesMessage("Faculty name validation failed: Name cannot be empty.", "Faculty name is a required field.");
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(message);
        }

        if(input.length() < MIN_LENGTH) {
            FacesMessage message = new FacesMessage("Faculty name validation failed: Name is too short. It needs to be at least 3 characters.", "Invalid faculty name format.");
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(message);
        }

        if(input.length() > MAX_LENGTH) {
            FacesMessage message = new FacesMessage("Faculty name validation failed: Name is too long. It needs to be at most " + MAX_LENGTH + " characters.", "Invalid faculty name format.");
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(message);
        }

        //Check correct format
        if(!matcher.matches()) {
            FacesMessage message = new FacesMessage("Faculty name validation failed: Invalid format.", "Invalid faculty name format.");
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(message);
        }

        //See if that course code doesn't already exist in the database
        if(programBean.getProgramByShortName(input) != null) {
            FacesMessage message = new FacesMessage("Faculty name validation failed: Faculty name already exists.", "Faculty with that course code already exists.");
            message.setSeverity((FacesMessage.SEVERITY_ERROR));
            throw new ValidatorException(message);
        }
    }
}
