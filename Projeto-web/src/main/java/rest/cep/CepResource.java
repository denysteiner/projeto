package rest.cep;

import cep.BuscaCep;
import cep.CepService;
import cep.Webservicecep;
import model.Endereco;
import model.EnderecoRepository;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/cep")
@RequestScoped
public class CepResource {

    private static Integer ultimaLinha = 0;
    @Inject
    private BuscaCep buscaCep;
    @Inject
    private CepService cepService;
    @Inject
    private Logger log;
    @Inject
    private EnderecoRepository enderecoRepository;

    @GET
    @Path("/{cep:[0-9][0-9]*}")
    @Produces(MediaType.APPLICATION_JSON)
    public Webservicecep findCep(@PathParam("cep") String cep) {
        return buscaCep.getCep(cep);
    }

    @GET
    @Path("/persiste/{cep:[0-9][0-9]*}/")
    @Produces(MediaType.APPLICATION_JSON)
    public Endereco findCepPersiste(@PathParam("cep") String cep) {
//        final Webservicecep retornoCep = buscaCep.getCep(cep);
//        if (retornoCep.getResultado().equals("0")) {
//            return null;
//        }
//        Estado estado = estadoRepository.findBySigla(retornoCep.getUf());
//        Cidade cidade = cidadeRepository.findByNomeEEstado(retornoCep.getCidade(), estado);
//        if (cidade == null) {
//            cidade = new Cidade(retornoCep.getCidade(), estado);
//        }
//        Bairro bairro = bairroRepository.findByNome(retornoCep.getBairro());
//        if (bairro == null) {
//            bairro = new Bairro(retornoCep.getBairro());
//        }
//        Endereco endereco = enderecoRepository.findByCep(cep);
//        if (endereco == null) {
//            final Endereco endePersistir = new Endereco(cep, retornoCep.getLogradouro(), bairro, cidade);
//            endereco = enderecoService.persist(endePersistir);
//        }
        final Endereco endereco = cepService.buscaEnderecoPeloCep(cep);
        return endereco;
    }

    @GET
    @Path("/persistetodos/{cep:[0-9][0-9]*}/")
    @Produces(MediaType.APPLICATION_JSON)
    public Endereco findCepPersisteTodos(@PathParam("cep") Integer qtd) {

        ArrayList<String> linhas = new ArrayList<String>();
        InputStream is = null;
        String aux = "";
        Integer cont = 0;
        try {
            is = new FileInputStream("C:\\temp\\cep\\listaCeps.txt");
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            try {
                while (aux != null) {
                    cont++;
//                    if (cont >= 200) {
////                        break;
//                    }
                    try {
                        aux = br.readLine();
                    } catch (IOException ex) {
                        System.out.println("Não foi possivel ler o arquivo");
                    }
                    if (aux != null) {
                        linhas.add(aux);
//                        log.warning("-" + aux + "-");
                    }
                }
            } finally {
                try {
                    br.close();
                } catch (IOException ex) {
                    Logger.getLogger(CepResource.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch (FileNotFoundException ex) {
            System.out.println("Não foi encontrado o arquivo: ");
        }
        Integer total = linhas.size();
        Integer cont1 = 0;
        Integer cont2 = 0;
        for (String linha : linhas) {
            cont1++;
            if (cont1 <= ultimaLinha) {
                continue;
            }
            log.warning("->> " + linha + "- " + cont1 + " / " + total);
            ultimaLinha++;
            cont2++;
            if (cont2 >= qtd) {
                break;
            }
            if (enderecoRepository.findByCep(linha) == null) {
                cepService.buscaEnderecoPeloCep(linha);
            }
        }

        return null;
    }

}
