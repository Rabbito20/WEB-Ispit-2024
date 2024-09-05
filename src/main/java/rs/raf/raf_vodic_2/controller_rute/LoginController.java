package rs.raf.raf_vodic_2.controller_rute;

import rs.raf.raf_vodic_2.login.KorisnikLogin;
import rs.raf.raf_vodic_2.login.ValidJWT;
import rs.raf.raf_vodic_2.repo.korisnici.Korisnik;
import rs.raf.raf_vodic_2.services.ServisKorisnik;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;

@Path("/login")
public class LoginController {

    @Inject
    private ServisKorisnik servisKorisnik;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(@Valid KorisnikLogin userLogin) {
        Map<String, String> response = new HashMap<>();
        String jwt = servisKorisnik.login(userLogin.getEmail(), userLogin.getPassword());

        if (jwt == null)
            return Response.status(422, "Unprocessable Entity").entity(response).build();

        Korisnik korisnik = servisKorisnik.getKorisnikByEmail(userLogin.getEmail());
        response.put("jwt", jwt);
        response.put("name", korisnik.getIme());
        response.put("tip_korisnika", korisnik.getTipKorisnika().toString());

        return Response.ok(response).build();
    }

    @POST
    @Path("/valid")
    @Produces(MediaType.APPLICATION_JSON)
    public Response isJwtValid(ValidJWT validJWT) {

        Map<String, Boolean> map = new HashMap<>();
        try {
            if (this.servisKorisnik.isAuthorized(validJWT.getJwt(), "login/valid", "POST")) {
                map.put("valid", true);
                return Response.ok(map).build();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        map.put("valid", false);

        return Response.ok(map).build();
    }
}
