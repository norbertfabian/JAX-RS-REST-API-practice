package cz.fabian.practice.jaxrs.server.service;

import cz.fabian.practice.jaxrs.server.database.Database;
import cz.fabian.practice.jaxrs.server.model.Message;
import cz.fabian.practice.jaxrs.server.result.MessageResult;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

/**
 * Created by nfabian on 22.6.16.
 */
public class MessageService {

    private Map<Long, Message> messages = Database.getMessages();
    private long messageId = messages.size();

    public MessageService() {
    }

    public MessageResult getAll() {
        return new MessageResult(new ArrayList<Message>(messages.values()));
    }

    public MessageResult add(Message message) {
        message.setId(messageId++);
        messages.put(message.getId(), message);
        return new MessageResult(message);
    }

    public MessageResult update(Message message) {
        Message persistedMessage = messages.get(message.getId());
        if (persistedMessage == null) {
            return new MessageResult(MessageResult.DATA_NOT_FOUND);
        }
        return new MessageResult(persistedMessage.update(message));
    }

    public MessageResult delete(long id) {
        if (!messages.containsKey(id))
            return new MessageResult(MessageResult.DATA_NOT_FOUND);
        messages.remove(id);
        return new MessageResult();
    }

    public MessageResult get(long id) {
        Message message = messages.get(id);
        if (message == null) {
            return new MessageResult(MessageResult.DATA_NOT_FOUND);
        }
        return new MessageResult(message);
    }

    public MessageResult get(int startId, int size) {
        ArrayList<Message> result = new ArrayList<Message>(messages.values());
        if (startId + size <= messages.size()) {
            return new MessageResult(result.subList(startId, startId + size));
        }
        return new MessageResult(MessageResult.OUT_OF_RANGE);
    }

    public MessageResult getAllForYear(int year) {
        List<Message> result = new ArrayList<Message>();
        Calendar cal = Calendar.getInstance();
        for (Message message : messages.values()) {
            cal.setTime(message.getCreated());
            if (cal.get(Calendar.YEAR) == year) {
                result.add(message);
            }
        }
        return new MessageResult(result);
    }
}
