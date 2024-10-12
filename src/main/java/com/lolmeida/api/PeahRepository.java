package com.lolmeida.api;

import java.util.List;

import jakarta.transaction.Transactional;

import com.lolmeida.domain.entity.database.User;
import com.lolmeida.api.dto.request.UserRequest;

public interface PeahRepository<T> {

    List<T> findAll(String... orderByColumns);

    List<T> search(final String field, final String value);

    //public List<T> searchOpt(final Map<String,String>... fieldsAndValues);

    List<T> find(final String id);

    @Transactional
    User add(final UserRequest entity);

    @Transactional
    String update(final UserRequest request,final String id);

    @Transactional
    String delete(final String id);



}
