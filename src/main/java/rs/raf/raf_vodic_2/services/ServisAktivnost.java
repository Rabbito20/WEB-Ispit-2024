package rs.raf.raf_vodic_2.services;

import rs.raf.raf_vodic_2.repo.aktivnosti.Aktivnost;
import rs.raf.raf_vodic_2.repo.aktivnosti.AktivnostRepoInterface;

import javax.inject.Inject;
import java.util.List;

public class ServisAktivnost {
    @Inject
    private AktivnostRepoInterface aktivnostRepo;

    public List<Aktivnost> getAllAktivnosti() {
        return aktivnostRepo.getAllAktivnosti();
    }

    public Aktivnost getAktivnostById(Long id) {
        return aktivnostRepo.getAktivnostById(id);
    }

    public Aktivnost addAktivnost(Aktivnost aktivnost) {
        return aktivnostRepo.addAktivnost(aktivnost);
    }

    public Aktivnost getAktivnostByName(String name) {
        return aktivnostRepo.getAktivnostByName(name);
    }

    //  TODO:   update i delete
//    public Aktivnost updateAktivnost(Aktivnost aktivnost) {return aktivnostRepo.updateAktivnost(aktivnost);}
//    public void deleteAktivnost(Long id) {aktivnostRepo.deleteAktivnost(id);}
}
