package com.eeit109team6.finalproject.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.eeit109team6.finalproject.dao.IOrderitemDao;
import com.eeit109team6.finalproject.model.OrderItem;

@Repository
public class OrderitemDaoImpl implements IOrderitemDao {

	SessionFactory factory;

	@Autowired
	public void setSession(SessionFactory factory) {
		this.factory = factory;
	}

	@Override
	public List<OrderItem> showOrder() {
		String hql = "FROM OrderItem";
		Session session = factory.getCurrentSession();
		return session.createQuery(hql).getResultList();
	}

	@Override
	public List<OrderItem> getOrderItemsById(Integer order_id) {
		String hql = "FROM OrderItem  WHERE order_id = :order_id";
		Query query = factory.getCurrentSession().createQuery(hql);
		query.setParameter("order_id", order_id);
		return query.getResultList();
	}

}
