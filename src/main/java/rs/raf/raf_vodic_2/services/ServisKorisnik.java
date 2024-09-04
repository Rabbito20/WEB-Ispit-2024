package rs.raf.raf_vodic_2.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import rs.raf.raf_vodic_2.filters.Global;
import rs.raf.raf_vodic_2.repo.korisnici.Korisnik;
import rs.raf.raf_vodic_2.repo.korisnici.KorisnikRepoInterface;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Date;

public class ServisKorisnik {

    @Inject
    KorisnikRepoInterface korisnikRepo;

    private static final Algorithm algoritam = Algorithm.HMAC256("help");

    public String login(String email, String password) {
        Korisnik korisnik = this.korisnikRepo.getKorisnikByEmail(email);
        String hashedPassword = Global.hashPassword(password);

        if (korisnik == null || !korisnik.getPassword().equals(hashedPassword)) return null;

        Date issuedAt = new Date();
        Date expiresAt = new Date(issuedAt.getTime() + 24 * 60 * 60 * 1000);

        //  Mora biti jedinstven!!!
        return JWT.create()
                .withIssuedAt(issuedAt)
                .withExpiresAt(expiresAt)
                .withSubject(email)
//                .withClaim("tip_korisnika", korisnik.getTipKorisnika()) //  HMMMMMMM
                .sign(algoritam);
    }

    public boolean isAuthorized(String token, String path, String method) {

        try {
            JWTVerifier verifier = JWT.require(algoritam).build();
            DecodedJWT decodedJWT = verifier.verify(token);     //  Baca exception!

            String email = decodedJWT.getSubject();
            String tipKorisnika = String.valueOf(decodedJWT.getClaim("tip_korisnika"));
            tipKorisnika = tipKorisnika.substring(1, tipKorisnika.length() - 1);    //  "ADMIN", remove

            return this.korisnikRepo.getKorisnikByEmail(email) != null && validRole(path, tipKorisnika, method);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


    //  Za path "/api/korsinik"
    // TODO:  Napravi rutu kad je ulogovan "/api/korisnik/..." (u kontroleru)
    private boolean validRole(String path, String tipKorisnika, String method) {
        if (path.startsWith("user")) return tipKorisnika.equals("ADMIN");

        return true;
    }

    public Korisnik getKorisnikByEmail(String email) {
        return korisnikRepo.getKorisnikByEmail(email);
    }

    public Korisnik addKorisnik(Korisnik korisnik) {
        return korisnikRepo.addKorisnik(korisnik);
    }

    public ArrayList<Korisnik> getAllKorisnici() {
        return korisnikRepo.getAllKorisnici();
    }

    public Korisnik getKorisnikById(Long id) {
        return korisnikRepo.getKorisnikById(id);
    }

    public void deleteKorsinik(Long id) {
        korisnikRepo.deleteKorisnik(id);
    }

    public Korisnik updateKorisnik(Korisnik korisnik) {
        return korisnikRepo.updateKorisnik(korisnik);
    }

}
