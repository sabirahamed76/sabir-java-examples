package com.home.sabir.spring.repository;

import com.home.sabir.spring.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface StudentRepository extends JpaRepository<Student,Integer> {
}
