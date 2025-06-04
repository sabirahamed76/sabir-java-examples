package com.home.sabir.microservices.SpringCloudProductService.service;


import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.home.sabir.microservices.SpringCloudProductService.component.Product;

@Service
public interface ProductService {

  public List<Product> getAllProducts();

  public Optional<Product> getProductById(Long id);

  public Product updateProduct(Product product);

  public void saveProduct(Product product);
}