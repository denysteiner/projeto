package util;

import java.io.Serializable;
import java.util.Optional;
import javax.ejb.TransactionAttribute;
import javax.inject.Inject;
import javax.persistence.EntityManager;

public abstract class AbstractService<T extends Object> implements Serializable {

    @Inject
    protected EntityManager em;

    public AbstractService() {
    }

    public T persist(T entity) {
        return em.merge(entity);
    }

    public void remove(T entity) {
        em.remove(entity);
    }
}
