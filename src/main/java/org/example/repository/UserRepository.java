package org.example.repository;

import org.example.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUserDetailsEmail(String email);

    List<User> findDistinctByBookings_Flight_Id(Long flightId);
}
