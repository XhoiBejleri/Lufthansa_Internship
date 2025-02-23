package org.example.repository;

import java.util.List;
import java.util.Optional;

public interface Repository<ENTITY, ID> {

    /**
     * Finds a entity by a given id
     *
     * @param id the given id
     * @return the optional containing the entity
     */
    Optional<ENTITY> findById(ID id);
    List<ENTITY> findAll();
    void save(ENTITY entity);
    void delete(ENTITY entity);

}
