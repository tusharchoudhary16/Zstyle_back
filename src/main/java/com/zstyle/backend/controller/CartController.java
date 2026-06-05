package com.zstyle.backend.controller;

import com.zstyle.backend.dto.CartRequest;
import com.zstyle.backend.dto.CartResponse;
import com.zstyle.backend.service.CartService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
@CrossOrigin(origins = "*")
public class CartController {

    @Autowired
    private CartService cartService;

    // Add product to cart
    @PostMapping("/add")
    public ResponseEntity<String> addToCart(
            @RequestBody CartRequest request
    ) {
        cartService.addToCart(
                request.getProductId(),
                request.getQuantity()
        );

        return ResponseEntity.ok("Product added to cart");
    }

    // Get complete cart
    @GetMapping
    public ResponseEntity<List<CartResponse>> getCart() {
        return ResponseEntity.ok(
                cartService.getCart()
        );
    }

    // Remove item
    @DeleteMapping("/remove/{productId}")
    public ResponseEntity<String> removeItem(
            @PathVariable Long productId
    ) {
        cartService.removeItem(productId);

        return ResponseEntity.ok("Item removed");
    }
}