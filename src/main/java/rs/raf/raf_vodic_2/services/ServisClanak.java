package rs.raf.raf_vodic_2.services;

import rs.raf.raf_vodic_2.repo.clanci.Clanak;
import rs.raf.raf_vodic_2.repo.clanci.ClanakRepoInterface;

import javax.inject.Inject;
import javax.ws.rs.core.UriInfo;
import java.util.ArrayList;

public class ServisClanak {

    @Inject
    private ClanakRepoInterface clanakRepo;

//    @Inject
//    private ClanakAktivnostRepoInterface clanakAktivnostRepo; //  TODO

//    @Inject
//    private KomentarRepoInterface komentarRepo;               //  TODO

    public ArrayList<Clanak> getAllClanci(UriInfo uriInfo) {
        String numOfElemTOReturn = uriInfo.getQueryParameters().getFirst("top");
        String najcitanije = uriInfo.getQueryParameters().getFirst("mostRead");
        return clanakRepo.getAllClanci(numOfElemTOReturn, najcitanije);
    }

    public Clanak getClanakById(Long id) {
        Clanak clanak = clanakRepo.getClanakById(id);
//        TODO: izdvojiti aktivnosti iz clanka.
//        TODO: izdvojiti komentare iz clanka.

        return clanak;
    }

    public Clanak addClanak(Clanak clanak) {
        clanakRepo.addClanak(clanak);

        //  TODO:   Dodaj aktivnosti iz clanka.

        return clanak;
    }

    public void deleteClanak(Long id) {
        clanakRepo.deleteClanak(id);
    }

    public Clanak updateClanak(Clanak clanak) {
        return clanakRepo.updateClanak(clanak);
    }

    public void incrementBrPoseta(Long id) {
        clanakRepo.incrementBrPoseta(id);
    }

    //  TODO:   ADD Komentar.

}
