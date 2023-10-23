package com.example.ECommerceSite.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ECommerceSite.Model.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
