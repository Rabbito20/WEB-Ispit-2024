package rs.raf.raf_vodic_2.controller_rute;

import rs.raf.raf_vodic_2.repo.destinacije.Destinacija;
import rs.raf.raf_vodic_2.services.ServisDestinacije;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/destinacija")
public class DestinacijaController {

    @Inject
    private ServisDestinacije servisDestinacije;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllDestinacije() {
        return Response.ok(servisDestinacije.getAllDestinacije()).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Destinacija getDestinacijaById(@PathParam("id") Long id) {
        return servisDestinacije.getDestinacijaById(id);
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public void deleteDestinacija(@PathParam("id") Long id) {
        servisDestinacije.deleteDestinacija(id);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Destinacija addDestinacija(@Valid Destinacija destinacija) {
        return servisDestinacije.addDestinacija(destinacija);
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public Destinacija updateDestinacija(@Valid Destinacija destinacija) {
        return servisDestinacije.updateDestinacija(destinacija);
    }
}
