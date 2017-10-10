package model;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

import util.AbstractRepository;
import javax.persistence.Query;

@ApplicationScoped
public class UsuarioRepository extends AbstractRepository<Usuario>{

    public Usuario findById(Long id) {
        return em.find(Usuario.class, id);
    }

    public Usuario findByEmail(String email) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Usuario> criteria = cb.createQuery(Usuario.class);
        Root<Usuario> usuario = criteria.from(Usuario.class);
        criteria.select(usuario).where(cb.equal(usuario.get("email"), email));
        return em.createQuery(criteria).getSingleResult();
    }

    public List<Usuario> findAllOrderedByNome() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Usuario> criteria = cb.createQuery(Usuario.class);
        Root<Usuario> usuario = criteria.from(Usuario.class);
        criteria.select(usuario).orderBy(cb.asc(usuario.get("nome")));
        return em.createQuery(criteria).getResultList();
    }

    public List<Usuario> findAll() {
        Query query = em.createQuery("SELECT u FROM Usuario u");
        return (List) query.getResultList();
    }
}
