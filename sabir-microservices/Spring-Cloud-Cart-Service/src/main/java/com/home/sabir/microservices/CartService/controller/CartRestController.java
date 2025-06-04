package com.home.sabir.microservices.CartService.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cart")
public class CartRestController {

	Logger logger = LoggerFactory.getLogger(CartRestController.class);
	
	@Autowired
    Environment environment;

    @GetMapping("/data")
    public String getServerData() {
       return "CART-SERVICE, Running on port: "
         +environment.getProperty("local.server.port");
    }
    
      @GetMapping("/getCartData")
      public String getCartData() {
                 return "Returning data from CART-SERVICE";
      }
}