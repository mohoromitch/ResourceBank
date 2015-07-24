package ca.ryerson.scs.cscu.entities;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by mitchellmohorovich on 15-07-23.
 */

@Entity
@NamedQueries({
        @NamedQuery(
                name = "getAllFiles",
                query = "select uf from UploadedFile uf"
        )
})
public class UploadedFile implements Serializable {
    @Id
    @GeneratedValue
    private int id;

}
