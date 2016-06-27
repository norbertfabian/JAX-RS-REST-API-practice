package cz.fabian.practice.jaxrs.server.service;

import cz.fabian.practice.jaxrs.server.database.Database;
import cz.fabian.practice.jaxrs.server.model.Comment;
import cz.fabian.practice.jaxrs.server.model.Message;
import cz.fabian.practice.jaxrs.server.result.CommentResult;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by nfabian on 27.6.16.
 */
public class CommentService {

    private Map<Long, Message> messages = Database.getMessages();

    public CommentService() {

    }

    public CommentResult getAll(long messageId) {
        Message message = messages.get(messageId);
        if (message == null) {
            return new CommentResult(CommentResult.MESSAGE_NOT_FOUND);
        }
        Map<Long, Comment> comments = message.getComments();
        return new CommentResult(new ArrayList<>(comments.values()));
    }

    public CommentResult get(long messageId, long commentId) {
        Message message = messages.get(messageId);
        if (message == null) {
            return new CommentResult(CommentResult.MESSAGE_NOT_FOUND);
        }
        Map<Long, Comment> comments = messages.get(messageId).getComments();
        Comment comment = comments.get(commentId);
        if (comment == null) {
            return new CommentResult(CommentResult.COMMENT_NOT_FOUND);
        }
        return new CommentResult(comment);
    }

    public CommentResult add(long messageId, Comment comment) {
        Map<Long, Comment> comments = messages.get(messageId).getComments();
        comment.setId(comments.size() + 1);
        comments.put(comment.getId(), comment);
        return new CommentResult(comment);
    }

    public CommentResult update(long messageId, Comment comment) {
        Map<Long, Comment> comments = messages.get(messageId).getComments();
        if (comment.getId() <= 0) {
            return null;
        }
        comments.put(comment.getId(), comment);
        return new CommentResult(comment);
    }

    public void remove(long messageId, long commentId) {
        Map<Long, Comment> comments = messages.get(messageId).getComments();
    }
}
