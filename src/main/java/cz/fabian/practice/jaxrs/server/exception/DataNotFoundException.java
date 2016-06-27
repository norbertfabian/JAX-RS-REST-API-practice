package cz.fabian.practice.jaxrs.server.exception;

/**
 * Created by nfabian on 24.6.16.
 */
public class DataNotFoundException extends RuntimeException {

    public DataNotFoundException(String message) {
        super(message);
    }
}
