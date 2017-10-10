package util;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

@Stateless
public class GerenciarDados {

    @Inject
    private EntityManager em;

    public void apagar() {
        em.createQuery("delete from Endereco").executeUpdate();
        em.createQuery("delete from Bairro").executeUpdate();
        em.createQuery("delete from Cidade").executeUpdate();
    }
}
