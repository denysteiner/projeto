package rest;

import model.Cidade;
import model.CidadeRepository;
import model.CidadeService;
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

@Path("/cidades")
@RequestScoped
public class CidadeResource {
    @Inject
    private CidadeRepository repository;

    @Inject
    CidadeService service;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Cidade> listAllCidades() {
        return repository.findAll();
    }

    @GET
    @Path("/{id:[0-9][0-9]*}")
    @Produces(MediaType.APPLICATION_JSON)
    public Cidade findById(@PathParam("id") long id) {
        Cidade cidade = repository.findById(id);
        if (cidade == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        return cidade;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createCidade(Cidade cidade) {
        Response.ResponseBuilder builder = null;
        try {
            service.persist(cidade);
            builder = Response.ok();
        } catch (Exception e) {
            Map<String, String> responseObj = new HashMap<>();
            responseObj.put("error", e.getMessage());
            builder = Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
        }
        return builder.build();
    }

}
