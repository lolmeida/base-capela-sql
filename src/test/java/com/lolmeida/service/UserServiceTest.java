package com.lolmeida.service;


import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import io.quarkus.test.junit.QuarkusTest;

import com.lolmeida.api.Utils;
import com.lolmeida.api.dto.request.UserRequest;
import com.lolmeida.domain.entity.database.User;
import com.lolmeida.domain.repository.UserRepository;
import com.lolmeida.domain.service.UserService;
import com.lolmeida.domain.translate.TranslateService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.when;

@QuarkusTest
public class UserServiceTest {

    @Mock
    UserRepository repository;

    @Mock
    TranslateService translateService;

    @InjectMocks
    UserService userService;

    private UserRequest request;
    private User user;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);

        user = Utils.createUser();
        request = translateService.translate(user, UserRequest.class);
        //when(repository.add(any(UserRequest.class))).thenReturn(user);
        when(repository.update(any(UserRequest.class), anyString())).thenReturn("User updated");
        when(repository.delete(anyString())).thenReturn("User deleted");
        when(repository.find(anyString())).thenReturn(Collections.singletonList(user));
        when(repository.findAll((String) any())).thenReturn(Collections.singletonList(user));
        when(repository.search(anyString(), anyString())).thenReturn(Collections.singletonList(user));
    }

    @Test
    public void testFindAll() {
        List<User> users = userService.findAll("id");
        assertFalse(users.isEmpty());
        assertEquals("Lolmeida", users.get(0).getName());
    }

    @Test
    public void testSearch() {
        List<User> users = userService.search("field", "value");
        assertFalse(users.isEmpty());
        assertEquals("Lolmeida", users.get(0).getName());
    }

    @Test
    public void testFind() {
        List<User> users = userService.find("id");
        assertFalse(users.isEmpty());
        assertEquals("Lolmeida", users.get(0).getName());
    }

    @Test
    public void testAdd() {
        request = Utils.createUserRequest();
        User result = userService.add(request);
        assertEquals("Antonio", result.getName());

    }

    @Test
    public void testUpdate() {
        String result = userService.update(request, "id");
        assertEquals("User updated", result);
    }

    @Test
    public void testDelete() {
        String result = userService.delete("id");
        assertEquals("User deleted", result);
    }
}