package com.home.sabir.springboot.SpringData.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.home.sabir.springboot.SpringData.entity.Student;
import com.home.sabir.springboot.SpringData.service.StudentJPAService;

@Controller
public class StudentWebController {
	
	@Autowired
	private StudentJPAService studentJPAService;

	public StudentWebController(StudentJPAService studentJPAService) {
		super();
		this.studentJPAService = studentJPAService;
	}
	
	// handler method to handle list students and return mode and view
	@GetMapping("/students")
	public String listStudents(Model model) {
		model.addAttribute("students", studentJPAService.getAllStudents());
		return "students";
	}
	
	// create student object to hold student form data
	@GetMapping("/students/new")
	public String createStudentForm(Model model) {		
		Student student = new Student();
		model.addAttribute("student", student);
		return "create_student";
		
	}
	
	@PostMapping("/students")
	public String saveStudent(@ModelAttribute("student") Student student) {
		studentJPAService.saveStudent(student);
		return "redirect:/students";
	}
	
	@GetMapping("/students/edit/{id}")
	public String editStudentForm(@PathVariable Long id, Model model) {
		model.addAttribute("student", studentJPAService.getStudentById(id));
		return "edit_student";
	}

	// handler method to handle update student request
	@PostMapping("/students/{id}")
	public String updateStudent(@PathVariable Long id,
			@ModelAttribute("student") Student student,
			Model model) {
		
		// get student from database by id
		Student existingStudent = studentJPAService.getStudentById(id);
		
		existingStudent.setFirstName(student.getFirstName());
		existingStudent.setLastName(student.getLastName());
		existingStudent.setEmail(student.getEmail());
		
		// save updated student object
		studentJPAService.updateStudent(existingStudent,id);
		return "redirect:/students";		
	}
	
	// handler method to handle delete student request	
	@GetMapping("/students/{id}")
	public String deleteStudent(@PathVariable Long id) {
		studentJPAService.deleteStudentById(id);
		return "redirect:/students";
	}
	
}
