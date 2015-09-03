package ca.ryerson.scs.cscu.entities;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by mitchellmohorovich on 15-07-17.
 * A basic entity used to categorize programs.
 */

@Entity
@NamedQueries({
        @NamedQuery(
                name = "getAllFaculties",
                query = "select f from Faculty f"
        ),
        @NamedQuery(
                name = "getFacultyByName",
                query = "select f from Faculty f where f.name = :name"
        )
})
public class Faculty implements Serializable {
    @Id
    @GeneratedValue
    private int id;
    private String name;

    public Faculty() {
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
