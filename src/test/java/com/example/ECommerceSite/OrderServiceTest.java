package com.example.ECommerceSite;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.example.ECommerceSite.Ennum.OrderStatus;
import com.example.ECommerceSite.Model.Order;
import com.example.ECommerceSite.Repository.OrderRepository;
import com.example.ECommerceSite.Service.OrderServiceImpl;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Collections.*;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class OrderServiceTest {

    @InjectMocks
    private OrderServiceImpl orderService;

    @Mock
    private OrderRepository orderRepository;

    private Order testOrder;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        
        testOrder.setOrderId(1L);
        testOrder.setCustomerName("Test Customer");
        testOrder.setOrderDate(new Date());
        testOrder.setTotalAmount(new BigDecimal("100.00"));
        testOrder.setOrderStatus(OrderStatus.PENDING);
    }

    @Test
    public void testGetAllOrders() {
        Mockito.when(orderRepository.findAll()).thenReturn(Collections.singletonList(testOrder));
        List<Order> orders = orderService.getAllOrders();

        assertEquals(1, orders.size());
        assertEquals(testOrder, orders.get(0));
    }

    @Test
    public void testGetOrderById() {
        Mockito.when(orderRepository.findById(1L)).thenReturn(Optional.of(testOrder));
        Order retrievedOrder = orderService.getOrderById(1L);

        assertEquals(testOrder, retrievedOrder);
    }

    @Test
    public void testPlaceOrder() {
        Mockito.when(orderRepository.save(Mockito.any(Order.class))).thenReturn(testOrder);
        Order placedOrder = orderService.placeOrder(testOrder);

        assertEquals(testOrder, placedOrder);
    }

    @Test
    public void testUpdateOrderStatus() {
        Mockito.when(orderRepository.findById(1L)).thenReturn(Optional.of(testOrder));
        Mockito.when(orderRepository.save(Mockito.any(Order.class))).thenReturn(testOrder);

        Order updatedOrder = orderService.updateOrderStatus(1L, OrderStatus.CONFIRMED);


    }
}

