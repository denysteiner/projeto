package rest;

import model.Endereco;
import model.EnderecoRepository;
import model.EnderecoService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/enderecos")
@RequestScoped
public class EnderecoResource {

    @Inject
    private EnderecoRepository repository;

    @Inject
    EnderecoService service;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Endereco> listAll() {
        return repository.findAll();
    }

    @GET
    @Path("/cidade/{id:[0-9][0-9]*}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Endereco> listAllCidade(@PathParam("id") long idCidade) {
        return repository.findAllByCidade(idCidade);
    }

    @GET
    @Path("/{id:[0-9][0-9]*}")
    @Produces(MediaType.APPLICATION_JSON)
    public Endereco findById(@PathParam("id") long id) {
        Endereco endereco = repository.findById(id);
        if (endereco == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        return endereco;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createEndereco(Endereco endereco) {
        Response.ResponseBuilder builder = null;
        try {
            service.persist(endereco);
            builder = Response.ok();
        } catch (Exception e) {
            // Handle generic exceptions
            Map<String, String> responseObj = new HashMap<>();
            responseObj.put("error", e.getMessage());
            builder = Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
        }
        return builder.build();
    }

}
