package rs.raf.raf_vodic_2.repo.komentari;

import java.util.ArrayList;

public interface KomentarRepoInterface {
    ArrayList<Komentar> getAllKomentariForClanakById(Long clanakId);

    Komentar addKomentar(Komentar komentar);

    void deleteKomentar(Long id);
}
