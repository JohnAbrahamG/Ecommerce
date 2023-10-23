package com.example.ECommerceSite.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ECommerceSite.Ennum.OrderStatus;
import com.example.ECommerceSite.Exception.OrderNotFoundException;
import com.example.ECommerceSite.Model.Order;
import com.example.ECommerceSite.Repository.OrderRepository;

import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public Order getOrderById(Long orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException("Order not found with ID: " + orderId));
    }

    @Override
    public Order placeOrder(Order order) {
        // Validation logic here (e.g., check if customer name is provided, total amount is positive)
        // Set order status to PENDING by default
        order.setOrderStatus(OrderStatus.PENDING);
        return orderRepository.save(order);
    }

    @Override
    public Order updateOrderStatus(Long orderId, OrderStatus status) {
        Order order = getOrderById(orderId);
        order.setOrderStatus(status);
        return orderRepository.save(order);
    }
}

