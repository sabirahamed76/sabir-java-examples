package com.home.sabir.springboot.SpringData.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.home.sabir.springboot.SpringData.entity.Student;
import com.home.sabir.springboot.SpringData.repository.StudentJDBCRepository;
import com.home.sabir.springboot.SpringData.service.StudentJDBCService;

@Service
@Transactional
public class StudentJDBCServiceImpl implements  StudentJDBCService {
	
	@Autowired
	private StudentJDBCRepository studentJDBCRepository;



    public Student saveStudent(Student student) {
        return studentJDBCRepository.save(student);
    }

    public Optional<Student> getStudentById(Long id) {
        return studentJDBCRepository.findById(id);
    }

    public List<Student> getAllStudents() {
        return (List<Student>) studentJDBCRepository.findAll();
    }

    public void deleteStudent(Long id) {
        studentJDBCRepository.deleteById(id);
    }

    public Student updateStudent(Student student) {
        return studentJDBCRepository.save(student);
    }
    
    public long countStudents() {
    	return studentJDBCRepository.count();
    };
    
}
