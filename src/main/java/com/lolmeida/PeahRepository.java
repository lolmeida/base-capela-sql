package com.lolmeida;

import java.util.List;

public interface PeahRepository<T> {
    public List<T> findAll(String... orderByColumns);

    public List<T> search(final String field, final String value);

    //public List<T> searchOpt(final Map<String,String>... fieldsAndValues);

    public List<T> findBy(final String id);
    public String save(T entity);

}
