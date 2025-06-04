package com.home.sabir.spring.WebFlux.dto;

import lombok.Data;

import java.util.UUID;

import com.home.sabir.spring.WebFlux.enums.PaymentStatus;

@Data
public class PaymentResponseDTO {
    private Integer userId;
    private UUID orderId;
    private Double amount;
    private PaymentStatus status;
}
