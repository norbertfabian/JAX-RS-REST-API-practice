package cz.fabian.practice.jaxrs.server.database;

import cz.fabian.practice.jaxrs.server.model.Message;
import cz.fabian.practice.jaxrs.server.model.Profile;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by nfabian on 22.6.16.
 */
public class Database {

    private static Map<Long, Message> messages;
    static
    {
        messages = new HashMap<Long, Message>();
        messages.put(0L, new Message(0L, "1st message", "Fabian"));
        messages.put(1L, new Message(1L, "1st message", "Fabian"));
    }

    private static Map<String, Profile> profiles = new HashMap<String, Profile>();

    public static Map<Long, Message> getMessages() {
        return messages;
    }

    public static Map<String, Profile> getProfiles() {
        return profiles;
    }
}
