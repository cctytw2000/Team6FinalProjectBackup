package com.eeit109team6.finalproject.dao;

import java.util.List;

import com.eeit109team6.finalproject.model.Member;
import com.eeit109team6.finalproject.model.Orders;
import com.eeit109team6.finalproject.model.Product;

public interface IOrderDao {
	void insertOrder(Orders order);
	public List<Orders> showOrder(Integer member_id);
	public Orders getOrderById(Integer order_id);
	public Boolean updateOrderstate(Integer order_id);
	public List<Orders> findAll();
	public void deleteOrderById(Integer order_id);
	public List<Object[]> dailySalescount();
	public List<Orders> findAll(Integer state);
	public List<Orders> showOrder(Integer member_id,Integer state);
	public List<Member> getMemberByKeyWord(String keyWord);
}
