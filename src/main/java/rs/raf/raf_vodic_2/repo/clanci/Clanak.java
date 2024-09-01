package rs.raf.raf_vodic_2.repo.clanci;

public class Clanak {

    private Long id;
    private String naslov;
    private String tekst;
    private Long autorId;   //  ID korisnika
    private Long destinacijaId;
    private int brojPoseta;
    private String datumKreiranja;

    public Clanak() {
    }

    public Clanak(Long id, String naslov, String tekst, Long autorId, Long destinacijaId, int brojPoseta, String datumKreiranja) {
        this.id = id;
        this.naslov = naslov;
        this.tekst = tekst;
        this.autorId = autorId;
        this.destinacijaId = destinacijaId;
        this.brojPoseta = brojPoseta;
        this.datumKreiranja = datumKreiranja;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNaslov() {
        return naslov;
    }

    public void setNaslov(String naslov) {
        this.naslov = naslov;
    }

    public String getTekst() {
        return tekst;
    }

    public void setTekst(String tekst) {
        this.tekst = tekst;
    }

    public Long getAutorId() {
        return autorId;
    }

    public void setAutorId(Long autorId) {
        this.autorId = autorId;
    }

    public Long getDestinacijaId() {
        return destinacijaId;
    }

    public void setDestinacijaId(Long destinacijaId) {
        this.destinacijaId = destinacijaId;
    }

    public int getBrojPoseta() {
        return brojPoseta;
    }

    public void setBrojPoseta(int brojPoseta) {
        this.brojPoseta = brojPoseta;
    }

    public String getDatumKreiranja() {
        return datumKreiranja;
    }

    public void setDatumKreiranja(String datumKreiranja) {
        this.datumKreiranja = datumKreiranja;
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
                ", datumKreiranja='" + datumKreiranja + '\'' +
                '}';
    }
}
