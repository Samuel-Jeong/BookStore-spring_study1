package com.springmvc.repository;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.springmvc.domain.Order;

@Repository
public class OrderRepositoryImpl implements OrderRepository {

	private Map<Long, Order> listOfOrders;
	private long nextOrderId;

	public OrderRepositoryImpl() {
		this.listOfOrders = new HashMap<>();
		nextOrderId = 2000;
	}

	private synchronized long getNextOrderId() {
		return nextOrderId++;
	}

	@Override
	public Long saveOrder(Order order) {
		order.setOrderId(getNextOrderId());
		listOfOrders.put(order.getOrderId(), order);
		return order.getOrderId();
	}

}
