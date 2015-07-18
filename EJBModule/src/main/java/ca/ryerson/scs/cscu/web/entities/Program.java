package ca.ryerson.scs.cscu.web.entities;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by mitchellmohorovich on 15-07-14.
 */

@Entity
@NamedQueries({
        @NamedQuery(
                name = "getAllPrograms",
                query = "select p from Program p"
        )
})
public class Program implements Serializable {
    @Id
    @GeneratedValue
    private int id;
    private String name;
    private String shortname;

    public Program() {}

    public Program(String name, String shortname) {
        this.name = name;
        this.shortname = shortname;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getShortform() {
        return shortname;
    }
}
