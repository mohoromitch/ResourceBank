package ca.ryerson.scs.cscu.interfaces;

import java.io.IOException;

/**
 * Created by mitchellmohorovich on 15-07-22.
 */
public interface AdminBean<E> {
    void persistEntity() throws IOException;
    void removeEntityById(int id);
}
