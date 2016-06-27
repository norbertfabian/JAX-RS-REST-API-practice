package cz.fabian.practice.jaxrs.server.resource;

import cz.fabian.practice.jaxrs.server.model.MyDate;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by nfabian on 26.6.16.
 */

@Path("/date")
@Produces(MediaType.TEXT_PLAIN)
public class DateResource {

    @GET
    public Date getActualDate() {
        return Calendar.getInstance().getTime();
    }

    @GET
    @Path("/{DateString}")
    public String getDate(@PathParam("DateString") MyDate date) {
        return date.toString();
    }
}
