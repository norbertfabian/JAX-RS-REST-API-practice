package cz.fabian.practice.jaxrs.server;

import cz.fabian.practice.jaxrs.server.resource.DateResource;
import cz.fabian.practice.jaxrs.server.resource.MessageResource;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

/**
 * This class is used for running the application without a servlet
 * Created by nfabian on 26.6.16.
 */
@ApplicationPath("application/webapi")
public class MyApp extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> s = new HashSet<Class<?>>();
        s.add(DateResource.class);
        s.add(MessageResource.class);
        return s;
    }
}

