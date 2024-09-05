package rs.raf.raf_vodic_2;

import org.glassfish.jersey.internal.inject.AbstractBinder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;
import rs.raf.raf_vodic_2.repo.aktivnosti.AktivnostDAO;
import rs.raf.raf_vodic_2.repo.aktivnosti.AktivnostRepoInterface;
import rs.raf.raf_vodic_2.repo.clanci.ClanakDAO;
import rs.raf.raf_vodic_2.repo.clanci.ClanakRepoInterface;
import rs.raf.raf_vodic_2.repo.clanci_aktivnosti.ClanakAktivnostiDAO;
import rs.raf.raf_vodic_2.repo.clanci_aktivnosti.ClanakAktivnostiRepoInterface;
import rs.raf.raf_vodic_2.repo.destinacije.DestinacijaDAO;
import rs.raf.raf_vodic_2.repo.destinacije.DestinacijaRepoInterfejs;
import rs.raf.raf_vodic_2.repo.komentari.KomentarDAO;
import rs.raf.raf_vodic_2.repo.komentari.KomentarRepoInterface;
import rs.raf.raf_vodic_2.repo.korisnici.KorisnikDAO;
import rs.raf.raf_vodic_2.repo.korisnici.KorisnikRepoInterface;
import rs.raf.raf_vodic_2.services.ServisAktivnost;
import rs.raf.raf_vodic_2.services.ServisClanak;
import rs.raf.raf_vodic_2.services.ServisDestinacije;
import rs.raf.raf_vodic_2.services.ServisKorisnik;

import javax.inject.Singleton;
import javax.ws.rs.ApplicationPath;

@ApplicationPath("/api")
public class AppConfigMain extends ResourceConfig {

    public AppConfigMain() {
        //  Ukljucujemo validaciju
        property(ServerProperties.BV_SEND_ERROR_IN_RESPONSE, true);

        AbstractBinder binder = new AbstractBinder() {
            @Override
            protected void configure() {
                this.bind(KorisnikDAO.class).to(KorisnikRepoInterface.class).in(Singleton.class);
                this.bind(AktivnostDAO.class).to(AktivnostRepoInterface.class).in(Singleton.class);
                this.bind(DestinacijaDAO.class).to(DestinacijaRepoInterfejs.class).in(Singleton.class);
                this.bind(ClanakDAO.class).to(ClanakRepoInterface.class).in(Singleton.class);
                this.bind(ClanakAktivnostiDAO.class).to(ClanakAktivnostiRepoInterface.class).in(Singleton.class);
                this.bind(KomentarDAO.class).to(KomentarRepoInterface.class).in(Singleton.class);

                this.bindAsContract(ServisKorisnik.class);
                this.bindAsContract(ServisAktivnost.class);
                this.bindAsContract(ServisDestinacije.class);
                this.bindAsContract(ServisClanak.class);
            }
        };
        register(binder);

        //  Ucitavamo resurse
        packages("rs.raf.raf_vodic_2");
    }
}
