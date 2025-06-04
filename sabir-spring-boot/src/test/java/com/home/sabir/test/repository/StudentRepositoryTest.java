package com.home.sabir.test.repository;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.home.sabir.springboot.datajpa.entity.Student;
import com.home.sabir.springboot.datajpa.repository.StudentRepository;

@SpringBootTest
public class StudentRepositoryTest {

    @Autowired
    private StudentRepository studentRepository;
    
    public StudentRepositoryTest() {}

    @Test
    public void saveStudent() {
        Student student = Student.builder()
                .email("shabbir@gmail.com")
                .firstName("Shabbir")
                .lastName("Dawoodi")
                .build();

        studentRepository.save(student);
    }

    
    @Test
    public void printAllStudent() {
        List<Student> studentList =
                studentRepository.findAll();

        System.out.println("studentList = " + studentList);
    }
    
    @Test
    public void printStudentByFirstName() {
        
        List<Student> students =
                studentRepository.findByFirstName("shivam");

        System.out.println("students = " + students);
    }

    @Test
    public void printStudentByFirstNameContaining() {

        List<Student> students =
                studentRepository.findByFirstNameContaining("sh");

        System.out.println("students = " + students);
    }
    

    @Test
    public void printgetStudentByEmailAddress() {
        Student student =
                studentRepository.getStudentByEmailAddress(
                        "shabbir@gmail.com"
                );

        System.out.println("student = " + student);
    }

    @Test
    public void printgetStudentFirstNameByEmailAddress() {
        String firstName =
                studentRepository.getStudentFirstNameByEmailAddress(
                        "shivam@gmail.com"
                );
        System.out.println("firstName = " + firstName);
    }
    
    @Test
    public void printgetStudentByEmailAddressNative(){
        Student student =
                studentRepository.getStudentByEmailAddressNative(
                        "shivam@gmail.com"
                );

        System.out.println("student = " + student);
    }

    @Test
    public void printgetStudentByEmailAddressNativeNamedParam() {
        Student student =
                studentRepository.getStudentByEmailAddressNativeNamedParam(
                        "shivam@gmail.com"
                );

        System.out.println("student = " + student);
    }

    @Test
    public void updateStudentNameByEmailIdTest() {
        studentRepository.updateStudentNameByEmailId(
                "shabbir dawoodi",
                "shabbir@gmail.com");
    }

}