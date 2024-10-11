package com.lolmeida.service;

import com.lolmeida.domain.entity.database.User;
import com.lolmeida.PeahRepository;
import com.lolmeida.repository.UserRepository;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

import java.util.List;

@RequestScoped
public class UserService implements PeahRepository<User> {
    @Inject
    UserRepository repository;

    @Override
    public List<User> findAll(String... orderByColumns){
        return  repository.findAll(orderByColumns);
    }

    @Override
    public List<User> search(String field, String value) {
        return repository.search(field, value);
    }

    @Override
    public List<User> find(String id) {
        return repository.find(id);
    }

    @Override
    public Object deleteby( User entity) {
         repository.delete(entity);
        return null;
    }

    @Override
    public String save(User entity) {
        return repository.save(entity);
    }
}
