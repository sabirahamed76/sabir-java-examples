package com.home.sabir.spring.WebFlux.order.service;

import com.home.sabir.spring.WebFlux.dto.OrchestratorResponseDTO;
import com.home.sabir.spring.WebFlux.order.repository.PurchaseOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class OrderEventUpdateService {

    @Autowired
    private PurchaseOrderRepository repository;

    public Mono<Void> updateOrder(final OrchestratorResponseDTO responseDTO){
        return this.repository.findById(responseDTO.getOrderId())
                .doOnNext(p -> p.setStatus(responseDTO.getStatus()))
                .flatMap(this.repository::save)
                .then();
    }

}
