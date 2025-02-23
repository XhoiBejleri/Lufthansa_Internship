package org.example.repository;

import org.example.model.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends Repository<User, Long> {

    Optional<User> findByEmail(String email);

    List<User> findAllNamedQuery(String email);
}
