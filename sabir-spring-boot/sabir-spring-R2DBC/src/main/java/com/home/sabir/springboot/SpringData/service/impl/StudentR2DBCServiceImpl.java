package com.home.sabir.springboot.SpringData.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.home.sabir.springboot.SpringData.entity.Student;
import com.home.sabir.springboot.SpringData.repository.StudentR2DBCRepository;
import com.home.sabir.springboot.SpringData.service.StudentR2DBCService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Transactional
public class StudentR2DBCServiceImpl implements  StudentR2DBCService{


    private StudentR2DBCRepository studentR2DBCRepository;

    private StudentR2DBCServiceImpl(StudentR2DBCRepository studentR2DBCRepository){
        this.studentR2DBCRepository=studentR2DBCRepository;
    }

    public Flux<Student> getAllStudents(){
        return this.studentR2DBCRepository.findAll();
    }

    public Mono<Student> getStudentById(int studentId){
        return this.studentR2DBCRepository.findById(Long.valueOf(studentId));
    }

    public Mono<Student> createStudent(final Student student){
        return this.studentR2DBCRepository.save(student);
    }

    public Mono<Student> updateStudent(int studentId, final Mono<Student> studentMono){
        return this.studentR2DBCRepository.findById(Long.valueOf(studentId))
                .flatMap(s -> studentMono.map(u -> {
                    s.setEmail(u.getEmail());
                    return s;
                }))
                .flatMap(p -> this.studentR2DBCRepository.save(p));
    }

    public Mono<Void> deleteStudent(final int id){
        return this.studentR2DBCRepository.deleteById(Long.valueOf(id));
    }
    
    public Mono<Long> countStudents() {
    	return this.studentR2DBCRepository.count();
    };
    
}
