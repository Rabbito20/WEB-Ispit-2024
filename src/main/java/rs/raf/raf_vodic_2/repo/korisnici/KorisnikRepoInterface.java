package rs.raf.raf_vodic_2.repo.korisnici;

import java.util.ArrayList;

public interface KorisnikRepoInterface {
    ArrayList<Korisnik> getAllKorisnici();

    Korisnik getKorisnikByEmail(String email);
//    Korisnik getKorisnikByEmail(String email, boolean takePassword);

    Korisnik getKorisnikById(Long id);

    Korisnik addKorisnik(Korisnik korisnik);

    void deleteKorisnik(Long id);

    Korisnik updateKorisnik(Korisnik korisnik);

}
