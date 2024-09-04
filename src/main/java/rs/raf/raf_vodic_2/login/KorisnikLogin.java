package rs.raf.raf_vodic_2.login;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class KorisnikLogin {
    @NotNull(message = "EMAIL ne moze biti null!")
    @NotEmpty(message = "EMAIL ne moze biti prazno polje!")
    private String email;

    @NotNull(message = "SIFRA ne moze biti null!")
    @NotEmpty(message = "SIFRA ne moze biti prazno polje!")
    private String password;
}
