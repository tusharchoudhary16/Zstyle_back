package com.zstyle.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.zstyle.backend.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}