package com.lolmeida.repository;

import com.lolmeida.PeahRepository;
import com.lolmeida.domain.entity.database.User;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import io.quarkus.panache.common.Sort;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class UtilizadorPeahRepository implements PanacheRepositoryBase<User, UUID>, PeahRepository<User> {
    public List<User> findAll(String... orderByColumns){
        return listAll(Sort.descending(orderByColumns)).stream().toList();
    }

    public List<User> search(final String field, final String value){
        final String searchInput = "%" + value.toLowerCase() + "%";
        return list("LOWER(" + field + ") like ?1", searchInput.toLowerCase());
    }


    @Override
    public List<User> findBy(final String id){
        return list("id like ?1", id);
    }

    @Override
    @Transactional
    public String save(User entity) {
        persistAndFlush(entity);
        return entity.getId();
    }

}
