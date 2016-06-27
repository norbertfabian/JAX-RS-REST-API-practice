package cz.fabian.practice.jaxrs.server.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Created by nfabian on 26.6.16.
 */

@Path("/auth")
@Produces(MediaType.TEXT_PLAIN)
public class SecuredResource {

    @GET
    @Path("message")
    public String getMessage() {
        return "Secured content!";
    }
}
