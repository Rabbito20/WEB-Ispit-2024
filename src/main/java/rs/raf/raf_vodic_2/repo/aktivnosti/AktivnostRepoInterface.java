package rs.raf.raf_vodic_2.repo.aktivnosti;

import java.util.ArrayList;
import java.util.List;

public interface AktivnostRepoInterface {

    List<Aktivnost> getAllAktivnosti();

    Aktivnost addAktivnost(Aktivnost aktivnost);

    Aktivnost updateAktivnost(Aktivnost aktivnost);

    Aktivnost getAktivnostById(Long id);

    Aktivnost getAktivnostByName(String ime);

    void deleteAktivnost(Long id);

}
