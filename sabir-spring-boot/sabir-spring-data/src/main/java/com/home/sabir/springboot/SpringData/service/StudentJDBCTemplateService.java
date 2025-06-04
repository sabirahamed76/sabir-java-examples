package com.home.sabir.springboot.SpringData.service;

import java.util.List;

import com.home.sabir.springboot.SpringData.entity.Student;

public interface StudentJDBCTemplateService {

    

    public Student saveStudent(Student student) ;

    public Student updateStudent(Long id, String firstName, String lastName, String email);

    public void deleteStudent(Long id) ;

    public int countStudents() ;
    
    public Student getStudentById(Long id);
    
    public Student findByStudentId (Long id);
    
    public Student findByStudentIdDirect (Long id);
    
    public List<Student> getAllStudents() ;
    
    public List<Student> listAllStudents() ;

    public List<Student> getAllStudentsByFirstName(String firstName);
}
