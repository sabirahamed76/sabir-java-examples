package com.home.sabir.microservices.CustomerService.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.home.sabir.microservices.CustomerService.model.Customer;

@Repository
public class CustomerRepository {

    private List<Customer> customers
            = new ArrayList<>();

    public Customer add(Customer customer) {
    	customers.add(customer);
        return customer;
    }

    public Customer findById(Long id) {
        return customers.stream()
                .filter(a -> a.id().equals(id))
                .findFirst()
                .orElseThrow();
    }

    public List<Customer> findAll() {
        return customers;
    }

}
