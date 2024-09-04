package rs.raf.raf_vodic_2;

import org.glassfish.jersey.internal.inject.AbstractBinder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;

import javax.ws.rs.ApplicationPath;

@ApplicationPath("/api")
public class AppConfigMain extends ResourceConfig {

    public AppConfigMain() {
        //  Ukljucujemo validaciju
        property(ServerProperties.BV_SEND_ERROR_IN_RESPONSE, true);

        AbstractBinder binder = new AbstractBinder() {
            @Override
            protected void configure() {
                //todo
//                this.bind()
            }
        };

        register(binder);

        //  Ucitavamo resurse
        packages("rs.raf.raf_vodic_2");
    }

}
