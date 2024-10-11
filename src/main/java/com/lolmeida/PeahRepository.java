package com.lolmeida;

import java.util.List;

import jakarta.transaction.Transactional;

import com.lolmeida.domain.entity.database.User;

public interface PeahRepository<T> {

    List<T> findAll(String... orderByColumns);

    List<T> search(final String field, final String value);

    //public List<T> searchOpt(final Map<String,String>... fieldsAndValues);

    List<T> find(final String id);

    @Transactional
    Object deleteby(final User entity);

    @Transactional
    String save(T entity);

}
