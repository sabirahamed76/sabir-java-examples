package com.home.sabir.spring.caching.repository;

import com.home.sabir.spring.caching.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}