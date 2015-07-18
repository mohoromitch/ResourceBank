package ca.ryerson.scs.cscu.web.entities;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by mitchellmohorovich on 15-07-17.
 */

@Entity
@NamedQueries(
        @NamedQuery(
                name = "getAllFaculties",
                query = "select f from Faculty f"
        )
)
public class Faculty implements Serializable {
    @Id
    @GeneratedValue
    private int id;
    private String name;

    public Faculty() {
        this.name = "";
    }

    public Faculty(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
