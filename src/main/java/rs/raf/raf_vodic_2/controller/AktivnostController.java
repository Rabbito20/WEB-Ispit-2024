package rs.raf.raf_vodic_2.controller;

import rs.raf.raf_vodic_2.repo.aktivnosti.Aktivnost;
import rs.raf.raf_vodic_2.services.ServisAktivnost;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/aktivnosti")
public class AktivnostController {
    @Inject
    private ServisAktivnost servisAktivnost;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllAktivnosti() {
        return Response.ok(servisAktivnost.getAllAktivnosti()).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Aktivnost getAktivnostById(@PathParam("id") Long id) {
        return servisAktivnost.getAktivnostById(id);
    }

    @GET
    @Path("/naziv/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Aktivnost getAktivnostByName(@PathParam("name") String name) {
        return servisAktivnost.getAktivnostByName(name);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Aktivnost addAKtivnost(@Valid Aktivnost aktivnost) {
        return servisAktivnost.addAktivnost(aktivnost);
    }

    //  TODO:   Update i Delete
}
