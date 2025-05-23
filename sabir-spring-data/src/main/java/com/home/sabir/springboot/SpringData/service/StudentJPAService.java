package com.home.sabir.springboot.SpringData.service;

import java.util.List;

import com.home.sabir.springboot.SpringData.entity.Student;

public interface StudentJPAService {
	List<Student> getAllStudents();
	
	Student saveStudent(Student student);
	
	List<Student> getStudentByName(String name);
	
	Student getStudentById(Long id);
	
	Student updateStudent(Student student, Long id);
	
	void deleteStudentById(Long id);
}
