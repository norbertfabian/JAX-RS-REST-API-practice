package cz.fabian.practice.jaxrs.server.result;

import cz.fabian.practice.jaxrs.server.model.Comment;

import java.util.List;

/**
 * Created by nfabian on 24.6.16.
 */
public class CommentResult extends ServiceResult {

    public static final String MESSAGE_NOT_FOUND = "Service.result.model.messageNotFound";
    public static final String COMMENT_NOT_FOUND = "Service.result.model.commentNotFound";

    private Comment entity;
    private List<Comment> entities;

    public CommentResult(Comment entity) {
        this.entity = entity;
    }

    public CommentResult(List<Comment> entities) {
        this.entities = entities;
    }

    public CommentResult(String errorMsg) {
        super(errorMsg);
    }

    public Comment getEntity() {
        return entity;
    }

    public List<Comment> getEntities() {
        return entities;
    }
}
