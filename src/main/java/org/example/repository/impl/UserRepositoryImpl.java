package org.example.repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.example.configuration.EntityManagerConfiguration;
import org.example.model.entity.User;
import org.example.repository.UserRepository;
import org.example.util.Queries;

import java.util.List;
import java.util.Optional;

public class UserRepositoryImpl implements UserRepository {

    private final EntityManager entityManager;

    public UserRepositoryImpl() {
        this.entityManager = EntityManagerConfiguration.getEntityManager();
    }

    @Override
    public Optional<User> findById(Long id) {
        return Optional.ofNullable(entityManager.find(User.class, id));
    }

    @Override
    public List<User> findAll() {
        TypedQuery<User> query = entityManager.createQuery(Queries.FIND_ALL_USERS, User.class);
        return query.getResultList();
    }

    @Override
    public void save(User user) {
        entityManager.getTransaction().begin();
        if (user.getId() == null) {
            entityManager.persist(user);
        } else {
            entityManager.merge(user);
        }
        entityManager.getTransaction().commit();
    }

    @Override
    public void delete(User user) {
        entityManager.getTransaction().begin();
        User managedUser = user;
        if (!entityManager.contains(user)) {
            managedUser = entityManager.merge(user);
        }
        entityManager.remove(managedUser);
        entityManager.getTransaction().commit();
    }

    @Override
    public Optional<User> findByEmail(String email) {
        TypedQuery<User> query = entityManager.createNamedQuery("findByEmail", User.class);
        query.setParameter("email", email);
        try {
            return Optional.ofNullable(query.getSingleResult());
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public List<User> findAllNamedQuery(String email) {
        TypedQuery<User> query = entityManager.createNamedQuery("findByEmail", User.class);
        query.setParameter("email", email);
        return query.getResultList();
    }
}