package model;

import com.mysema.query.jpa.impl.JPAQuery;
import javax.enterprise.context.ApplicationScoped;
import java.util.List;

import util.AbstractRepository;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

@ApplicationScoped
public class EnderecoRepository extends AbstractRepository<Endereco> {

    public Endereco findById(Long id) {
        return em.find(Endereco.class, id);
    }

//    public List<Endereco> findAll() {
//        Query query = em.createQuery("SELECT c FROM Endereco c");
//        return (List) query.getResultList();
//    }
    public List<Endereco> findAll() {
        QEndereco endereco = QEndereco.endereco;
        JPAQuery query = new JPAQuery(em);
        List<Endereco> enderecos = query.from(endereco).fetchAll().list(endereco);
        return enderecos;
    }

    public Endereco findByCep(final String cep) {
        final Query query = em.createQuery("SELECT c FROM Endereco c where c.cep= :cep")
                .setParameter("cep", cep);
        final List<Endereco> listaEnderecos = query.getResultList();
        if (listaEnderecos.isEmpty()) {
            return null;
        }
        return listaEnderecos.stream().findFirst().get();
    }

    public List<Endereco> findAllByCidade(final Long idCidade) {
//        String consulta = "select c from Endereco c where c.cidade.id = :idCidade";
//        TypedQuery<Endereco> query = em.createQuery(consulta, Endereco.class);
//        query.setParameter("idCidade", idCidade);
//        return (List) query.getResultList();
        QEndereco endereco = QEndereco.endereco;
        JPAQuery query = new JPAQuery(em);
        List<Endereco> enderecos = query.from(endereco)
                .where(QEndereco.endereco.cidade.id.eq(idCidade))
                .fetchAll().list(endereco);
        return enderecos;

    }
}
