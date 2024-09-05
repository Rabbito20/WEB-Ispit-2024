package rs.raf.raf_vodic_2;

import org.glassfish.jersey.internal.inject.AbstractBinder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;
import rs.raf.raf_vodic_2.repo.aktivnosti.AktivnostDAO;
import rs.raf.raf_vodic_2.repo.aktivnosti.AktivnostRepoInterface;
import rs.raf.raf_vodic_2.repo.korisnici.KorisnikDAO;
import rs.raf.raf_vodic_2.repo.korisnici.KorisnikRepoInterface;
import rs.raf.raf_vodic_2.services.ServisAktivnost;
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


                this.bindAsContract(ServisKorisnik.class);
                this.bindAsContract(ServisAktivnost.class);

                //todo: Destinacija
                //todo: Clanak
                //todo: Clanak sa aktivnostima
            }
        };

        register(binder);

        //  Ucitavamo resurse
        packages("rs.raf.raf_vodic_2");
    }

}
