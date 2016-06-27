package cz.fabian.practice.jaxrs.client.service;

import cz.fabian.practice.jaxrs.server.model.Message;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by nfabian on 26.6.16.
 */
public class MessageService {

    private Client client = ClientBuilder.newClient();

    private WebTarget baseTarget = client.target("http://localhost:8080/webapi/");
    private WebTarget messagesTarget = baseTarget.path("messages");
    private WebTarget singleMessageTarget = messagesTarget.path("{messageId}");


    public Message get(long id) {
        Response response = singleMessageTarget
                .resolveTemplate("messageId", id)
                .request(MediaType.APPLICATION_JSON)
                .get();
        //      .buildGet() - if you want to get the invocation to invoke later

        return response.readEntity(Message.class);
    }

    public List<Message> getAll() {
        Response response = messagesTarget
                .request(MediaType.APPLICATION_JSON)
                .get();

        return  response.readEntity(new GenericType<List<Message>>() {});
    }

    public List<Message> getAllForYear(int year) {
        Response response = messagesTarget
                .queryParam("year", year)
                .request(MediaType.APPLICATION_JSON)
                .get();

        return  response.readEntity(new GenericType<List<Message>>() {});
    }

    public Message create(Message message) {
        Response response = messagesTarget
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.json(message));

        return response.readEntity(Message.class);
    }
}
