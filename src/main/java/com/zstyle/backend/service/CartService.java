package com.zstyle.backend.service;

import com.zstyle.backend.dto.CartResponse;
import com.zstyle.backend.entity.Order;
import com.zstyle.backend.entity.OrderItem;
import com.zstyle.backend.entity.Product;
import com.zstyle.backend.repository.OrderItemRepository;
import com.zstyle.backend.repository.OrderRepository;
import com.zstyle.backend.repository.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CartService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private ProductRepository productRepository;

    // ADD TO CART
    public void addToCart(Long productId, Integer quantity) {

        Order cart = orderRepository
                .findByStatus("CART")
                .orElseGet(() -> {

                    Order newCart = new Order();

                    newCart.setStatus("CART");
                    newCart.setOrderDate(LocalDateTime.now());
                    newCart.setTotalAmount(0.0);

                    return orderRepository.save(newCart);
                });

        Optional<OrderItem> existingItem =
                orderItemRepository.findByOrderIdAndProductId(
                        cart.getId(),
                        productId
                );

        if (existingItem.isPresent()) {

            OrderItem item = existingItem.get();

            item.setQuantity(
                    item.getQuantity() + quantity
            );

            orderItemRepository.save(item);

        } else {

            OrderItem item = new OrderItem();

            item.setOrderId(cart.getId());
            item.setProductId(productId);
            item.setQuantity(quantity);

            orderItemRepository.save(item);
        }
    }

    // GET CART
    public List<CartResponse> getCart() {

        Order cart = orderRepository
                .findByStatus("CART")
                .orElse(null);

        List<CartResponse> response = new ArrayList<>();

        if (cart == null) {
            return response;
        }

        List<OrderItem> items =
                orderItemRepository.findByOrderId(
                        cart.getId()
                );

        for (OrderItem item : items) {

            Product product =
                    productRepository
                            .findById(
                                    item.getProductId().intValue()
                            )
                            .orElse(null);

            if (product != null) {

                response.add(
                        new CartResponse(
                                item.getProductId(),
                                product.getName(),
                                product.getPrice(),
                                item.getQuantity()
                        )
                );
            }
        }

        return response;
    }

    // REMOVE ITEM
    public void removeItem(Long productId) {

        Order cart = orderRepository
                .findByStatus("CART")
                .orElse(null);

        if (cart == null) {
            return;
        }

        Optional<OrderItem> item =
                orderItemRepository
                        .findByOrderIdAndProductId(
                                cart.getId(),
                                productId
                        );

        item.ifPresent(orderItemRepository::delete);
    }
}