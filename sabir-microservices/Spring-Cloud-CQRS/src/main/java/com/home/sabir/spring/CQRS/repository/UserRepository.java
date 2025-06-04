package com.home.sabir.spring.CQRS.repository;

import com.home.sabir.spring.CQRS.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
