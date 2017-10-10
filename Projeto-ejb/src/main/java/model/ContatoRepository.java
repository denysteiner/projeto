package model;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

import util.AbstractRepository;
import javax.persistence.Query;

@ApplicationScoped
public class ContatoRepository extends AbstractRepository<Contato> {

    public Contato findById(Long id) {
        return em.find(Contato.class, id);
    }

    public List<Contato> findAll() {
        Query query = em.createQuery("SELECT c FROM Contato c");
        return (List) query.getResultList();
    }

    
}
