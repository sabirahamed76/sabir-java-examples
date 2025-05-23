package com.home.sabir.springboot.SpringData.service;

import java.util.List;

import com.home.sabir.springboot.SpringData.entity.Student;

public interface StudentJDBCTemplateService {

    

    public int saveStudent(Student student) ;

    public int updateStudent(Long id, String name, String email);

    public int deleteStudent(Long id) ;

    public int countStudents() ;
    
    public Student getStudentById(Long id);
    
    public Student findByStudentId (Long id);
    
    public Student findByStudentIdDirect (Long id);
    
    public List<Student> getAllStudents() ;
    
    public List<Student> listAllStudents() ;
    
    
}
