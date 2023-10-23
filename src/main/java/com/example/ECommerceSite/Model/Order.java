package com.example.ECommerceSite.Model;

import javax.persistence.*;

import com.example.ECommerceSite.Ennum.OrderStatus;

import java.math.BigDecimal;
import java.util.Date;

@Entity
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    @Column(nullable = false)
    private String customerName;

    @Temporal(TemporalType.TIMESTAMP)
    private Date orderDate;

    private BigDecimal totalAmount;

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Order(Long orderId, String customerName, Date orderDate, BigDecimal totalAmount) {
		super();
		this.orderId = orderId;
		this.customerName = customerName;
		this.orderDate = orderDate;
		this.totalAmount = totalAmount;
	}

	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", customerName=" + customerName + ", orderDate=" + orderDate
				+ ", totalAmount=" + totalAmount + "]";
	}
//	

	

   @Enumerated(EnumType.STRING)
   private OrderStatus orderStatus;



public void setOrderStatus(OrderStatus pending) {
	// TODO Auto-generated method stub
	
}

    // Constructors, getters, and setters
}

