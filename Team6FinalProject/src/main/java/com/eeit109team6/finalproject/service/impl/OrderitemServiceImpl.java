package com.eeit109team6.finalproject.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eeit109team6.finalproject.dao.IOrderitemDao;
import com.eeit109team6.finalproject.model.OrderItem;
import com.eeit109team6.finalproject.service.OrderitemService;

@Transactional
@Service
public class OrderitemServiceImpl implements OrderitemService {

	IOrderitemDao dao;

	@Autowired
	public void setDao(IOrderitemDao dao) {
		this.dao = dao;
	}

	@Override
	public List<OrderItem> showOrders() {
		return dao.showOrder();
	}

	@Override
	public List<OrderItem> getOrderItemsById(Integer order_id) {
		return dao.getOrderItemsById(order_id);
	}

	
}
