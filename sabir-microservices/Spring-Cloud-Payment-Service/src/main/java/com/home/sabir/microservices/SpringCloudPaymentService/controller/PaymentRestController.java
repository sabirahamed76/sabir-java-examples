package com.home.sabir.microservices.SpringCloudPaymentService.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.home.sabir.microservices.SpringCloudPaymentService.component.CartRestConsumer;


@RestController
@RequestMapping("/payment")
public class PaymentRestController  {

		Logger logger = LoggerFactory.getLogger(PaymentRestController.class);
	
         @Autowired
         private CartRestConsumer consumer;  // HAS-A

         @Autowired
         Environment environment;

         @GetMapping("/data")
         public String getProductData() {
        	 logger.debug("payment-service---");

            return "PAYMENT-SERVICE, Running on port: "
              +environment.getProperty("local.server.port");
         }
         
              
         @GetMapping("/getCartService")
         public String getCartInfo() {
            System.out.println(consumer.getClass().getName());  //prints as a proxy class
            return "Accessing from PAYMENT-SERVICE ==> " +consumer.getCartInfo();
         }

         
         
         
}