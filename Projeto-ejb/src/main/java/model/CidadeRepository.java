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
public class CidadeRepository {

    @Inject
    private EntityManager em;

    public Cidade findById(Long id) {
        return em.find(Cidade.class, id);
    }

    public List<Cidade> findAllOrderedByNome() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Cidade> criteria = cb.createQuery(Cidade.class);
        Root<Cidade> cidade = criteria.from(Cidade.class);
        criteria.select(cidade).orderBy(cb.asc(cidade.get("nome")));
        return em.createQuery(criteria).getResultList();
    }

    public List<Cidade> findAll() {
        Query query = em.createQuery("SELECT c FROM Cidade c");
        return (List) query.getResultList();
    }

    public Cidade findByNomeEEstado(final String nome, final Estado estado) {
        final Query query = em.createQuery("SELECT c FROM Cidade c where c.nome = :nome and c.estado = :estado")
                .setParameter("nome", nome)
                .setParameter("estado", estado);
        final List<Cidade> listaCidades = query.getResultList();
        if (listaCidades.isEmpty()) {
            return null;
        }
        return listaCidades.stream().findFirst().get();
    }
}
