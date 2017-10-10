package model;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

import javax.persistence.Query;

@ApplicationScoped
public class BairroRepository {

    @Inject
    private EntityManager em;

    public Bairro findById(Long id) {
        return em.find(Bairro.class, id);
    }

    public List<Bairro> findAll() {
        Query query = em.createQuery("SELECT b FROM Bairro b");
        return (List) query.getResultList();
    }

    public Bairro findByNome(final String nome) {
        final Query query = em.createQuery("SELECT e FROM Bairro e where e.nome = :nome")
                .setParameter("nome", nome);
        final List<Bairro> listaBairros = query.getResultList();
        if (listaBairros.isEmpty()) {
            return null;
        }
        return listaBairros.stream().findFirst().get();
    }
}
