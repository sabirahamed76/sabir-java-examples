package com.home.sabir.microservices.SpringCloudProductService.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.home.sabir.microservices.SpringCloudProductService.component.Product;
import com.home.sabir.microservices.SpringCloudProductService.repository.ProductRepository;
import com.home.sabir.microservices.SpringCloudProductService.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService{


  @Autowired
  ProductRepository productRepository;
  
  @Override
	public List<Product> getAllProducts() {
	  	List<Product> productList = new ArrayList<>();
	    productRepository.findAll().forEach(productList::add);
	    return productList;
	}

  @Cacheable(value = "products", key = "#id")
  public Optional<Product> getProductById(Long id) {
    return productRepository.findById(id);
  }

  @CachePut(cacheNames = "products", key = "#product.id")
  public Product updateProduct(Product product) {
    return productRepository.save(product);
  }

  public void saveProduct(Product product) {
    productRepository.save(product);
  }
}