package cz.fabian.practice.jaxrs.server.resource;

import cz.fabian.practice.jaxrs.server.exception.DataNotFoundException;
import cz.fabian.practice.jaxrs.server.model.Comment;
import cz.fabian.practice.jaxrs.server.result.CommentResult;
import cz.fabian.practice.jaxrs.server.service.CommentService;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;

/**
 * Created by nfabian on 27.6.16.
 */

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CommentResource {

    private CommentService commentService = new CommentService();

    @GET
    @Path("/{commentId}")
    public Response getComment(@PathParam("messageId") long messageId,
                               @PathParam("commentId") long commentId,
                               @Context UriInfo uriInfo) {
        CommentResult result = commentService.get(messageId, commentId);
        hasError(result, messageId, commentId);
        return Response.created(uriInfo.getAbsolutePath())
                .entity(result.getEntity())
                .status(Response.Status.OK)
                .build();
    }

    @GET
    public Response getAllComments(@PathParam("messageId") long messageId,
                                   @Context UriInfo uriInfo) {
        CommentResult result = commentService.getAll(messageId);
        hasMessageError(result, messageId);
        return Response.created(uriInfo.getAbsolutePath())
                .entity(result.getEntities())
                .status(Response.Status.OK)
                .build();

    }

    @POST
    public Response addComment(@PathParam("messageId") long messageId,
                               @Context UriInfo uriInfo,
                               Comment comment) {
        CommentResult result = commentService.add(messageId, comment);
        hasMessageError(result, messageId);
        URI uri = uriInfo.getAbsolutePathBuilder()
                .path(String.valueOf(result.getEntity().getId()))
                .build();
        return Response.created(uri)
                .entity(result.getEntity())
                .status(Response.Status.CREATED)
                .build();
    }

    @PUT
    @Path("/{commentId}")
    public Response updateComment(@PathParam("messageId") long messageId,
                                  @PathParam("commentId") long commentId,
                                  @Context UriInfo uriInfo,
                                 Comment comment) {
        comment.setId(commentId);
        CommentResult result = commentService.update(messageId, comment);
        hasError(result, messageId, commentId);
        return Response.created(uriInfo.getAbsolutePath())
                .status(Response.Status.OK)
                .entity(result.getEntity())
                .build();
    }

    @DELETE
    @Path("/{commentId}")
    public Response deleteComment(@PathParam("messageId") long messageId,
                                  @PathParam("commentId") long commentId) {
        return Response.status(Response.Status.NO_CONTENT).build();
    }

    private void hasError(CommentResult result, long messageId, long commentId) {
        if(result.isError()) {
            handleMessageError(result, messageId);
            handleCommentError(result, commentId);
        }
    }

    private void hasMessageError(CommentResult result, long messageId) {
        if(result.isError()) {
            handleMessageError(result, messageId);
        }
    }

    private void handleMessageError(CommentResult result, long messageId) {
        if (result.getErrorMsg().equals(CommentResult.MESSAGE_NOT_FOUND))
            throw new DataNotFoundException("Message with id " + messageId + " not found.");
    }

    private void handleCommentError(CommentResult result, long commentId) {
        if (result.getErrorMsg().equals(CommentResult.COMMENT_NOT_FOUND))
            throw new DataNotFoundException("Comment with id " + commentId + " not found.");
    }

}
