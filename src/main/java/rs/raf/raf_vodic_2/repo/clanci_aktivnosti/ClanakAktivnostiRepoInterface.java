package rs.raf.raf_vodic_2.repo.clanci_aktivnosti;

import rs.raf.raf_vodic_2.repo.aktivnosti.Aktivnost;

import java.util.ArrayList;
import java.util.List;

public interface ClanakAktivnostiRepoInterface {

    ArrayList<Aktivnost> getAllAktivnostiForClanakId(Long clanakId);

    void addAllConnections(Long clanakId, List<Long> listaIdAktivnosti);

}
