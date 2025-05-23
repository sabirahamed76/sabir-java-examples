package com.home.sabir.springboot.SpringData.service;

import java.util.List;
import java.util.Optional;

import com.home.sabir.springboot.SpringData.entity.Student;

public interface StudentJDBCService {


    public Student saveStudent(Student student) ;

    public Optional<Student> getStudentById(Long id) ;

    public List<Student> getAllStudents() ;

    public void deleteStudent(Long id) ;
    
    public Student updateStudent(Student student);
    
    public long countStudents();

    
}
