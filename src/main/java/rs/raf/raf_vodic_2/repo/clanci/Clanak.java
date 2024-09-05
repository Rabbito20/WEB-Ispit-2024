package rs.raf.raf_vodic_2.repo.clanci;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rs.raf.raf_vodic_2.repo.aktivnosti.Aktivnost;
import rs.raf.raf_vodic_2.repo.destinacije.Destinacija;
import rs.raf.raf_vodic_2.repo.komentari.Komentar;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;

@Getter
@Setter
@NoArgsConstructor
public class Clanak {

    private Long id;

    @NotNull
    @NotEmpty
    private String naslov;

    @NotNull
    @NotEmpty
    private String tekst;

    @NotNull
    @NotEmpty
    private Long autorId;   //  ID korisnika

    @NotNull
    @NotEmpty
    private Long destinacijaId;

    private int brojPoseta;

    private LocalDate datumKreiranja;

    private ArrayList<Komentar> komentari;
    private ArrayList<Aktivnost> listaAktivnosti;
    private Destinacija destinacija;

    public Clanak(Long id, String naslov, String tekst, Long autorId, Long destinacijaId, int brojPoseta, LocalDate datumKreiranja) {
        this.id = id;
        this.naslov = naslov;
        this.tekst = tekst;
        this.autorId = autorId;
        this.destinacijaId = destinacijaId;
        this.brojPoseta = brojPoseta;
        this.datumKreiranja = datumKreiranja;
    }

    public Clanak(Long id, String naslov, String tekst, Long autorId, Long destinacijaId, int brojPoseta, LocalDate datumKreiranja, Destinacija destinacija) {
        this.id = id;
        this.naslov = naslov;
        this.tekst = tekst;
        this.autorId = autorId;
        this.destinacijaId = destinacijaId;
        this.brojPoseta = brojPoseta;
        this.datumKreiranja = datumKreiranja;
        this.destinacija = destinacija;
    }

    @Override
    public String toString() {
        return "Clanak{" +
                "id=" + id +
                ", naslov='" + naslov + '\'' +
                ", tekst='" + tekst + '\'' +
                ", autorId=" + autorId +
                ", destinacijaId=" + destinacijaId +
                ", brojPoseta=" + brojPoseta +
                ", datumKreiranja=" + datumKreiranja +
                ", komentari=" + komentari +
                ", listaAktivnosti=" + listaAktivnosti +
                ", destinacija=" + destinacija +
                '}';
    }
}
