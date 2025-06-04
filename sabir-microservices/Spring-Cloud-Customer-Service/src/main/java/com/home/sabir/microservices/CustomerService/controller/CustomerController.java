package com.home.sabir.microservices.CustomerService.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.home.sabir.microservices.CustomerService.model.Customer;
import com.home.sabir.microservices.CustomerService.repository.CustomerRepository;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Customer", description = "Customer management APIs")
@CrossOrigin(origins = "http://localhost:8087")
@RestController
@RequestMapping("/customer")
public class CustomerController {
    private static final Logger LOGGER
            = LoggerFactory.getLogger(CustomerController.class);

    @Autowired
    CustomerRepository customerRepository;
    
    @Autowired
    Environment environment;

    @GetMapping("/data")
    public String getServerData() {
       return "CUSTOMER-SERVICE, Running on port: "
         +environment.getProperty("local.server.port");
    }
    
    @Operation(summary = "Create a new Customer", tags = { "customer", "post" })
    @ApiResponses({
        @ApiResponse(responseCode = "201", content = {
            @Content(schema = @Schema(implementation = Customer.class), mediaType = "application/json") }),
        @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @PostMapping
    public Customer add(@RequestBody Customer customer) {
        LOGGER.info("Customer add: {}", customer);
        return customerRepository.add(customer);
    }

    @Operation(summary = "Retrieve all Customers", tags = { "customers", "get", "filter" })
    @ApiResponses({
        @ApiResponse(responseCode = "200", content = {
            @Content(schema = @Schema(implementation = Customer.class), mediaType = "application/json") }),
        @ApiResponse(responseCode = "204", description = "There are no Tutorials", content = {
            @Content(schema = @Schema()) }),
        @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @GetMapping
    public List<Customer> findAll() {
        LOGGER.info("Customer find");
        return customerRepository.findAll();
    }
    
    @Operation(
    	      summary = "Retrieve a Customer by Id",
    	      description = "Get a Customer object by specifying its id. The response is Customer object with id, title, description and published status.",
    	      tags = { "customers", "get" })
    	  @ApiResponses({
    	      @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = Customer.class), mediaType = "application/json") }),
    	      @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
    	      @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    	  @GetMapping("/{id}")
    	  public ResponseEntity<Customer> getCustomerById(@PathVariable("id") long id) {
		    	Customer customer = customerRepository.findById(id);
		    	LOGGER.info("Customer find: id={}", id);
	    	    if (customer != null) {
	    	      return new ResponseEntity<>(customer, HttpStatus.OK);
	    	    } else {
	    	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    	    }
    	  }



}
