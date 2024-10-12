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
    private UserService userService;

    @Mock
    private TranslateService translateService;

    @InjectMocks
    private UserResource userResource;

    private User user;
    private UserRequest userRequest;
    private UserResponse userResponse;

    @BeforeEach
    public void setUp() {
        user = Utils.createUser();
        userRequest = translateService.translate(user, UserRequest.class);
        userResponse = translateService.translate(user, UserResponse.class);
    }

    @Test
    public void testGetAll() {
        List<User> users = Collections.singletonList(user);
        when(userService.findAll()).thenReturn(users);
        when(translateService.createResponse(users, UserResponse.class)).thenReturn(Response.ok(users).build());

        Response response = userResource.getAll();
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        verify(userService, times(1)).findAll();
        verify(translateService, times(1)).createResponse(users, UserResponse.class);
    }

    @Test
    public void testSearch() {
        List<User> users = Collections.singletonList(user);
        when(userService.search("field", "value")).thenReturn(users);
        when(translateService.createResponse(users, UserResponse.class)).thenReturn(Response.ok(users).build());

        Response response = userResource.search("field", "value");
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        verify(userService, times(1)).search("field", "value");
        verify(translateService, times(1)).createResponse(users, UserResponse.class);
    }

    @Test
    public void testFind() {
        List<User> users = Collections.singletonList(user);
        when(userService.find("id")).thenReturn(users);
        when(translateService.createResponse(users, UserResponse.class)).thenReturn(Response.ok(users).build());

        Response response = userResource.find("id");
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        verify(userService, times(1)).find("id");
        verify(translateService, times(1)).createResponse(users, UserResponse.class);
    }

    @Test
    public void testAdd() {
        when(userService.add(userRequest)).thenReturn(user);
        Response response = userResource.add(userRequest);
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        verify(userService, times(1)).add(userRequest);
    }

    @Test
    public void testUpdate() {
        when(userService.update(userRequest, "id")).thenReturn(String.valueOf(user));
        Response response = userResource.updadte(userRequest, "id");
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        verify(userService, times(1)).update(userRequest, "id");
    }

    @Test
    public void testDelete() {
        when(userService.delete("id")).thenReturn(String.valueOf(user));
        Response response = userResource.delete("id");
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        verify(userService, times(1)).delete("id");
    }
}