package ca.ryerson.scs.cscu.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by mitchellmohorovich on 15-07-19.
 */

@Entity
public class Exam implements Serializable {
    @Id
    @GeneratedValue
    private int id;
    private short year;
    private String semester; //Winter, Spring, Summer, Fall :)
    private String type; //Exam, Test, Practice Test, Practice Exam
    private String filename; //prepended with the file location
}
