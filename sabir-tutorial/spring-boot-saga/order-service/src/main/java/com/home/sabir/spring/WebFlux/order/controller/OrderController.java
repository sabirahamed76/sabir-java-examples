package com.home.sabir.spring.WebFlux.order.controller;

import com.home.sabir.spring.WebFlux.dto.OrderRequestDTO;
import com.home.sabir.spring.WebFlux.dto.OrderResponseDTO;
import com.home.sabir.spring.WebFlux.order.entity.PurchaseOrder;
import com.home.sabir.spring.WebFlux.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("order")
public class OrderController {

    @Autowired
    private OrderService service;

    @PostMapping("/create")
    public Mono<PurchaseOrder> createOrder(@RequestBody Mono<OrderRequestDTO> mono){
        return mono
                .flatMap(this.service::createOrder);
    }

    @GetMapping("/all")
    public Flux<OrderResponseDTO> getOrders(){
        return this.service.getAll();
    }

}
