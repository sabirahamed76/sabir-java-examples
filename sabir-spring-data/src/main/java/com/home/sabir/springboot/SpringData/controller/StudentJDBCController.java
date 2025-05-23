package com.home.sabir.springboot.SpringData.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.home.sabir.springboot.SpringData.service.StudentJDBCService;

@RestController
@RequestMapping("/jdbc/students")
public class StudentJDBCController {

	@Autowired
    private StudentJDBCService studentJDBCService;
}
