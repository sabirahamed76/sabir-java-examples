package com.home.sabir.test.service;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.home.sabir.springboot.datajpa.entity.Student;
import com.home.sabir.springboot.datajpa.service.StudentService;

@SpringBootTest
public class StudentServiceTest {


    @Autowired
    private StudentService studentService;
    
    public StudentServiceTest() {}

    @Test
    public void saveStudent() {
        Student student = Student.builder()
                .email("shabbir@gmail.com")
                .firstName("Shabbir")
                .lastName("Dawoodi")
                .build();

        studentService.saveStudent(student);
    }

    
    @Test
    public void printAllStudent() {
        List<Student> studentList =
        		studentService.getAllStudents();

        System.out.println("studentList = " + studentList);
    }
    
    
    @Test
    public void getStudentById(Long studentId) {
    	Student student=studentService.getStudentById(studentId);
    	System.out.println("student = " + student);
    }
    
    
    @Test
    public void printStudentByFirstName() {
        
        List<Student> students =
        		studentService.getStudentByName("shivam");

        System.out.println("students = " + students);
    }



    @Test
    public void updateStudent(Student student, Long id) {
    	studentService.updateStudent(student, id);
    	
    	System.out.println("student = " + student);
    	
    }

}