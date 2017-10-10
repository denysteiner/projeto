package util;

import java.io.Serializable;
import java.util.Optional;
import javax.inject.Inject;
import javax.persistence.EntityManager;

public abstract class AbstractRepository<T extends Object> implements Serializable {

    @Inject
    protected EntityManager em;

    public AbstractRepository() {
    }

//    public T findById(Long id) {
//        return em.find(T, id);
//    }
}
