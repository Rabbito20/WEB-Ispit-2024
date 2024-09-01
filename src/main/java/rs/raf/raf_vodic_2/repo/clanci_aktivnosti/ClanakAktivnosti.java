package rs.raf.raf_vodic_2.repo.clanci_aktivnosti;

public class ClanakAktivnosti {

    private Long clanakId;
    private Long aktivnostId;

    public ClanakAktivnosti() {
    }

    public ClanakAktivnosti(Long clanakId, Long aktivnostId) {
        this.clanakId = clanakId;
        this.aktivnostId = aktivnostId;
    }

    public Long getClanakId() {
        return clanakId;
    }

    public void setClanakId(Long clanakId) {
        this.clanakId = clanakId;
    }

    public Long getAktivnostId() {
        return aktivnostId;
    }

    public void setAktivnostId(Long aktivnostId) {
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
