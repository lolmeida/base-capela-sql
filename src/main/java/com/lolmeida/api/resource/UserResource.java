package com.lolmeida.api.resource;

import java.util.List;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import com.lolmeida.api.PeahResource;
import com.lolmeida.domain.entity.database.User;
import com.lolmeida.domain.translate.TranslateService;
import com.lolmeida.api.dto.request.UserRequest;
import com.lolmeida.api.dto.response.UserResponse;
import com.lolmeida.domain.service.UserService;


@Consumes(MediaType.APPLICATION_JSON)
public class UserResource implements PeahResource {

    @Inject
    UserService service;
    @Inject
    TranslateService translateService;

    @Override
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        List<User> data = service.findAll();
        return translateService.createResponse(data, UserResponse.class);
    }

    @Override
    @Produces({MediaType.APPLICATION_JSON})
    public Response search(
            final String field,
            final String value) {
        List<User> data = service.search(field, value);
        return translateService.createResponse(data, UserResponse.class);
    }

    @Override
    @Produces({MediaType.APPLICATION_JSON})
    public Response find(final String id) {
        List<User> data = service.find(id);
        return translateService.createResponse(data, UserResponse.class);
    }


    @Override
    @Consumes(MediaType.APPLICATION_JSON)
    public Response add(final UserRequest request) {
        return Response.ok(service.add(request)).build();
    }

    @Override
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updadte(final UserRequest request, final String id) {
        return Response.ok(
                service.update(request, id)
        ).build();
    }

    @Override
    @Consumes(MediaType.APPLICATION_JSON)
    public Response delete(final String id) {
        return Response.ok(
                service.delete(id)
        ).build();
    }


}
