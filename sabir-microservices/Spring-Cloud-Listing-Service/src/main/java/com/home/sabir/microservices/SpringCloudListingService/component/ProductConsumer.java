package com.home.sabir.microservices.SpringCloudListingService.component;

import java.util.concurrent.atomic.AtomicReference;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import io.opentelemetry.api.trace.Span;
import reactor.core.publisher.Mono;

@Component
public class ProductConsumer {

	Logger logger = LoggerFactory.getLogger(ProductConsumer.class);
	
	private WebClient webClient = null;
	
	public String getProductByIdThruRestTemplate(Long id) {
		RestTemplate restTemplate = new RestTemplate();
		String response = null;
		Span span = Span.current();
		String traceId = span.getSpanContext().getTraceId();
		String spanId = span.getSpanContext().getSpanId();
		
		logger.debug("-------Trace ID: " + traceId+"-----------------Span ID: " + spanId);
		HttpHeaders headers = new HttpHeaders();
		headers.set("traceparent", "00-80e1afed08e019fc1110464cfa66635c-7a085853722dc6d2-01");
		HttpEntity<String> entity = new HttpEntity<>(headers);
		//ResponseEntity<String> response = restTemplate.exchange("http://PRODUCT-SERVICE:8083/product/"+id, HttpMethod.GET, entity, String.class);
		
        
		response = restTemplate.getForObject("http://PRODUCT-SERVICE:8083/product/"+id, String.class);
        return response;
    }

	public String getProductByIdThruWebClient(Long id) {
		Span span = Span.current();
		String traceId = span.getSpanContext().getTraceId();
		String spanId = span.getSpanContext().getSpanId();
		
		logger.debug("-------Trace ID: " + traceId+"-----------------Span ID: " + spanId);
		webClient = WebClient.create("http://PRODUCT-SERVICE:8083");
	
		//AtomicReference<String> response = new AtomicReference<>();
		
		//String response = "";
		/*
		 * webClient.get() .uri("/product/"+id) .retrieve() .bodyToMono(String.class)
		 * .map(value -> { response.set(value); return value; }) // Ensures response is
		 * set only if non-null .subscribe();
		 */
		String response=webClient.get()
			    .uri("/product/" + id)
			    .retrieve()
			    .bodyToMono(String.class)
			    .block(); // Blocking call to get the value

		System.out.println("Received Response: " + response);
		logger.debug("-------response.get(): " + response);
		
        return response;
    }
	
}
