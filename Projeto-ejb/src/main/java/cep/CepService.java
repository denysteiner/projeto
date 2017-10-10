package cep;

import model.Bairro;
import model.BairroRepository;
import model.Cidade;
import model.CidadeRepository;
import model.Endereco;
import model.EnderecoRepository;
import model.EnderecoService;
import model.Estado;
import model.EstadoRepository;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

@Stateless
public class CepService {

    @Inject
    protected EntityManager em;
    @Inject
    private BuscaCep buscaCep;
    @Inject
    private EnderecoService enderecoService;
    @Inject
    private EstadoRepository estadoRepository;
    @Inject
    private CidadeRepository cidadeRepository;
    @Inject
    private BairroRepository bairroRepository;
    @Inject
    private EnderecoRepository enderecoRepository;
    @Inject
    private Logger log;

    public Endereco buscaEnderecoPeloCep(final String cep) {
        final Webservicecep retornoCep = buscaCep.getCep(cep);

//        if (!"Criciúma".equals(retornoCep.getCidade())) {
//            return null;
//        }
        Endereco endereco = enderecoRepository.findByCep(cep);
        if (endereco != null) {
            return endereco;
        }
        if (retornoCep.getUf().isEmpty()) {
            return null;
        }

        Estado estado = estadoRepository.findBySigla(retornoCep.getUf().substring(0, 2));
        final String nomeCidade = retornoCep.getCidade().trim();
        Cidade cidade = cidadeRepository.findByNomeEEstado(nomeCidade, estado);
        if (cidade == null) {
            cidade = new Cidade(nomeCidade, estado);
        }
        final String nomeBairro = retornoCep.getBairro().trim();
        Bairro bairro = bairroRepository.findByNome(nomeBairro);
        if (bairro == null && !nomeBairro.isEmpty()) {
            bairro = new Bairro(nomeBairro);
        }

        if (endereco == null) {
            final String tipoLogradouro = retornoCep.getTipo_logradouro().trim();
            final String logradouro = retornoCep.getLogradouro().trim();
            final Endereco endePersistir = new Endereco(cep, tipoLogradouro + " " + logradouro, bairro, cidade);
            endereco = enderecoService.persist(endePersistir);
//            log.warning("Endereco cadastrado: " + endereco);
        }
        return endereco;
    }
}
