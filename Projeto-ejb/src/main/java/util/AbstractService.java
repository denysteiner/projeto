package util;

import java.io.Serializable;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.validation.Valid;

public abstract class AbstractService<T extends Object> implements Serializable {

    @Inject
    protected EntityManager em;
   
    public AbstractService() {
    }

    public T persist(@Valid T entity) {
        return em.merge(entity);
    }

    public void remove(T entity) {
        em.remove(entity);
    }
}
