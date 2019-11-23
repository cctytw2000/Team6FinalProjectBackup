package com.eeit109team6.finalproject.dao;

import java.util.List;

import com.eeit109team6.finalproject.model.OrderItem;
import com.eeit109team6.finalproject.model.Orders;

public interface IOrderitemDao {
	public List<OrderItem> showOrder();
	public List<OrderItem> getOrderItemsById(Integer order_id);
}
