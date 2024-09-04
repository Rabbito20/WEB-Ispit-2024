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

@Path("/korisnici")
public class LoginResource {

    //  Ovo injektuje neki servis (registracija/login)

    private KorisnikDAO korisnikDAO = null;
//    new KorisnikDAO(DbHelper.getConnection());


    public LoginResource(KorisnikDAO korisnikDAO) throws SQLException {
//        this.korisnikDAO = korisnikDAO;
        try (Connection connection = DbHelper.getConnection()) {
            korisnikDAO = new KorisnikDAO(connection);
        }
    }

    @POST
    @Path("/register")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response registerUser(Korisnik korisnik) throws SQLException {
        boolean isCreated = korisnikDAO.createKorisnik(korisnik);

        if (isCreated)
            return Response.status(Response.Status.CREATED).build();
        else
            return Response.status(Response.Status.BAD_REQUEST).entity("Korisnik vec postoji!!!").build();
    }

    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response loginUser(Korisnik korisnik) throws SQLException {
        Korisnik dbUser = korisnikDAO.findKorisnikByEmail(korisnik.getEmail());
        if (dbUser != null && dbUser.getPassword().equals(korisnik.getPassword()))
            return Response.ok().build();
        else
            return Response.status(Response.Status.UNAUTHORIZED).entity("Probajte ponovo, kredencijali ne valjaju!!!").build();

    }

}
