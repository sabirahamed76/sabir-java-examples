package com.home.sabir.microservices.SpringCloudProductService.repository;

import org.springframework.data.repository.CrudRepository;

import com.home.sabir.microservices.SpringCloudProductService.component.Product;

public interface ProductRepository extends CrudRepository<Product, Long>{

}
