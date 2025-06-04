package com.home.sabir.microservices.SpringCloudListingService.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.home.sabir.microservices.SpringCloudListingService.component.ProductConsumer;
import com.home.sabir.microservices.SpringCloudListingService.component.ProductFeignConsumer;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;

@RestController
@RequestMapping("/listing")
public class ListingRestController  {

		Logger logger = LoggerFactory.getLogger(ListingRestController.class);
	
         @Autowired
         private ProductFeignConsumer consumer;  // HAS-A
         
         @Autowired
         private ProductConsumer productConsumer; 
         
         @Autowired
         Environment environment;

         @GetMapping("/data")
         public String getListingInfo() {
            return "LISITING-SERVICE, Running on port: "
              +environment.getProperty("local.server.port");
         }
         
         @GetMapping("/getProductService")
         public String getProductInfo() {
            System.out.println(consumer.getClass().getName());  //prints as a proxy class
            return "Accessing from LISTING-SERVICE ==> " +consumer.getProductData();
         }
         
         @GetMapping("/getProductByRestTemplate/{id}")
         public String getProductByRestTemplate(@PathVariable Integer id) {
        	 return "Accessing from LISTING-SERVICE getProductByIdThruRestTemplate==> " + productConsumer.getProductByIdThruRestTemplate(Long.valueOf(id)); 
         }
         
         @GetMapping("/getProductByWebTemplate/{id}")
         public String getProductByWebTemplate(@PathVariable Integer id) {
        	 return "Accessing from LISTING-SERVICE getProductByIdThruWebClient==> " + productConsumer.getProductByIdThruWebClient(Long.valueOf(id)); 
         }
         

         @GetMapping("/getAllProducts")
         @CircuitBreaker(name = "getAllProductsCB", fallbackMethod = "getAllProductsFallbackCB")
         @RateLimiter(name = "getAllProductsRateLimit", fallbackMethod = "getAllProductsFallBackRL")
         @Retry(name = "getAllProductsRetry", fallbackMethod = "getAllProductsFallbackRetry") 
         @Bulkhead(name = "getAllProductsBH", fallbackMethod = "getAllProductsFallBackBH")         
          public String getAllProducts() {
        	logger.info("---Accessing from LISTING-SERVICE getAllProducts==>");
            return "Accessing from LISTING-SERVICE ==> " + consumer.getAllProducts();
         }
         
         public String getAllProductsFallbackCB(Exception e) {
             logger.info("---RESPONSE FROM FALLBACK METHOD getAllProductsCB---");
             return "CIRCUIT BREAK ...SERVICE IS DOWN, PLEASE TRY AFTER SOMETIME !!!";
          }
         
         public String getAllProductsRLFallbackRL(Exception e) {
             logger.info("---RESPONSE FROM FALLBACK METHOD getAllProductsRL---");
             return "RATE LIMIT EXCEEDS.....SERVICE IS DOWN, PLEASE TRY AFTER SOMETIME !!!";
          }
         
         public String getAllProductsRetryFallbackRetry(Exception e) {
             logger.info("---RESPONSE FROM FALLBACK METHOD getAllProductsRetryFallbackRetry---");
             return "RETRY EXCEEDS...SERVICE IS DOWN, PLEASE TRY AFTER SOMETIME !!!";
          }
         
         public String getAllProductsRetryBHRetry(Exception e) {
             logger.info("---RESPONSE FROM FALLBACK METHOD getAllProductsBH---");
             return "CONCURRENT REQUEST EXCEEDS...SERVICE IS DOWN, PLEASE TRY AFTER SOMETIME !!!";
          }

         @GetMapping("/getProduct/{id}")
         public String getProductDetail(@PathVariable Integer id) {
            return "Accessing from LISTING-SERVICE ==> " + consumer.getProductById(Long.valueOf(id)); 
         }

         @GetMapping("/entityData")
         public String printEntityData() {
            ResponseEntity<String> resp = consumer.getEntityData();
            return "Accessing from LISTING-SERVICE ==> " + resp.getBody() +" , status is:" + resp.getStatusCode();
         }
         
         
}