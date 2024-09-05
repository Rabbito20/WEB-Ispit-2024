package rs.raf.raf_vodic_2.services;

import rs.raf.raf_vodic_2.repo.aktivnosti.Aktivnost;
import rs.raf.raf_vodic_2.repo.clanci.Clanak;
import rs.raf.raf_vodic_2.repo.clanci.ClanakRepoInterface;
import rs.raf.raf_vodic_2.repo.clanci_aktivnosti.ClanakAktivnostiRepoInterface;
import rs.raf.raf_vodic_2.repo.komentari.Komentar;
import rs.raf.raf_vodic_2.repo.komentari.KomentarRepoInterface;

import javax.inject.Inject;
import javax.ws.rs.core.UriInfo;
import java.util.ArrayList;
import java.util.List;

public class ServisClanak {

    @Inject
    private ClanakRepoInterface clanakRepo;

    @Inject
    private ClanakAktivnostiRepoInterface clanakAktivnostRepo;

    @Inject
    private KomentarRepoInterface komentarRepo;

    public ArrayList<Clanak> getAllClanci(UriInfo uriInfo) {
        String numOfElemTOReturn = uriInfo.getQueryParameters().getFirst("top");
        String najcitanije = uriInfo.getQueryParameters().getFirst("mostRead");
        return clanakRepo.getAllClanci(numOfElemTOReturn, najcitanije);
    }

    public Clanak getClanakById(Long id) {
        Clanak clanak = clanakRepo.getClanakById(id);
        clanak.setListaAktivnosti(clanakAktivnostRepo.getAllAktivnostiForClanakId(id));

//        TODO: izdvojiti komentare iz clanka.

        return clanak;
    }

    public Clanak addClanak(Clanak clanak) {
        clanakRepo.addClanak(clanak);

        //  Dodajemo aktivnosti iz clanka.
        if (clanak.getListaAktivnosti() != null) {
            List<Long> idList = new ArrayList<>();
            for (Aktivnost aktivnost : clanak.getListaAktivnosti())
                idList.add(aktivnost.getId());
            clanakAktivnostRepo.addAllConnections(clanak.getId(), idList);
        }

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
    
    public Komentar addKomentar(Komentar komentar) {
        return komentarRepo.addKomentar(komentar);
    }

}
