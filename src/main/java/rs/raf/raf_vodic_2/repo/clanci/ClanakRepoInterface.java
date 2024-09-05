package rs.raf.raf_vodic_2.repo.clanci;

import java.util.ArrayList;

public interface ClanakRepoInterface {

    //  Parametri su String, jer moze biti prazno polje (null), pa je ovo bolje resenje
    ArrayList<Clanak> getAllClanci(String numOfElmentsToReturn, String najcitaniji);

    ArrayList<Clanak> getAllClanciUnordered();

    Clanak getClanakById(Long id);

    Clanak addClanak(Clanak clanak);

    Clanak updateClanak(Clanak clanak);

    void deleteClanak(Long id);

    void incrementBrPoseta(Long id);

}
