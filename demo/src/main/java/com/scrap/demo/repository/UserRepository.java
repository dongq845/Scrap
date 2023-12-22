package com.scrap.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.scrap.demo.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    // Custom Methods
    Optional<User> findByUsername(String username); // Retrieves Optional<User> - Single

    Optional<User> findByEmail(String email); // Retrieve Optional<User> - Single
}
