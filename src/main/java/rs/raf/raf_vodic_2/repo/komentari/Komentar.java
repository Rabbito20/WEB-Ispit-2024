package rs.raf.raf_vodic_2.repo.komentari;

public class Komentar {

    private Long id;
    private Long autorId;
    private String tekst;
    private String datumKreiranja;
    private Long clanakId;

    public Komentar() {
    }

    public Komentar(Long id, Long autorId, String tekst, String datumKreiranja, Long clanakId) {
        this.id = id;
        this.autorId = autorId;
        this.tekst = tekst;
        this.datumKreiranja = datumKreiranja;
        this.clanakId = clanakId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAutorId() {
        return autorId;
    }

    public void setAutorId(Long autorId) {
        this.autorId = autorId;
    }

    public String getTekst() {
        return tekst;
    }

    public void setTekst(String tekst) {
        this.tekst = tekst;
    }

    public String getDatumKreiranja() {
        return datumKreiranja;
    }

    public void setDatumKreiranja(String datumKreiranja) {
        this.datumKreiranja = datumKreiranja;
    }

    public Long getClanakId() {
        return clanakId;
    }

    public void setClanakId(Long clanakId) {
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
