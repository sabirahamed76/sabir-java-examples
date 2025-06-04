package com.home.sabir.spring.WebFlux.inventory.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.home.sabir.spring.WebFlux.dto.InventoryRequestDTO;
import com.home.sabir.spring.WebFlux.dto.InventoryResponseDTO;
import com.home.sabir.spring.WebFlux.inventory.service.InventoryService;

@RestController
@RequestMapping("inventory")
public class InventoryController {

	Logger logger = LoggerFactory.getLogger(InventoryController.class);
	
    @Autowired
    private InventoryService service;

    @PostMapping("/deduct")
    public InventoryResponseDTO deduct(@RequestBody final InventoryRequestDTO requestDTO){
        return this.service.deductInventory(requestDTO);
    }

    @PostMapping("/add")
    public void add(@RequestBody final InventoryRequestDTO requestDTO){
        this.service.addInventory(requestDTO);
    }

}
