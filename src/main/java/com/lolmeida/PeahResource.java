package com.lolmeida;

import jakarta.enterprise.context.RequestScoped;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;

import com.lolmeida.domain.entity.database.User;

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

    @DELETE
    @Path("/{id}")
    @Transactional
    void deleteby(
            @RequestBody T request);

    void deleteby(User entity);

   /* @POST
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response save(@RequestBody T request);*/

}
