package cz.fabian.practice.jaxrs.client;


import cz.fabian.practice.jaxrs.client.service.MessageService;
import cz.fabian.practice.jaxrs.server.model.Message;

import java.util.List;

/**
 * Created by nfabian on 26.6.16.
 */
public class RestApiClient {

    private static MessageService messageService = new MessageService();

    public static void main(String[] args) {
        Message message = messageService.get(1);
        System.out.println(message.getMessage());

        Message newMessage = new Message(100, "Message from client", "Fabian");
        message = messageService.create(newMessage);
        System.out.println(message.getMessage());

        List<Message> messages = messageService.getAll();
        for(Message m : messages) {
            System.out.println(m.getMessage());
        }
    }

}
