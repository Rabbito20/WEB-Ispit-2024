package rs.raf.raf_vodic_2.repo.aktivnosti;

public class Aktivnost {

    private Long id;
    private String naziv;

    public Aktivnost() {}

    public Aktivnost(Long id, String naziv) {
        this.id = id;
        this.naziv = naziv;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
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
