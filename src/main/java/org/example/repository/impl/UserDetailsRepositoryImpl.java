package org.example.repository.impl;

import jakarta.persistence.EntityManager;
import org.example.configuration.EntityManagerConfiguration;
import org.example.model.entity.UserDetails;
import org.example.repository.UserDetailsRepository;

import java.util.List;
import java.util.Optional;

public class UserDetailsRepositoryImpl implements UserDetailsRepository {

    private final EntityManager entityManager;

    public UserDetailsRepositoryImpl() {
        entityManager = EntityManagerConfiguration.getEntityManager();
    }

    @Override
    public Optional<UserDetails> findById(Long id) {
        return Optional.ofNullable(entityManager.find(UserDetails.class, id));
    }

    @Override
    public List<UserDetails> findAll() {
        return entityManager.createQuery("SELECT ud FROM UserDetails ud", UserDetails.class)
                .getResultList();
    }

    @Override
    public void save(UserDetails userDetails) {
        entityManager.getTransaction().begin();
        entityManager.persist(userDetails);
        entityManager.getTransaction().commit();
    }

    @Override
    public void delete(UserDetails userDetails) {
        entityManager.getTransaction().begin();
        if (!entityManager.contains(userDetails)) {
            userDetails = entityManager.merge(userDetails);
        }
        entityManager.remove(userDetails);
        entityManager.getTransaction().commit();
    }
}
