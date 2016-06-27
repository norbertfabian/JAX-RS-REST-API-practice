package cz.fabian.practice.jaxrs.server.result;

import cz.fabian.practice.jaxrs.server.model.Message;

import java.util.List;

/**
 * Created by nfabian on 24.6.16.
 */
public class MessageResult extends ServiceResult {

    private Message entity;
    private List<Message> entities;

    public MessageResult(Message entity) {
        this.entity = entity;
    }

    public MessageResult(List<Message> entities) {
        this.entities = entities;
    }

    public MessageResult(String errorMsg) {
        super(errorMsg);
    }

    public MessageResult(){}

    public Message getEntity() {
        return entity;
    }

    public List<Message> getEntities() {
        return entities;
    }
}
