package com.lolmeida.api;

import jakarta.enterprise.context.RequestScoped;
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

import com.lolmeida.api.dto.request.UserRequest;

@RequestScoped
@Path("/user")
@Produces({MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_JSON})
public interface PeahResource<T> {

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    Response getAll();

    @GET
    @Path("/search/{field}/{value}")
    @Produces({MediaType.APPLICATION_JSON})
    Response search(
            @PathParam("field")
            final String field,
            @PathParam("value")
            final String value);

    @GET
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    Response find(
            @PathParam("id")
            final String id);

    @POST
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    Response add(
            @RequestBody UserRequest request);

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    Response updadte(
            @RequestBody
            UserRequest request,
            @PathParam("id")
            final String id);

    @DELETE
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    Response delete(
            @PathParam("id")
            final String id);

}
