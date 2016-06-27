package cz.fabian.practice.jaxrs.server.model;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.*;

/**
 * Created by nfabian on 22.6.16.
 */

@XmlRootElement
public class Message {

    private long id;
    private String message;
    private Date created;
    private String author;
    private Map<Long, Comment> comments;
    private List<Link> links;

    public Message() {
        this.comments = new HashMap<Long, Comment>();
        this.links = new ArrayList<Link>();
    }

    public Message(long id, String message, String author) {
        this.comments = new HashMap<Long, Comment>();
        this.links = new ArrayList<Link>();
        this.id = id;
        this.message = message;
        this.author = author;
        this.created = new Date();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Map<Long, Comment> getComments() {
        return comments;
    }

    public void setComments(Map<Long, Comment> comments) {
        this.comments = comments;
    }

    public List<Link> getLinks() {
        return links;
    }

    public void setLinks(List<Link> links) {
        this.links = links;
    }

    public Message update(Message message) {
        this.id = message.id;
        this.message = message.getMessage();
        this.created = message.getCreated();
        this.author = message.getAuthor();
        this.comments = message.getComments();
        this.links = message.getLinks();
        return this;
    }
}
