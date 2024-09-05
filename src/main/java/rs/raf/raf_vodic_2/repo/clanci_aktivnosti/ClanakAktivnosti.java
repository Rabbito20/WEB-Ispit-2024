package rs.raf.raf_vodic_2.repo.clanci_aktivnosti;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Setter
@Getter
@NoArgsConstructor
public class ClanakAktivnosti {

    private Long id;     //  Nesto sam zeznuo sa bazom i ovo je int

    @NotNull
    private Long clanakId;

    @NotNull
    private Long aktivnostId;

    public ClanakAktivnosti(Long id, Long clanakId, Long aktivnostId) {
        this.id = id;
        this.clanakId = clanakId;
        this.aktivnostId = aktivnostId;
    }

    @Override
    public String toString() {
        return "ClanakAktivnosti{" +
                "clanakId=" + clanakId +
                ", aktivnostId=" + aktivnostId +
                '}';
    }
}
