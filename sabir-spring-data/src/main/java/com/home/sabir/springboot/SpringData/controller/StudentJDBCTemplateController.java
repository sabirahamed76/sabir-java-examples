package com.home.sabir.springboot.SpringData.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.home.sabir.springboot.SpringData.service.StudentJDBCTemplateService;

@RestController
@RequestMapping("/jdbcTemplate/students")
public class StudentJDBCTemplateController {

	@Autowired
    private StudentJDBCTemplateService studentJDBCTemplateService;
	
}
