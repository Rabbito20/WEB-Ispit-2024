package rs.raf.raf_vodic_2.repo.korisnici;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Getter
@NoArgsConstructor
public class Korisnik {

    @Setter
    private Long id;

    @Setter
    @NotNull(message = "Ne sme biti null!")
    @NotEmpty(message = "Ne sme biti prazno!")
    @Email
    private String email;

    @Setter
    @NotNull
    private String ime;

    @Setter
    @NotNull
    private String prezime;

    @Setter
    @NotNull(message = "Ne sme biti null!")
    @NotEmpty(message = "Ne sme biti prazno!")
    private String password;                //  Hash value

    @NotNull
    private TipKorisnika tipKorisnika;      //  Admin ili User

    @Setter
    @NotNull
    private boolean status;                 //  Aktivan/Neaktivan

    /**
     * Kreira Korisnika (bez passworda)
     */
    public Korisnik(Long id, String email, String ime, String prezime, TipKorisnika tipKorisnika, boolean status) {
        this.id = id;
        this.email = email;
        this.ime = ime;
        this.prezime = prezime;
        this.tipKorisnika = tipKorisnika;
        this.status = status;
    }

    /**
     * Kreira Korisnika (sa passwordom)
     */
    public Korisnik(Long id, String email, String ime, String prezime, String password, TipKorisnika tipKorisnika, boolean status) {
        this.id = id;
        this.email = email;
        this.ime = ime;
        this.prezime = prezime;
        this.tipKorisnika = tipKorisnika;
        this.status = status;
        this.password = password;
    }

    public void setTipKorisnika(String tipKorisnika) {
        if (Objects.equals(tipKorisnika.toLowerCase(), "ADMIN".toLowerCase()))
            this.tipKorisnika = TipKorisnika.ADMIN;
        else
            this.tipKorisnika = TipKorisnika.USER;
    }

    @Override
    public String toString() {
        return "Korisnik{" +
                "id:" + id +
                ", email:'" + email + '\'' +
                ", ime:'" + ime + '\'' +
                ", prezime:'" + prezime + '\'' +
                ", password:'" + password + '\'' +
                ", tipKorisnika:" + tipKorisnika +
                ", aktivan:" + status +
                '}';
    }
}
