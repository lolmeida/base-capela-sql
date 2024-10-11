package com.lolmeida.resource;

import com.lolmeida.Utils;
import com.lolmeida.domain.translate.TranslateService;
import com.lolmeida.dto.request.UserRequest;
import com.lolmeida.dto.response.UserResponse;
import com.lolmeida.domain.entity.database.User;
import com.lolmeida.service.UserService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;


import java.util.List;

@Path("/user")
@RequestScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UtilizadorResource {
    @Inject
    UserService service;

    @Inject
    TranslateService translateService;

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {

        List<UserResponse> data = service.findAll()
                .stream()
                .map(user -> translateService.translate(user, UserResponse.class))
                .toList();
        return Response.ok(data).build();
    }

    @GET
    @Path("/search/{field}/{value}")
    public Response search(
            @PathParam("field") final String field,
            @PathParam("value") final String value) {
        List<UserResponse> data = service.search( field, value)
                .stream()
                .map(user -> translateService.translate(user, UserResponse.class))
                .toList();
        return Response.ok(data).build();
    }

    @GET
    @Path("/{id}")
    public Response findByCustomer(@PathParam("id") final String id){
        List<UserResponse> data = service.findBy(id)
                .stream()
                .map(user -> translateService.translate(user, UserResponse.class))
                .toList();
        return Response.ok(data).build();
    }

    @POST
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response save(@RequestBody UserRequest request) {
        service.save(translateService.translate(request, User.class));

        return Response
                //.ok(service.search("Mail", service.save(requestToObj(request))))
                .ok("requested")
                .build();
    }

    private User objToResponse (){
        return translateService.translate(UserResponse.class, User.class);

    }

    private UserResponse requestToObj (){
        return translateService.translate(User.class, UserResponse.class);

    }
}
