package com.home.sabir.spring.jms.controller;


import javax.jms.Queue;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.home.sabir.spring.jms.component.Student;

@RestController
@RequestMapping("/produce")
public class ProducerController  implements ErrorController{

	@Autowired
	private JmsTemplate jmsTemplate;

	@Autowired
	private Queue queue;
	

	@PostMapping("/message")
	public Student sendMessage(@RequestBody Student student) {

		try {
			ObjectMapper mapper = new ObjectMapper();
			String studentAsJson = mapper.writeValueAsString(student);

			jmsTemplate.convertAndSend(queue, studentAsJson);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return student;
	}
	
	@GetMapping("/welcomeJMS")
	public String welcomeJMS(){
		return "Welcome JMS";
	}
	
	@RequestMapping(value = "/error")
    public String error(HttpServletRequest request) {
		return getStatus(request) +" No Mapping Found";
    }
	
	@Override
    public String getErrorPath() {
        // TODO Auto-generated method stub
        return "No Mapping Found";
    }
   
	private boolean getTraceParameter(HttpServletRequest request) {
        String parameter = request.getParameter("trace");
        if (parameter == null) {
            return false;
        }
        return !"false".equals(parameter.toLowerCase());
    }
	
	
	private HttpStatus getStatus(HttpServletRequest request) {
        Integer statusCode = (Integer) request
                .getAttribute("javax.servlet.error.status_code");
        if (statusCode != null) {
            try {
                return HttpStatus.valueOf(statusCode);
            }
            catch (Exception ex) {
            }
        }
        return HttpStatus.INTERNAL_SERVER_ERROR;
    }
}
