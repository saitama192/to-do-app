package com.example.to_do_app.repository;

import com.example.to_do_app.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // Additional query methods can be defined here if needed
    Optional<User> findByEmail(String email);
}
