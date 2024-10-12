package com.lolmeida.domain.service;

import com.lolmeida.domain.entity.database.User;
import com.lolmeida.api.PeahRepository;
import com.lolmeida.domain.translate.TranslateService;
import com.lolmeida.api.dto.request.UserRequest;
import com.lolmeida.domain.repository.UserRepository;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

import java.util.List;
import java.util.Optional;

@RequestScoped
public class UserService implements PeahRepository<User> {
    @Inject
    UserRepository repository;
    @Inject
    TranslateService translateService;

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
    public User add(final UserRequest entity) {
        return repository.add(entity);
    }

    @Override
    public String update( final UserRequest request,final String id) {
        final Optional<User> data = repository.find(id).stream().findFirst();
        if(data.isPresent()){
            repository.update(request, id);
            return "User updated";
        }
        return "User not found";

    }

    @Override
    public String delete( final String id) {
        final Optional<User> data = repository.find(id).stream().findFirst();
        if(data.isPresent()){
            repository.delete(id);
            return "User deleted";
        }
        return "User not found";
    }
}
