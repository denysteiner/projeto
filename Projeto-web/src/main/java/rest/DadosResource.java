package rest;

import cep.CepService;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import util.GerenciarDados;

@Path("/dados")
@RequestScoped
public class DadosResource {

    @Inject
    private GerenciarDados gerenciarDados;
    @Inject
    private CepService cepService;

    @POST
    @Path("/apagar")
    @Produces(MediaType.APPLICATION_JSON)
    public void apagar() {
        gerenciarDados.apagar();
    }

    @POST
    @Path("/gerarDados")
    @Produces(MediaType.APPLICATION_JSON)
    public void gerarDados() {
        cepService.buscaEnderecoPeloCep("88802010");
        cepService.buscaEnderecoPeloCep("88138106");
        cepService.buscaEnderecoPeloCep("88138110");
        cepService.buscaEnderecoPeloCep("88138115");
        cepService.buscaEnderecoPeloCep("88010100");
        cepService.buscaEnderecoPeloCep("88010095");
        cepService.buscaEnderecoPeloCep("88802015");
        cepService.buscaEnderecoPeloCep("88802020");
    }

}
