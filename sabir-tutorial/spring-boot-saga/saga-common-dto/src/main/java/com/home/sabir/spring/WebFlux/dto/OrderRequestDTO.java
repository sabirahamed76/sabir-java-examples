package com.home.sabir.spring.WebFlux.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class OrderRequestDTO {

    private Integer userId;
    private Integer productId;
    private UUID orderId;

}