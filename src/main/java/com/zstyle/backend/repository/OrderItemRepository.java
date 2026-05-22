package com.zstyle.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.zstyle.backend.entity.OrderItem;
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
