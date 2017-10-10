package model;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

import javax.persistence.Query;

@ApplicationScoped
public class EstadoRepository {

    @Inject
    private EntityManager em;

    public Estado findById(Long id) {
        return em.find(Estado.class, id);
    }

    public List<Estado> findAllOrderedByNome() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Estado> criteria = cb.createQuery(Estado.class);
        Root<Estado> estado = criteria.from(Estado.class);
        criteria.select(estado).orderBy(cb.asc(estado.get("nome")));
        return em.createQuery(criteria).getResultList();
    }

    public List<Estado> findAll() {
        Query query = em.createQuery("SELECT e FROM Estado e");
        return (List) query.getResultList();
    }

    public Estado findBySigla(final String sigla) {
        return (Estado) em.createQuery("SELECT e FROM Estado e where e.sigla = :sigla")
                .setParameter("sigla", sigla)
                .getSingleResult();
    }
}
