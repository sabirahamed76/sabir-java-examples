package com.home.sabir.spring.CQRS.service.impl;

import com.home.sabir.spring.CQRS.entity.Product;
import com.home.sabir.spring.CQRS.entity.PurchaseOrder;
import com.home.sabir.spring.CQRS.entity.User;
import com.home.sabir.spring.CQRS.repository.ProductRepository;
import com.home.sabir.spring.CQRS.repository.PurchaseOrderRepository;
import com.home.sabir.spring.CQRS.repository.UserRepository;
import com.home.sabir.spring.CQRS.service.OrderCommandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.*;
import java.util.List;
import java.time.LocalDate;
import java.util.Date;

@Service
public class OrderCommandServiceImpl implements OrderCommandService {

    private static final long ORDER_CANCELLATION_WINDOW = 30;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private PurchaseOrderRepository purchaseOrderRepository;

    private List<User> users;
    private List<Product> products;

    private LocalDate orderDate;


    @PostConstruct
    private void init(){
        this.users = this.userRepository.findAll();
        this.products = this.productRepository.findAll();
    }

    @Override
    public void createOrder(int userIndex, int productIndex) {
        LocalDate localDate = LocalDate.now();
        Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        PurchaseOrder purchaseOrder = new PurchaseOrder();
        purchaseOrder.setProductId(this.products.get(productIndex).getId());
        purchaseOrder.setUserId(this.users.get(userIndex).getId());
        purchaseOrder.setOrderDate(date);
        this.purchaseOrderRepository.save(purchaseOrder);
    }

    @Override
    public void cancelOrder(long orderId) {

        this.purchaseOrderRepository.findById(orderId)
                .ifPresent(purchaseOrder -> {
                    Period period = Period.between(LocalDate.of(2025, 5, 27), LocalDate.of(2025, 6, 1));
                    LocalDate localDate = purchaseOrder.getOrderDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                    if (Duration.between(orderDate.atStartOfDay(), LocalDate.now().atStartOfDay()).toDays() <= ORDER_CANCELLATION_WINDOW) {
                        this.purchaseOrderRepository.deleteById(orderId);
                        // Additional logic to issue refund
                    }
                });



    }
}
