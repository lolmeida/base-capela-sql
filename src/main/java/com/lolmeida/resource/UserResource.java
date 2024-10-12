package com.lolmeida.resource;

import java.util.List;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

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
    public Response find(final String id) {
        List<User> data = service.find(id);
        return translateService.createResponse(data, UserResponse.class);
    }


    @Override
    public Response save(final UserRequest request) {
        return Response.ok(service.add(request)).build();
    }

    @Override
    public Response updadte(final UserRequest request, final String id) {
        return Response.ok(
                service.update(request, id)
        ).build();
    }

    @Override
    public Response delete(final String id) {
        return Response.ok(
                service.delete(id)
        ).build();
    }


}
