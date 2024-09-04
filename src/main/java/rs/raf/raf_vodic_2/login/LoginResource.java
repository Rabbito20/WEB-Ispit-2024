package rs.raf.raf_vodic_2.login;

import rs.raf.raf_vodic_2.repo.korisnici.Korisnik;
import rs.raf.raf_vodic_2.repo.korisnici.KorisnikDAO;
import rs.raf.raf_vodic_2.rest_api.DbHelper;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.Connection;
import java.sql.SQLException;

@Path("/entrance")
public class LoginResource {

    //  Ovo injektuje neki servis (registracija/login)

    private KorisnikDAO korisnikDAO = null;
//    new KorisnikDAO(DbHelper.getConnection());


    public LoginResource(KorisnikDAO korisnikDAO) throws SQLException {
//        this.korisnikDAO = korisnikDAO;
        try (Connection connection = DbHelper.getConnection()) {
//            korisnikDAO = new KorisnikDAO(connection);
        }
    }


    @POST
    @Path("/register")
    @Consumes(MediaType.APPLICATION_JSON)
//    @Produces("application/json")
    public Response registerUser(Korisnik korisnik) {
        /*boolean isCreated = korisnikDAO.addKorisnik(korisnik);

        if (isCreated)
            return Response.status(Response.Status.CREATED).build();
        else
            return Response.status(Response.Status.BAD_REQUEST).entity("Korisnik vec postoji!!!").build();*/
        return Response.status(Response.Status.BAD_REQUEST).build();
    }

    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response loginUser(Korisnik korisnik) {
        Korisnik dbUser = korisnikDAO.getKorisnikByEmail(korisnik.getEmail());
        if (dbUser != null && dbUser.getPassword().equals(korisnik.getPassword()))
            return Response.ok().build();
        else
            return Response.status(Response.Status.UNAUTHORIZED).entity("Probajte ponovo, kredencijali ne valjaju!!!").build();

    }

}
