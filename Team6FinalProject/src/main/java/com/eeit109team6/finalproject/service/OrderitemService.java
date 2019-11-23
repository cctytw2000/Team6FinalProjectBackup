package com.eeit109team6.finalproject.service;

import java.util.List;

import com.eeit109team6.finalproject.model.OrderItem;

public interface OrderitemService {
	public List<OrderItem> showOrders();
	public List<OrderItem> getOrderItemsById(Integer order_id);
}
