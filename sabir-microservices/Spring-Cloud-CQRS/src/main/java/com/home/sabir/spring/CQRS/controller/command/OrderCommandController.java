package com.home.sabir.spring.CQRS.controller.command;

import com.home.sabir.spring.CQRS.dto.OrderCommandDto;
import com.home.sabir.spring.CQRS.service.OrderCommandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("po")
@ConditionalOnProperty(name = "app.write.enabled", havingValue = "true")
public class OrderCommandController {

    @Autowired
    private OrderCommandService orderCommandService;

    @PostMapping("/sale")
    public OrderCommandDto placeOrder(@RequestBody OrderCommandDto dto){
        this.orderCommandService.createOrder(dto.getUserIndex(), dto.getProductIndex());
        return dto;
    }

    @PutMapping("/cancel-order/{orderId}")
    public void cancelOrder(@PathVariable long orderId){
        this.orderCommandService.cancelOrder(orderId);
    }
}
