package com.home.sabir.springboot.SpringData.controller;

import com.home.sabir.springboot.SpringData.entity.Student;
import com.home.sabir.springboot.SpringData.service.StudentJDBCService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.home.sabir.springboot.SpringData.service.StudentJDBCTemplateService;

import java.util.List;

@RestController
@RequestMapping("/jdbcTemplate/students")
public class StudentJDBCTemplateController {

	@Autowired
    private StudentJDBCTemplateService studentJDBCTemplateService;

    public StudentJDBCTemplateController(StudentJDBCTemplateService studentJDBCTemplateService) {
        super();
        this.studentJDBCTemplateService = studentJDBCTemplateService;
    }

    // build create student REST API
    // http://localhost:8080/jdbcTemplate/students/saveStudent
    @PostMapping("/saveStudent")
    public ResponseEntity<Student> saveStudent(@RequestBody Student student){
        return new ResponseEntity<Student>(studentJDBCTemplateService.saveStudent(student), HttpStatus.CREATED);
    }

    // build get all students REST API
    // http://localhost:8080/jdbcTemplate/students/listAllStudents
    @GetMapping("/listAllStudents")
    public List<Student> getAllStudents(){
        return studentJDBCTemplateService.getAllStudents();
    }

    // build get student by id REST API
    // http://localhost:8080/api/students/1
    @GetMapping("{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable("id") long studentId){
        try {
            return new ResponseEntity<Student>(studentJDBCTemplateService.getStudentById(studentId), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<Student>(new Student(), HttpStatus.NOT_FOUND);
        }
    }

    // build update student REST API
    // http://localhost:8080/jdbcTemplate/students/1
    @PutMapping("{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable("id") Long id
            ,@RequestBody Student student){
        try {
            Student st = studentJDBCTemplateService.getStudentById(id);
            return new ResponseEntity<Student>(studentJDBCTemplateService.updateStudent(student.getId(),st.getFirstName(),st.getLastName(),st.getEmail()), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<Student>(new Student(), HttpStatus.NOT_FOUND);
        }
    }

    // build delete student REST API
    // http://localhost:8080/jdbcTemplate/students/1
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable("id") long id){
        try {
            // delete student from DB
            studentJDBCTemplateService.deleteStudent(id);

            return new ResponseEntity<String>("Student deleted successfully!.", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<String>("Student not found", HttpStatus.NOT_FOUND);
        }
    }

    // build get all students REST API
    // http://localhost:8080/jdbcTemplate/students/listAllStudentsByName/{name}
    @GetMapping("/listAllStudentsByFirstName/{name}")
    public List<Student> getAllStudentsByFirstName(@PathVariable("name") String name){

        return studentJDBCTemplateService.getAllStudentsByFirstName(name);
    }

}
