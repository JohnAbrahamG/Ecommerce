package com.example.ECommerceSite;


import com.example.ECommerceSite.Controller.OrderController;
import com.example.ECommerceSite.Ennum.OrderStatus;
import com.example.ECommerceSite.Model.Order;
import com.example.ECommerceSite.Service.OrderService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@AutoConfigureMockMvc
public class OrderControllerTest {

    @InjectMocks
    private OrderController orderController;

    @Mock
    private OrderService orderService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private Order testOrder;

    @BeforeEach
    public void setUp() {
        
        testOrder.setOrderId(1L);
        testOrder.setCustomerName("Test Customer");
        testOrder.setOrderDate(new Date());
        testOrder.setTotalAmount(new BigDecimal("100.00"));
        testOrder.setOrderStatus(OrderStatus.PENDING);
    }

    @Test
    public void testGetAllOrders() throws Exception {
        List<Order> orders = Arrays.asList(testOrder);
        Mockito.when(orderService.getAllOrders()).thenReturn(orders);

        mockMvc.perform(MockMvcRequestBuilders.get("/orders"))
               .andExpect(MockMvcResultMatchers.status().isOk())
               .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testGetOrderById() throws Exception {
        Mockito.when(orderService.getOrderById(Mockito.anyLong())).thenReturn(testOrder);

        mockMvc.perform(MockMvcRequestBuilders.get("/orders/1"))
               .andExpect(MockMvcResultMatchers.status().isOk())
               .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
               .andExpect(MockMvcResultMatchers.jsonPath("$.orderId").value(testOrder.getOrderId()))
               .andExpect(MockMvcResultMatchers.jsonPath("$.customerName").value(testOrder.getCustomerName()));
    }

    @Test
    public void testPlaceOrder() throws Exception {
        Mockito.when(orderService.placeOrder(Mockito.any(Order.class))).thenReturn(testOrder);

        mockMvc.perform(MockMvcRequestBuilders.post("/orders")
               .contentType(MediaType.APPLICATION_JSON)
               .content(objectMapper.writeValueAsString(testOrder)))
               .andExpect(MockMvcResultMatchers.status().isCreated());
    

}
}
