package rs.raf.raf_vodic_2.repo.destinacije;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rs.raf.raf_vodic_2.repo.clanci.Clanak;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class Destinacija {
    private Long id;
    @NotNull
    @NotEmpty
    private String naziv;

    @NotNull
    @NotEmpty
    private String opis;

    private List<Clanak> clanakList;

    public Destinacija(Long id, String naziv, String opis) {
        this.id = id;
        this.naziv = naziv;
        this.opis = opis;
    }

    public Destinacija(Long id, String naziv, String opis, List<Clanak> clanakList) {
        this.id = id;
        this.naziv = naziv;
        this.opis = opis;
        this.clanakList = clanakList;
    }

    @Override
    public String toString() {
        return "Destinacija{" +
                "id=" + id +
                ", naziv='" + naziv + '\'' +
                ", opis='" + opis + '\'' +
                '}';
    }
}
