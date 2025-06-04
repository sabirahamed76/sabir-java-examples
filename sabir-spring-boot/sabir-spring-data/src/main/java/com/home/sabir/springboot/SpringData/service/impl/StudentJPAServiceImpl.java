package com.home.sabir.springboot.SpringData.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.home.sabir.springboot.SpringData.entity.Student;
import com.home.sabir.springboot.SpringData.exception.ResourceNotFoundException;
import com.home.sabir.springboot.SpringData.repository.StudentJPARepository;
import com.home.sabir.springboot.SpringData.service.StudentJPAService;

@Service
@Transactional
public class StudentJPAServiceImpl implements StudentJPAService{

	@Autowired
	private StudentJPARepository studentJPARepository;
	
	
	
	public StudentJPAServiceImpl(StudentJPARepository studentJPARepository) {
		super();
		this.studentJPARepository = studentJPARepository;
	}

	@Override
	public List<Student> getAllStudents() {
		return studentJPARepository.findAll();
	}

	@Override
	public Student saveStudent(Student student) {
		return studentJPARepository.save(student);
	}

	
	@Override
	public Student getStudentById(Long id) {
//		Optional<Student> student = studentRepository.findById(id);
//		if(student.isPresent()) {
//			return student.get();
//		}else {
//			throw new ResourceNotFoundException("Student", "Id", id);
//		}
		return studentJPARepository.findById(id).orElseThrow(() -> 
						new ResourceNotFoundException("Student", "Id", id));
		
	}
	
	@Override
	public List<Student> getStudentByName(String name) {
//		Optional<Student> student = studentRepository.findById(id);
//		if(student.isPresent()) {
//			return student.get();
//		}else {
//			throw new ResourceNotFoundException("Student", "Id", id);
//		}
		return studentJPARepository.findByFirstName(name);
		
	}

	
	@Override
	public Student updateStudent(Student student, Long id) {
		
		// we need to check whether student with given id is exist in DB or not
		Student existingStudent = studentJPARepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException("Student", "Id", id)); 
		
		existingStudent.setFirstName(student.getFirstName());
		existingStudent.setLastName(student.getLastName());
		existingStudent.setEmail(student.getEmail());
		// save existing student to DB
		studentJPARepository.save(existingStudent);
		return existingStudent;
	}
	

	@Override
	public void deleteStudentById(Long id) {		
		// check whether a student exist in a DB or not
		studentJPARepository.findById(id).orElseThrow(() -> 
								new ResourceNotFoundException("Student", "Id", id));
		studentJPARepository.deleteById(id);
	}
	

}
