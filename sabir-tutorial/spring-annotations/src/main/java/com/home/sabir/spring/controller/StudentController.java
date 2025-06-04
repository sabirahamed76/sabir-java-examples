package com.home.sabir.spring.controller;

import com.home.sabir.spring.bean.LazyLoadingBean;
import com.home.sabir.spring.bean.TestBean;
import com.home.sabir.spring.config.MailProps;
import com.home.sabir.spring.entity.Student;
import com.home.sabir.spring.exception.StudentNotFoundException;
import com.home.sabir.spring.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/students")
@PropertySource("classpath:custom.properties")
@Scope("prototype")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private TestBean testBean;

    @Autowired
    private LazyLoadingBean lazyLoadingBean;

    @Value("${mail.from}")
    private String from;
    @Value("${mail.host}")
    private String host;
    @Value("${mail.port}")
    private String port;
    @Value("${message}")
    private String message;

    @Autowired
    private MailProps mailProps;

    public StudentController() {
        System.out.println("controller object created ....");
    }

    @PostMapping("/save")
    public ResponseEntity<Student> addStudent(@RequestBody Student student) {
        return ResponseEntity.ok(studentService.addStudent(student));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Student>> getStudent(@PathVariable int id) throws StudentNotFoundException {
        Optional<Student> student = studentService.getStudent(id);
        if (student.isPresent()) {
            return ResponseEntity.ok(student);
        } else {
            throw new StudentNotFoundException("student not found with id " + id);
        }

    }

    @GetMapping("/all")
    public ResponseEntity<List<Student>> getStudents() {
        System.out.println("mail properties : "+ mailProps);
        return ResponseEntity.ok(studentService.getStudents());
    }

}
