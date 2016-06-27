package cz.fabian.practice.jaxrs.server.model;

/**
 * Created by nfabian on 22.6.16.
 */
public class Link {

    private String link;
    private String rel;

    public Link() {
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getRel() {
        return rel;
    }

    public void setRel(String rel) {
        this.rel = rel;
    }
}
