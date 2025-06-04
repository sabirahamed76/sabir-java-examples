sabir-microservices
-----------------------


Topics
------
	Eureka Server (Service Registry)
		- Include spring-cloud-starter-netflix-eureka-server maven dependency for enable Service Registry 
	
	Cloud Config Server (Cloud Config Server)	
		- Include spring-cloud-config-server maven dependency for enable config server 
		- Include all services.yaml files in config folder
		- Refer https://javatechonline.com/how-to-implement-spring-cloud-config-server-in-microservices/
			
	API Gateway  & Load Balancing (API Gateway)
		- Include spring-cloud-starter-gateway maven dependency
		- Include all cloud.gateway.routes load balancing in application.yaml file
		- Refer https://javatechonline.com/implement-api-gateway-spring-boot-microservices/
		
	Discovery Client (Department, Employee, Product, Cart, Payment Service)
		- Refer  https://javatechonline.com/how-to-register-microservices-in-eureka-server/
		
	Web Client & Load Balancing (Department, Employee Service)
		- To include spring-boot-starter-webflux maven dependency for enable webclient and HTTPExchange
		- Refer https://github.com/shabbirdwd53/springboot-3-microservices
				https://www.youtube.com/watch?v=HFl2dzhVuUo
	
	Feign Client (Listing Service)
		- Include spring-cloud-starter-openfeign maven Dependency
		- Refer https://javatechonline.com/how-to-implement-feign-client-in-spring-boot-microservices/
	
	Swagger OpenAPI Integration(Customer Service)
		- Include springdoc-openapi-starter-webmvc-ui maven dependency
	
	Fault Tolerance (Listing Service)
		- Circuit Breaker, RateLimit, Retry, Bulk Head, TimeLimit
		- Include resilience4j-spring-boot3 maven dependency
		- Refer https://javatechonline.com/fault-tolerance-in-microservices-resilience4j-spring-boot/
	
	Slueth and Zipkin for Distributed Tracing with TraceId and SpanId
		- Include springdoc-openapi-starter-webmvc-ui and zipkin-reporter-brave maven dependency
		- Add management.tracing.sampling.probability=1.0 in application. properties for 100% management sampling exposure to zipkin
	
	Kafka Message send and receive (Kafka)
		-Include org.springframework.kafka and spring-boot-configuration-processor
	
	SAGA Orchestration (SAGA)
	
	RabitMQ
	
	Caching with Redis
	
	
	Docker & kubernetes (Spring-Cloud-Order-Service)

		https://www.youtube.com/watch?v=pIPji3_rYPY
		https://github.com/Java-Techie-jt/springboot-crud-k8s

	CQRS
	
	ELK-Stack

Execution Steps
----------------
- Service Registry
	mvn clean install package
	docker build -t sabirahamed76/spring-cloud-service-registry:0.0.1 .
	docker run -d -p8761:8761 --name service-registry sabirahamed76/spring-cloud-service-registry:0.0.1 
	http://localhost:8761/ - access the eureka server console  
	http://localhost:8761/actuator - access the actuator
	http://localhost:8761/server/data - access the environement variable
	http://localhost:8761/server/getServices - list all services registered
	


- Config Server http://localhost:8050


- API Gateway
	http://localhost:8060/employee
	http://localhost:8060/department
	http://localhost:8060/department/with-employees
	
	

- Discovery Client Department Service
	http://localhost:8081/department/data
	GET http://localhost:8081/department
	
	POST http://localhost:8081/department
	{
		"id": 1,
		"name": "dept1"
	}
	
	GET http://localhost:8081/department/1
	
	GET http://localhost:8081/department/with-employees

- Discovery Client Employee Service

	http://localhost:8082/employee/data
	GET http://localhost:8082/employee
	
	POST http://localhost:8082/employee
	{
		"id": 1,
		"departmentId": 1,
		"name": "sabeer",
		"age": 50,
		"position": "manager"
	}

	GET http://localhost:8082/employee/department/1

- Discovery Client Product Service
	http://localhost:8083/product/data
	http://localhost:8083/product/product
	http://localhost:8083/product/1
	http://localhost:8083/product/listAll
	http://localhost:8083/product/entity

	http://PRODUCT-SERVICE:8083/product/data
	http://PRODUCT-SERVICE:8083/product/501

- Discovery Client Cart Service 
	http://localhost:8084/cart/data
	http://localhost:8084/cart/getCartData


- Discovery Client Payment Service  
	http://localhost:8085/payment/data
	http://localhost:8085/payment/getCartService


- Feign Client Listing Consumer Service
	http://localhost:8086/listing/data
	http://localhost:8086/listing/getProductService
	http://localhost:8086/listing/getAllProducts
	http://localhost:8086/listing/getProduct/1
	http://localhost:8086/listing/entityData
	
	http://localhost:8086/listing/getProductByRestTemplate/501
	http://localhost:8086/listing/getProductByWebTemplate/501

	http://LISTING-SERVICE/listing/data
	http://LISTING-SERVICE/listing/getProductByRestTemplate/501


- Swagger Client Customer Service 
	http://localhost:8087/customer/data
	http://localhost:8087/sabir-swagger-customer-documentation
	http://localhost:8087/sabir-swagger-customer-api-docs



- Discovery Client Order Service  
	http://localhost:8088/orders/data
	http://localhost:8086/orders
	http://localhost:8086/orders/1