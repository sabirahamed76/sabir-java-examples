package com.home.sabir.microservices.CloudServiceRegistry.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/server")
public class ServiceRegistryRestController  {

		Logger logger = LoggerFactory.getLogger(ServiceRegistryRestController.class);
	
        @Autowired
        Environment environment;
        
        @Autowired
        private DiscoveryClient discoveryClient;

        @GetMapping("/getServices")
        public String discoverServices() {
        	List<String> services = discoveryClient.getServices();
            String servicesString = services.stream().collect(Collectors.joining(", "));
            
            return "EUREKA-SERVER, Available Services: " + servicesString;
        }

        @GetMapping("/data")
        public String getServerData() {
           return "EUREKA-SERVER, Running on port: "
             +environment.getProperty("local.server.port");
        }
        
        
        

         
         
}