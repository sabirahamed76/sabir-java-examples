package com.home.sabir.springboot.SpringData.service;

import com.home.sabir.springboot.SpringData.entity.Student;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface StudentR2DBCService {

    public Flux<Student> getAllStudents();

    public Mono<Student> getStudentById(int studentId);

    public Mono<Student> createStudent(final Student student);

    public Mono<Student> updateStudent(int studentId, final Mono<Student> studentMono);

    public Mono<Void> deleteStudent(final int id);
    
    public Mono<Long> countStudents();
    
}
