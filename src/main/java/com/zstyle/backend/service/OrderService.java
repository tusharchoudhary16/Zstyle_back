package com.zstyle.backend.service;

import java.time.LocalDateTime;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import com.zstyle.backend.repository.OrderRepository;
import com.zstyle.backend.repository.OrderItemRepository;
import com.zstyle.backend.entity.Order;
import com.zstyle.backend.entity.OrderItem;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    public void placeOrder(List<Long> productIds, Double totalAmount) {

        // 1. Save order
//        Order order = new Order();
//        order.setTotalAmount(totalAmount);
//        order.setOrderDate(LocalDateTime.now());
//
//        Order savedOrder = orderRepository.save(order);

        // 2. Save items
//        for (Long productId : productIds) {
//            OrderItem item = new OrderItem();
//            item.setOrderId(savedOrder.getId());
//            item.setProductId(productId);
//            item.setQuantity(1);
//
//            orderItemRepository.save(item);
//        }
    }
}