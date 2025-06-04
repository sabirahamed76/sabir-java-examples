package com.home.sabir.springboot.SpringData.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
import com.home.sabir.springboot.SpringData.service.StudentR2DBCService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@RestController
@RequestMapping("/r2dbc/students")
public class StudentR2DBCController {

	Logger logger = LoggerFactory.getLogger(StudentR2DBCController.class);
	
    @Autowired
    private StudentR2DBCService studentR2DBCService;

    @GetMapping("all")
    public Flux<Student> getAll(){
        return this.studentR2DBCService.getAllStudents();
    }

    @GetMapping("{studentId}")
    public Mono<ResponseEntity<Student>> getStudentById(@PathVariable int studentId){
        return this.studentR2DBCService.getStudentById(studentId)
                                .map(ResponseEntity::ok)
                                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Mono<Student> createStudent(@RequestBody Mono<Student> studentMono){
        return studentMono.flatMap(this.studentR2DBCService::createStudent);
    }

    @PutMapping("{studentId}")
    public Mono<Student> updateStudent(@PathVariable int studentId,
                                       @RequestBody Mono<Student> studentMono){
        return this.studentR2DBCService.updateStudent(studentId, studentMono);
    }

    @DeleteMapping("{studentId}")
    public Mono<Void> deleteStudent(@PathVariable int studentId){
        return this.studentR2DBCService.deleteStudent(studentId);
    }

}