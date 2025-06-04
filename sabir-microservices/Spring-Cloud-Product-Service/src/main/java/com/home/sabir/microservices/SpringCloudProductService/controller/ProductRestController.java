package com.home.sabir.microservices.SpringCloudProductService.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.home.sabir.microservices.SpringCloudProductService.component.Product;

@RestController
@RequestMapping("/product")
public class ProductRestController  {

	Logger logger = LoggerFactory.getLogger(ProductRestController.class);
	
	@Autowired
    Environment environment;

    @GetMapping("/data")
    public String getProductData() {
       return "PRODUCT-SERVICE, Running on port: "
         +environment.getProperty("local.server.port");
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id) {
       return new Product(id, "Head First Java", Double.valueOf(500.75));
    }

    @GetMapping("/listAll")
    public List<Product> getAll(){
       return List.of(
              new Product(Long.valueOf(501), "Head First Java", Double.valueOf(439.75)),
              new Product(Long.valueOf(502), "Spring in Action", Double.valueOf(340.75)),
              new Product(Long.valueOf(503), "Hibernate in Action", Double.valueOf(355.75))
       );
    }

    @GetMapping("/entity")
    public ResponseEntity<String> getEntityData() {
       return new ResponseEntity<String>(
         "Hello from ProductRestController", 
          HttpStatus.OK);
    }
}