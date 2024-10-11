package com.lolmeida.resource;

import java.util.List;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;

import com.lolmeida.PeahResource;
import com.lolmeida.domain.entity.database.User;
import com.lolmeida.domain.translate.TranslateService;
import com.lolmeida.dto.request.UserRequest;
import com.lolmeida.dto.response.UserResponse;
import com.lolmeida.service.UserService;


@Consumes(MediaType.APPLICATION_JSON)
public class UserResource implements PeahResource {

    @Inject
    UserService service;
    @Inject
    TranslateService translateService;

    @Override
    public Response getAll() {
        List<User> data = service.findAll();
        return translateService.createResponse(data, UserResponse.class);
    }

    @Override
    public Response search(
            final String field,
            final String value) {
        List<User> data = service.search(field, value);
        return translateService.createResponse(data, UserResponse.class);
    }

    @Override
    public Response findByCustomer(final String id) {
        List<User> data = service.findBy(id);
        return translateService.createResponse(data, UserResponse.class);
    }


    @POST
    @Path("/")
    public Response save(
            @RequestBody UserRequest request
    ) {
        //service.save(translateService.translate(request, User.class));
        return Response
                .ok("requested")
                .build();
    }











 /*   @POST
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response save(@RequestBody UserRequest request) {
        //service.save(translateService.translate(request, User.class));
        return Response
                //.ok(service.search("id", service.save(requestToObj(request))))
                .ok("requested")
                .build();
    }*/



   /* private Response createResponse(List<User> data){
        final List<UserResponse> list = data
                .stream()
                .map(entity -> translateService.translate(entity, UserResponse.class))
                .toList();
        return Response.ok(data).build();
    }*/

    private UserResponse requestToObj() {
        return translateService.translate(User.class, UserResponse.class);

    }
}
