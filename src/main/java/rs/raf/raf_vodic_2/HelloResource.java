package rs.raf.raf_vodic_2;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("/hello-world-test")
public class HelloResource {

    @GET
    @Produces("text/plain")
    public String hello() {
        return "Hello, app start!";
    }
}
