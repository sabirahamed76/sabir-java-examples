package com.home.sabir.microservices.SpringCloudListingService.component;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="PRODUCT-SERVICE")
public interface ProductFeignConsumer {

	@GetMapping("/product/data")
    public String getProductData();

    @GetMapping("/product/{id}")
    public Product getProductById(@PathVariable Long id);

    @GetMapping("/product/listAll")
    public List<Product> getAllProducts();

    @GetMapping("/product/entity")
    public ResponseEntity<String> getEntityData();
}