package rs.raf.raf_vodic_2.controller;

import rs.raf.raf_vodic_2.repo.korisnici.Korisnik;
import rs.raf.raf_vodic_2.services.ServisKorisnik;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/user")
public class KorisnikController {

    @Inject
    private ServisKorisnik korisnikServis;

    @GET
//    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllKorisnici() {
        return Response.ok(korisnikServis.getAllKorisnici()).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Korisnik getKorisnikById(@PathParam("id") Long id) {
        return korisnikServis.getKorisnikById(id);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Korisnik addKorisnik(@Valid Korisnik korisnik) {
        return korisnikServis.addKorisnik(korisnik);
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public Korisnik updateKorisnik(@Valid Korisnik korisnik) {
        return korisnikServis.updateKorisnik(korisnik);
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public void deleteKorisnika(@PathParam("id") Long id) {
        korisnikServis.deleteKorsinik(id);
    }

    //  GET getTipKorsinika mislim da ne treba, jer su staticki???

}
