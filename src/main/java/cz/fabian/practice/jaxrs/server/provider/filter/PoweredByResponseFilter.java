package cz.fabian.practice.jaxrs.server.provider.filter;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

/**
 * Created by nfabian on 26.6.16.
 */

@Provider
public class PoweredByResponseFilter implements ContainerResponseFilter {
    @Override
    public void filter(ContainerRequestContext containerRequestContext,
                       ContainerResponseContext containerResponseContext) throws IOException {
        containerResponseContext.getHeaders().add("X-Powered-By", "Java Brains");

    }
}
