package rs.raf.raf_vodic_2.repo.komentari;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class Komentar {

    private Long id;

    @NotNull
    @NotEmpty
    private Long autorId;

    @NotNull
    @NotEmpty
    private String tekst;

    private LocalDate datumKreiranja;
    private Long clanakId;

    public Komentar(Long id, Long autorId, String tekst, LocalDate datumKreiranja, Long clanakId) {
        this.id = id;
        this.autorId = autorId;
        this.tekst = tekst;
        this.datumKreiranja = datumKreiranja;
        this.clanakId = clanakId;
    }

    @Override
    public String toString() {
        return "Komentar{" +
                "id=" + id +
                ", autorId=" + autorId +
                ", tekst='" + tekst + '\'' +
                ", datumKreiranja='" + datumKreiranja + '\'' +
                ", clanakId=" + clanakId +
                '}';
    }
}
