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
public class CourseCodeValidator implements Validator {
    private static final String regex = "[A-Z]+[0-9]+";
    private Pattern pattern;
    private Matcher matcher;

    @EJB
    ProgramBean programBean;

    @PostConstruct
    void init() {
        pattern = Pattern.compile(regex);
    }

    public CourseCodeValidator() {
        pattern = Pattern.compile(regex);
    }

    /**
     * This validator checks to see if the user entered course code is valid.
     * A valid course code is one that is one word in length, the first part all capital letters, and the other part numbers.
     * This format is represented using the regex [A-Z]+[0-9]+, one or more capital letter, one or more digit.
     * This validator also checks to see if there isn't already a course with that course code in the database.
     * @param facesContext The FacesContext instance.
     * @param uiComponent The UIComponent... lol
     * @param o Is the user entered string.
     * @throws ValidatorException Which will be the outputted error.
     */
    @Override
    public void validate(FacesContext facesContext, UIComponent uiComponent, Object o) throws ValidatorException {
        String input = o.toString();
        matcher = pattern.matcher(input);

        //Check correct format
        if(!matcher.matches()) {
            FacesMessage message = new FacesMessage("Course code validation failed: Invalid format.", "Invalid course code format.");
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(message);
        }

        //See if that course code doesn't already exist in the database
        if(programBean.getProgramByShortName(input) != null) {
            FacesMessage message = new FacesMessage("Course code validation failed: Short name already exists.", "Course with that course code already exists.");
            message.setSeverity((FacesMessage.SEVERITY_ERROR));
            throw new ValidatorException(message);
        }
    }
}
