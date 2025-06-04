package com.home.sabir.microservices.CloudAPIGateway.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/apigateway")
public class APIGatewayController {

	Logger logger = LoggerFactory.getLogger(APIGatewayController.class);
	
    @Autowired
    Environment environment;

    @GetMapping("/data")
    public String getData() {
        return "API-GATEWAY-SERVICE, Running on port: "
                +environment.getProperty("local.server.port");
    }


}



