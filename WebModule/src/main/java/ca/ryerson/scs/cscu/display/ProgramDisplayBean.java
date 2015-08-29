package ca.ryerson.scs.cscu.display;

import ca.ryerson.scs.cscu.ejb.database.Programs.ProgramBean;
import ca.ryerson.scs.cscu.entities.Program;
import ca.ryerson.scs.cscu.interfaces.DisplayBean;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by mitchellmohorovich on 15-08-29.
 */
@Named("programDisplayBean")
@RequestScoped
public class ProgramDisplayBean implements DisplayBean<Program> {
    @EJB
    ProgramBean programBean;

    private String uriProgramShortName;

    private Program program;

    public void init() {
    }

    public void validateURIShortName(String shortName) throws IOException {
        this.setProgram(programBean.getProgramByShortName(shortName));
        if(this.getProgram() == null) {
            FacesContext.getCurrentInstance().getExternalContext().responseSendError(HttpServletResponse.SC_NOT_FOUND, "No program was found.");
        }
    }

    @Override
    public List<Program> getAllEntities() {
        return programBean.getAllPrograms();
    }

    @Override
    public Program findEntityById(int id) {
        return programBean.getProgramById(id);
    }

    public Program getProgram() {
        return program;
    }

    public void setProgram(Program program) {
        this.program = program;
    }

    public Program getProgramByShortName(String programShortName) {
        return programBean.getProgramByShortName(programShortName);
    }
}
