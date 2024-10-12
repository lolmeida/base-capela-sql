package com.lolmeida;

import jakarta.enterprise.context.RequestScoped;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;

import com.lolmeida.dto.request.UserRequest;

@RequestScoped
@Path("/user")
@Produces({MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN})
public interface PeahResource<T> {

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    Response getAll();

    @GET
    @Path("/search/{field}/{value}")
    Response search(
            @PathParam("field")
            final String field,
            @PathParam("value")
            final String value);

    @GET
    @Path("/{id}")
    Response find(
            @PathParam("id")
            final String id);

    @POST
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    Response add(
            @RequestBody UserRequest request);

    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updadte(
            @RequestBody
            UserRequest request,
            @PathParam("id")
            final String id);

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    Response delete(
            @PathParam("id")
            final String id);

}
