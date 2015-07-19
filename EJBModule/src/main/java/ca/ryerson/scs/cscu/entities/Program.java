package ca.ryerson.scs.cscu.entities;

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
    private String shortName;
    @ManyToOne
    private Faculty faculty;

    public Program() {}

    public Program(String name, String shortName) {
        this.name = name;
        this.shortName = shortName;
    }

    public Program(String name, String shortName, Faculty faculty) {
        this.name = name;
        this.shortName = shortName;
        this.faculty = faculty;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getShortname() {
        return shortName;
    }

    public Faculty getFaculty() {
        return faculty;
    }
}
