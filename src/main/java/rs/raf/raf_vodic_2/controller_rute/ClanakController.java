package rs.raf.raf_vodic_2.controller_rute;

import rs.raf.raf_vodic_2.repo.clanci.Clanak;
import rs.raf.raf_vodic_2.repo.komentari.Komentar;
import rs.raf.raf_vodic_2.services.ServisClanak;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@Path("/articles")
public class ClanakController {

    @Inject
    private ServisClanak servisClanak;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllClanci(@Context UriInfo uriInfo) {
        return Response.ok(servisClanak.getAllClanci(uriInfo)).build();
    }

    /*@GET
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<Clanak> getAllClanci() {
//        return Response.ok(servisClanak.getAllClanci()).build();
        return servisClanak.getAllClanci();
    }*/

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Clanak getClanakById(@PathParam("id") Long id) {
        return servisClanak.getClanakById(id);
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public void deleteClanak(@PathParam("id") Long id) {
        servisClanak.deleteClanak(id);
    }

    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public void incrementBrPoseta(@PathParam("id") Long id) {
        servisClanak.incrementBrPoseta(id);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Clanak addClanak(@Valid Clanak clanak) {
        return servisClanak.addClanak(clanak);
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public Clanak updateClanak(@Valid Clanak clanak) {
        return servisClanak.updateClanak(clanak);
    }

    @POST
    @Path("/komentar")
    @Produces(MediaType.APPLICATION_JSON)
    public Komentar addKomentar() {
        return servisClanak.addKomentar(addKomentar());
    }

}
