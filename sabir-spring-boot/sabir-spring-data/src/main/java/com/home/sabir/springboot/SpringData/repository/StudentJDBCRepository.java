package com.home.sabir.springboot.SpringData.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.home.sabir.springboot.SpringData.entity.Student;

@Repository
public interface StudentJDBCRepository extends CrudRepository<Student, Long>{

}

