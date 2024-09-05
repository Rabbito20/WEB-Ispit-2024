package rs.raf.raf_vodic_2.services;

import rs.raf.raf_vodic_2.repo.destinacije.Destinacija;
import rs.raf.raf_vodic_2.repo.destinacije.DestinacijaRepoInterfejs;

import javax.inject.Inject;
import java.util.List;

public class ServisDestinacije {

    @Inject
    private DestinacijaRepoInterfejs destinacijaRepo;

    public List<Destinacija> getAllDestinacije() {
        return destinacijaRepo.getAllDestinacije();
    }

    public Destinacija getDestinacijaById(Long id) {
        return destinacijaRepo.getDestinacijaById(id);
    }

    public Destinacija addDestinacija(Destinacija destinacija) {
        return destinacijaRepo.addDestinacija(destinacija);
    }

    public Destinacija updateDestinacija(Destinacija destinacija) {
        return destinacijaRepo.updateDestinacija(destinacija);
    }

    public void deleteDestinacija(Long id) {
        destinacijaRepo.deleteDestinacija(id);
    }
}
