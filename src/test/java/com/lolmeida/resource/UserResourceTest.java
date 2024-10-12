package com.lolmeida.resource;


import java.util.Collections;
import java.util.List;

import jakarta.ws.rs.core.Response;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.lolmeida.api.Utils;
import com.lolmeida.api.dto.request.UserRequest;
import com.lolmeida.api.dto.response.UserResponse;
import com.lolmeida.api.resource.UserResource;
import com.lolmeida.domain.entity.database.User;
import com.lolmeida.domain.service.UserService;
import com.lolmeida.domain.translate.TranslateService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserResourceTest {

    @Mock
    private UserService service;

    @Mock
    private TranslateService translateService;

    @InjectMocks
    private UserResource userResource;

    private User entity;
    private UserRequest request;

    @BeforeEach
    public void setUp() {
        entity = Utils.createUser();
        request = Utils.createUserRequest();
    }

    @Test
    public void testGetAll() {
        List<User> users = Collections.singletonList(entity);
        when(service.findAll()).thenReturn(users);
        when(translateService.createResponse(users, UserResponse.class)).thenReturn(Response.ok(users).build());

        Response response = userResource.getAll();
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        verify(service, times(1)).findAll();
        verify(translateService, times(1)).createResponse(users, UserResponse.class);
    }

    @Test
    public void testSearch() {
        List<User> users = Collections.singletonList(entity);
        when(service.search("field", "value")).thenReturn(users);
        when(translateService.createResponse(users, UserResponse.class)).thenReturn(Response.ok(users).build());

        Response response = userResource.search("field", "value");
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        verify(service, times(1)).search("field", "value");
        verify(translateService, times(1)).createResponse(users, UserResponse.class);
    }

    @Test
    public void testFind() {
        List<User> users = Collections.singletonList(entity);
        when(service.find("id")).thenReturn(users);
        when(translateService.createResponse(users, UserResponse.class)).thenReturn(Response.ok(users).build());

        Response response = userResource.find("id");
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        verify(service, times(1)).find("id");
        verify(translateService, times(1)).createResponse(users, UserResponse.class);
    }

    @Test
    public void testAdd() {
        when(service.add(request)).thenReturn(entity);
        Response response = userResource.add(request);
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        verify(service, times(1)).add(request);
    }

    @Test
    public void testUpdate() {
        when(service.update(request, "id")).thenReturn(String.valueOf(entity));
        Response response = userResource.updadte(request, "id");
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        verify(service, times(1)).update(request, "id");
    }

    @Test
    public void testDelete() {
        when(service.delete("id")).thenReturn(String.valueOf(entity));
        Response response = userResource.delete("id");
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        verify(service, times(1)).delete("id");
    }
}