package com.home.sabir.springboot.SpringMVC.controller;


import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class ActuatorController {

    @GetMapping("/details")
    public String getDetails() {
        return "redirect:/details";
    }
}