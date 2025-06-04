package com.home.sabir.spring.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.home.sabir.spring.security.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
