package rest;

import model.Estado;
import model.EstadoRepository;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("/estados")
@RequestScoped
public class EstadoResource {

    @Inject
    private EstadoRepository repository;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Estado> listAllEstados() {
        return repository.findAll();
    }

    @GET
    @Path("/{id:[0-9][0-9]*}")
    @Produces(MediaType.APPLICATION_JSON)
    public Estado findById(@PathParam("id") long id) {
        Estado estado = repository.findById(id);
        if (estado == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        return estado;
    }

}
