package ca.ryerson.scs.cscu.interfaces;

import java.util.List;

/**
 * Created by mitchellmohorovich on 15-07-22.
 * An interface to be adhered to by all jb's that retrieve data from the db.
 */
public interface DisplayBean<E> {
    List<E> getAllEntities();
    E findEntityById(int id);
}
