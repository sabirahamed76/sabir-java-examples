package com.home.sabir.spring.CQRS.service;

public interface OrderCommandService {
    void createOrder(int userIndex, int productIndex);
    void cancelOrder(long orderId);
}
