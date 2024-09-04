package rs.raf.raf_vodic_2.repo.aktivnosti;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rs.raf.raf_vodic_2.repo.clanci.Clanak;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;

@Setter
@Getter
@NoArgsConstructor
public class Aktivnost {

    private Long id;

    @NotNull
    @NotEmpty
    private String naziv;

    ArrayList<Clanak> listaClanaka;

    public Aktivnost(Long id, String naziv) {
        this.id = id;
        this.naziv = naziv;
    }

    @Override
    public String toString() {
        return "Aktivnost{" +
                "id=" + id +
                ", naziv='" + naziv + '\'' +
                '}';
    }
}
