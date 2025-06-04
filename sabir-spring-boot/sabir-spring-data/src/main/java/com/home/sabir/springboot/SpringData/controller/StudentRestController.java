package com.home.sabir.springboot.SpringData.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.home.sabir.springboot.SpringData.entity.Student;
import com.home.sabir.springboot.SpringData.service.StudentJPAService;

@RestController
@RequestMapping("/api/students")
public class StudentRestController {
	
	@Autowired
	private StudentJPAService studentJPAService;

	

	public StudentRestController(StudentJPAService studentJPAService) {
		super();
		this.studentJPAService = studentJPAService;
	}
	
	// build create student REST API
	// http://localhost:8080/api/students/saveStudent
	@PostMapping("/saveStudent")
	public ResponseEntity<Student> saveStudent(@RequestBody Student student){
		return new ResponseEntity<Student>(studentJPAService.saveStudent(student), HttpStatus.CREATED);
	}
	
	// build get all students REST API
	// http://localhost:8080/api/students/listAllStudents
	@GetMapping("/listAllStudents")
	public List<Student> getAllStudents(){
		return studentJPAService.getAllStudents();
	}
	
	// build get student by id REST API
	// http://localhost:8080/api/students/1
	@GetMapping("{id}")
	public ResponseEntity<Student> getStudentById(@PathVariable("id") long studentId){
		try {
			return new ResponseEntity<Student>(studentJPAService.getStudentById(studentId), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Student>(new Student(), HttpStatus.NOT_FOUND);
		}
	}
	
	// build update student REST API
	// http://localhost:8080/api/students/1
	@PutMapping("{id}")
	public ResponseEntity<Student> updateStudent(@PathVariable("id") Long id
												  ,@RequestBody Student student){
		try {
			return new ResponseEntity<Student>(studentJPAService.updateStudent(student,id), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Student>(new Student(), HttpStatus.NOT_FOUND);
		}
	}
	
	// build delete student REST API
	// http://localhost:8080/api/students/1
	@DeleteMapping("{id}")
	public ResponseEntity<String> deleteStudent(@PathVariable("id") long id){
		try {	
			// delete student from DB
			studentJPAService.deleteStudentById(id);
			
				return new ResponseEntity<String>("Student deleted successfully!.", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Student not found", HttpStatus.NOT_FOUND);
		}
	}
	
}
