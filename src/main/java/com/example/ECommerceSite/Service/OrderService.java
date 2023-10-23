package com.example.ECommerceSite.Service;

import java.util.List;

import com.example.ECommerceSite.Ennum.OrderStatus;
import com.example.ECommerceSite.Model.Order;

public interface OrderService {
    List<Order> getAllOrders();
    Order getOrderById(Long orderId);
    Order placeOrder(Order order);
    Order updateOrderStatus(Long orderId, OrderStatus status);
}
