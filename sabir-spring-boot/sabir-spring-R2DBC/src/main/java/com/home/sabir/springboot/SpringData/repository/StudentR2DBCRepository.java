package com.home.sabir.springboot.SpringData.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import com.home.sabir.springboot.SpringData.entity.Student;

@Repository
public interface StudentR2DBCRepository extends ReactiveCrudRepository<Student, Long> {
}
