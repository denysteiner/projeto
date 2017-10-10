package rest;

import model.Contato;
import model.ContatoRepository;
import model.ContatoService;
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

@Path("/contatos")
@RequestScoped
public class ContatoResource {

    @Inject
    private ContatoRepository repository;

    @Inject
    ContatoService service;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Contato> listAll() {
        return repository.findAll();
    }

    @GET
    @Path("/{id:[0-9][0-9]*}")
    @Produces(MediaType.APPLICATION_JSON)
    public Contato findById(@PathParam("id") long id) {
        Contato contato = repository.findById(id);
        if (contato == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        return contato;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createContato(Contato contato) {
        Response.ResponseBuilder builder = null;
        try {
            service.persist(contato);
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
