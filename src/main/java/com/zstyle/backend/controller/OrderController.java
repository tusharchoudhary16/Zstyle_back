package com.zstyle.backend.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;
import java.util.List;

import com.zstyle.backend.service.OrderService;

@RestController
@RequestMapping("/orders")
@CrossOrigin
public class OrderController {

    @Autowired
    private OrderService orderService;



    @PostMapping
    public String placeOrder(@RequestBody Map<String, Object> data) {

        List<Integer> productIdsInt = (List<Integer>) data.get("productIds");
        List<Long> productIds = productIdsInt.stream()
                .map(Long::valueOf)
                .toList();

        Double totalAmount = Double.valueOf(data.get("totalAmount").toString());

        orderService.placeOrder(productIds, totalAmount);

        return "Order placed!";
    }
}
