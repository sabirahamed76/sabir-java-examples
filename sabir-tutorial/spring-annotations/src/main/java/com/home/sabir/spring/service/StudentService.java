package com.home.sabir.spring.service;

import com.home.sabir.spring.entity.Student;

import java.util.List;
import java.util.Optional;


public interface StudentService {

    public Student addStudent(Student student);

    public Optional<Student> getStudent(int id);

    public List<Student> getStudents();
}
