package com.home.sabir.spring.WebFlux.dto;

import lombok.Data;

import java.util.UUID;

import com.home.sabir.spring.WebFlux.enums.InventoryStatus;

@Data
public class InventoryResponseDTO {

    private UUID orderId;
    private Integer userId;
    private Integer productId;
    private InventoryStatus status;

}
