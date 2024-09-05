package rs.raf.raf_vodic_2.filters;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import java.io.IOException;

public class Cors implements ContainerResponseFilter {

    //  "Access-Control-Allow-Origin", "*"
    //  "Access-Control-Allow-Headers", "CSRF-Token, X-Requested-By, Authorization, Content-Type"
    //  "Access-Control-Allow-Credentials", "true"
    //  "Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD"
    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext response) throws IOException {
        response.getHeaders().add("Access-Control-Allow-Origin", "*");
        response.getHeaders().add("Access-Control-Allow-Headers", "CSRF-Token, X-Requested-By, Authorization, Content-Type");
        response.getHeaders().add("Access-Control-Allow-Credentials", "true");
        response.getHeaders().add("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD");
    }
}
