package rs.raf.raf_vodic_2.repo.destinacije;

import java.util.List;

public interface DestinacijaRepoInterfejs {

    Destinacija addDestinacija(Destinacija destinacija);
    Destinacija getDestinacijaById(Long id);
    List<Destinacija> getAllDestinacije();
    Destinacija updateDestinacija(Destinacija destinacija);
    void deleteDestinacija(Long id);

}
