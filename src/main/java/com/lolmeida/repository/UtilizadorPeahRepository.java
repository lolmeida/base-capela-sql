package com.lolmeida.repository;

import com.lolmeida.PeahRepository;
import com.lolmeida.entity.database.Utilizador;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import io.quarkus.panache.common.Sort;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class UtilizadorPeahRepository implements PanacheRepositoryBase<Utilizador, UUID>, PeahRepository<Utilizador> {
    public List<Utilizador> findAll(String... orderByColumns){
        return listAll(Sort.descending(orderByColumns)).stream().toList();
    }

    public List<Utilizador> search(final String field, final String value){
        final String searchInput = "%" + value.toLowerCase() + "%";
        return list("LOWER(" + field + ") like ?1", searchInput.toLowerCase());
    }


    @Override
    public List<Utilizador> findBy(final String id){
        return list("Mail like ?1", id);
    }

    @Override
    @Transactional
    public String save(Utilizador entity) {
        persistAndFlush(entity);
        return entity.getId();
    }

}
