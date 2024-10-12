package com.lolmeida.domain.repository;

import com.lolmeida.api.PeahRepository;
import com.lolmeida.domain.entity.database.User;
import com.lolmeida.domain.translate.TranslateService;
import com.lolmeida.api.dto.request.UserRequest;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import io.quarkus.panache.common.Sort;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
public class UserRepository implements PanacheRepositoryBase<User, UUID>, PeahRepository<User> {

    private final TranslateService translateService;

    @jakarta.inject.Inject
    public UserRepository(final TranslateService translateService) {
        this.translateService = translateService;
    }

    public List<User> findAll(String... orderByColumns){
        try {
            return listAll(Sort.descending(orderByColumns)).stream().toList();
        } catch (Exception e) {
            // Handle exception
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    public List<User> search(final String field, final String value){
        try {
            final String searchInput = "%" + value.toLowerCase() + "%";
            return list("LOWER(" + field + ") like ?1", searchInput.toLowerCase());
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    @Override
    public List<User> find(final String id){
        try {
            return list("id like ?1", id);
        } catch (Exception e) {
            // Handle exception
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    @Override
    @Transactional
    public String delete(final String id){
        final Optional<User> data = this.find(id).stream().findFirst();
        if(data.isEmpty()){
            return "User not found";
        }
        delete(data.get());
        return "User deleted";
    }









    @Override
    @Transactional
    public User add(UserRequest request) {
        final User data = translateService.translate(request, User.class);
        persistAndFlush(data);
        return data;
    }

    @Override
    @Transactional
    public String update(final UserRequest request, final String id){
        final Optional<User> data = this.find(id).stream().findFirst();
        if(data.isEmpty()){
            return "User not found";
        }
        update(request,id);
        return "User updated";

    }



}
