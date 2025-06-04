package com.home.sabir.springboot.SpringData.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.home.sabir.springboot.SpringData.component.StudentFees;
import com.home.sabir.springboot.SpringData.entity.Student;
import com.home.sabir.springboot.SpringData.repository.StudentJDBCTemplateRepository;
import com.home.sabir.springboot.SpringData.service.StudentJDBCTemplateService;

@Service
@Transactional
public class StudentJDBCTemplateServiceImpl implements  StudentJDBCTemplateService{


	private StudentFees studentFees;



    /*
	 * //Setter Based Dependency Injection
	 * 
	 * @Autowired
	 * 
	 * @Qualifier ("PublicSchoolStudentFees") public void setStudentFees(StudentFees
	 * studentFees) { this.studentFees = studentFees; }
	 */
	
	// constructor based DI
	public StudentJDBCTemplateServiceImpl(@Qualifier("PrivateSchoolStudentFees") StudentFees studentFees) {
		this.studentFees = studentFees;
	}
	
	@Autowired
	private StudentJDBCTemplateRepository studentJDBCTemplateRepository;

    
    public Student saveStudent(Student student) {
    	Double fees=studentFees.getFees();
        return studentJDBCTemplateRepository.saveStudent(student);
    }

    public Student updateStudent(Long id, String firstName, String lastName, String email) {
        return studentJDBCTemplateRepository.updateStudent(id, firstName,lastName, email);
    }

    public void deleteStudent(Long id) {
        studentJDBCTemplateRepository.deleteStudent(id);
    }
    
    public int countStudents() {
    	return studentJDBCTemplateRepository.countStudents();
    }
    
    public Student getStudentById(Long id) {
        return studentJDBCTemplateRepository.getStudentById(id);
    }
    
    public Student findByStudentId (Long id) {
    	return studentJDBCTemplateRepository.findByStudentId(id);
    }
    

    public Student findByStudentIdDirect (Long id) {
    	return studentJDBCTemplateRepository.findByStudentIdDirect(id);
    }
    
    
    public List<Student> getAllStudents() {
        return studentJDBCTemplateRepository.getAllStudents();
    }
    
    public List<Student> listAllStudents() {
    	return studentJDBCTemplateRepository.listAllStudents();
    }

    public List<Student> getAllStudentsByFirstName(String firstName) {
        return studentJDBCTemplateRepository.getAllStudentsByFirstName(firstName);
    }
    
    
}
