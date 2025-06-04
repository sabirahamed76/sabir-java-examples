package com.home.sabir.springboot.SpringData.controller;

import com.home.sabir.springboot.SpringData.entity.Student;
import com.home.sabir.springboot.SpringData.service.StudentJPAService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.home.sabir.springboot.SpringData.service.StudentJDBCService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/jdbc/students")
public class StudentJDBCController {

	@Autowired
    private StudentJDBCService studentJDBCService;

    public StudentJDBCController(StudentJDBCService studentJDBCService) {
        super();
        this.studentJDBCService = studentJDBCService;
    }

    // build create student REST API
    // http://localhost:8080/jdbc/students/saveStudent
    @PostMapping("/saveStudent")
    public ResponseEntity<Student> saveStudent(@RequestBody Student student){
        return new ResponseEntity<Student>(studentJDBCService.saveStudent(student), HttpStatus.CREATED);
    }

    // build get all students REST API
    // http://localhost:8080/jdbc/students/listAllStudents
    @GetMapping("/listAllStudents")
    public List<Student> getAllStudents(){
        return studentJDBCService.getAllStudents();
    }

    // build get student by id REST API
    // http://localhost:8080/jdbc/students/1
    @GetMapping("{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable("id") long studentId){
        try {
            Optional<Student> optionalStudent=studentJDBCService.getStudentById(studentId);
            Student std = optionalStudent.get();
            return new ResponseEntity<Student>(std, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<Student>(new Student(), HttpStatus.NOT_FOUND);
        }
    }

    // build update student REST API
    // http://localhost:8080/jdbc/students/1
    @PutMapping("{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable("id") Long id
            ,@RequestBody Student student){
        try {
            Optional<Student> optionalStudent=studentJDBCService.getStudentById(id);
            Student std = optionalStudent.get();
            return new ResponseEntity<Student>(studentJDBCService.updateStudent(std), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<Student>(new Student(), HttpStatus.NOT_FOUND);
        }
    }

    // build delete student REST API
    // http://localhost:8080/jdbc/students/1
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable("id") long id){
        try {
            // delete student from DB
            studentJDBCService.deleteStudent(id);

            return new ResponseEntity<String>("Student deleted successfully!.", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<String>("Student not found", HttpStatus.NOT_FOUND);
        }
    }
}
