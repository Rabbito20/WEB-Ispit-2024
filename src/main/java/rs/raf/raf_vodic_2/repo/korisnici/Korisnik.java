package rs.raf.raf_vodic_2.repo.korisnici;

import java.util.Objects;

public class Korisnik {

    private Long id;
    private String email;
    private String ime;
    private String prezime;
    private String password;                //  Hash value
    private TipKorisnika tipKorisnika;      //  Admin ili User
    private boolean status;                 //  Aktivan/Neaktivan

    public Korisnik() {
    }

    public Korisnik(Long id, String email, String ime, String prezime, String password, TipKorisnika tipKorisnika, boolean status) {
        this.id = id;
        this.email = email;
        this.ime = ime;
        this.prezime = prezime;
        this.tipKorisnika = tipKorisnika;
        this.status = status;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public TipKorisnika getTipKorisnika() {
        return tipKorisnika;
    }

    public void setTipKorisnika(String tipKorisnika) {
        if (Objects.equals(tipKorisnika, "ADMIN"))
            this.tipKorisnika = TipKorisnika.ADMIN;
        else
            this.tipKorisnika = TipKorisnika.USER;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Korisnik{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", ime='" + ime + '\'' +
                ", prezime='" + prezime + '\'' +
                ", password='" + password + '\'' +
                ", tipKorisnika=" + tipKorisnika +
                ", aktivan=" + status +
                '}';
    }
}
