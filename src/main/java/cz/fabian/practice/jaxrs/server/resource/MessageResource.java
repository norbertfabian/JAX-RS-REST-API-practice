package cz.fabian.practice.jaxrs.server.resource;

import java.net.URI;

import cz.fabian.practice.jaxrs.server.exception.DataNotFoundException;
import cz.fabian.practice.jaxrs.server.model.Message;
import cz.fabian.practice.jaxrs.server.resource.bean.MessageFilterBean;
import cz.fabian.practice.jaxrs.server.result.MessageResult;
import cz.fabian.practice.jaxrs.server.service.MessageService;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

/**
 * Created by nfabian on 24.6.16.
 */

//@Singleton Uncomment and import the javax.inject.Singleton in case you want the resource to be a singleton
@Path("/messages")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MessageResource {

    MessageService messageService = new MessageService();

    @GET
    public Response getMessages(@BeanParam MessageFilterBean filterBean, @Context UriInfo uriInfo) {
        if (filterBean.getYear() > 0) {
            return getMessagesForYear(filterBean.getYear(), uriInfo);
        }
        if (filterBean.getStart() >= 0 && filterBean.getSize() > 0) {
            return getMessagesPaginated(filterBean.getStart(), filterBean.getSize(), uriInfo);
        }
        MessageResult result = messageService.getAll();
        return Response.created(uriInfo.getAbsolutePath())
                .entity(result.getEntities())
                .status(Response.Status.OK)
                .build();
    }

    @GET
    @Path("/{messageId}")
    public Response getMessage(@PathParam("messageId") long id, @Context UriInfo uriInfo) {
        MessageResult result = messageService.get(id);
        hasError(result, id);
        return Response.created(uriInfo.getAbsolutePath())
                .entity(result.getEntity())
                .status(Response.Status.OK)
                .build();
    }

    @POST
    public Response addMessage(Message message, @Context UriInfo uriInfo) {
        MessageResult result = messageService.add(message);
        URI uri = uriInfo.getAbsolutePathBuilder()
                .path(String.valueOf(result.getEntity().getId()))
                .build();
        return Response.created(uri)
                .entity(result.getEntity())
                .status(Response.Status.CREATED)
                .build();
    }

    @PUT
    @Path("/{messageId}")
    public Response updateMessage(@PathParam("messageId") long id, Message message, @Context UriInfo uriInfo) {
        message.setId(id);
        MessageResult result = messageService.update(message);
        hasError(result, id);
        return Response.created(uriInfo.getAbsolutePath())
                .status(Response.Status.OK)
                .entity(result.getEntity())
                .build();
    }

    @DELETE
    @Path("/{messageId}")
    public Response removeMessage(@PathParam("messageId") long id, @Context UriInfo uriInfo) {
        MessageResult result = messageService.delete(id);
        hasError(result, id);
        return Response.status(Response.Status.NO_CONTENT).build();
    }

    @Path("/{messageId}/comments")
    public CommentResource getCommentResource() {
        return new CommentResource();
    }

    private Response getMessagesForYear(int year, UriInfo uriInfo) {
        MessageResult result = messageService.getAllForYear(year);
        return Response.created(uriInfo.getAbsolutePath())
                .entity(result.getEntities())
                .status(Response.Status.OK)
                .build();
    }

    private Response getMessagesPaginated(int start, int size, UriInfo uriInfo) {
        MessageResult result = messageService.get(start, size);
        hasError(result);
        return Response.created(uriInfo.getAbsolutePath())
                .entity(result.getEntities())
                .status(Response.Status.OK)
                .build();
    }

    private void hasError(MessageResult result) {
        hasError(result, -1);
    }

    private void hasError(MessageResult result, long messageId) {
        if(result.isError()) {
            if(messageId > -1)
                handleDataNotFound(result, messageId);
            handleOutOfRange(result);
        }
    }

    private void handleOutOfRange(MessageResult result) {
        if (result.getErrorMsg().equals(MessageResult.OUT_OF_RANGE))
            throw new IndexOutOfBoundsException("Out of range.");
    }

    private void handleDataNotFound(MessageResult result, long messageId) {
        if (result.getErrorMsg().equals(MessageResult.DATA_NOT_FOUND))
            throw new DataNotFoundException("Message with id " + messageId + " not found.");
    }
 }
