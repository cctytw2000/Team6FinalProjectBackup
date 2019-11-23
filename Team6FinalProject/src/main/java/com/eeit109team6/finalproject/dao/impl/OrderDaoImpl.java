package com.eeit109team6.finalproject.dao.impl;

import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.eeit109team6.finalproject.dao.IOrderDao;
import com.eeit109team6.finalproject.model.Member;
import com.eeit109team6.finalproject.model.Orders;

@Repository
public class OrderDaoImpl implements IOrderDao {

	SessionFactory factory;

	@Autowired
	public void setSession(SessionFactory factory) {
		this.factory = factory;
	}

	@Override
	public void insertOrder(Orders order) {
		Session session = factory.getCurrentSession();
		session.save(order);
	}

	@Override
	public List<Orders> showOrder(Integer member_id) {
		String hql = "FROM Orders  WHERE member_id = :member_id and is_remove = 0";
		Session session = factory.getCurrentSession();
		List<Orders> list = session.createQuery(hql).setParameter("member_id", member_id).getResultList();
		return list;
	}

	@Override
	public Orders getOrderById(Integer order_id) {
		String hql = "FROM Orders  WHERE order_id = :order_id";
		Session session = factory.getCurrentSession();
		Orders order = (Orders) session.createQuery(hql).setParameter("order_id", order_id).getSingleResult();
		return order;
	}

	@Override
	public Boolean updateOrderstate(Integer order_id) {
		String hql = "FROM Orders  WHERE order_id = :order_id AND state = 1";
		Query query = factory.getCurrentSession().createQuery(hql).setParameter("order_id", order_id);
		try {
			Orders ordersList = (Orders) query.getSingleResult();
			ordersList.setState(4);
			factory.getCurrentSession().update(ordersList);
			return true;
		} catch (NoResultException e) {
			System.out.println("沒有未付款訂單");
			return false;
		}
	}

	@Override
	public List<Orders> findAll() {
		Query query = factory.getCurrentSession().createQuery("from Orders where is_remove = 0");
		return query.getResultList();
	}

	@Override
	public void deleteOrderById(Integer order_id) {
		Session session = factory.getCurrentSession();
		Orders order = factory.getCurrentSession().get(Orders.class, order_id);
		order.setIs_remove(true);
		session.update(order);
	}

	@Override
	public List<Object[]> dailySalescount() {
		String hql = "SELECT CONVERT(date,ordertime) as 'time' , SUM(total) as 'Sales' "
				+ "FROM Orders WHERE is_remove = 0 and state = 4 " + "GROUP BY CONVERT(date,ordertime) "
				+ "ORDER BY CONVERT(date,ordertime) asc";
		Query query = factory.getCurrentSession().createSQLQuery(hql);
		List<Object[]> rows = query.getResultList();
		for (Object[] row : rows) {
			System.out.println("row[0].toString()=" + row[0].toString());
			System.out.println("row[1].toString()=" + row[1].toString());
			row[0] = row[0].toString();
			row[1] = row[1].toString();
		}
		return rows;
	}

	@Override
	public List<Orders> findAll(Integer state) {
		String hql = "FROM Orders  WHERE is_remove = 0 and state = :state";
		Query query = factory.getCurrentSession().createQuery(hql);
		query.setParameter("state", state);
		return query.getResultList();
	}

	@Override
	public List<Orders> showOrder(Integer member_id, Integer state) {
		String hql = "FROM Orders  WHERE member_id = :member_id and is_remove = 0 and state = :state";
		Query query = factory.getCurrentSession().createQuery(hql);
		query.setParameter("member_id", member_id);
		query.setParameter("state", state);
		return query.getResultList();
	}

	@Override
	public List<Member> getMemberByKeyWord(String keyWord) {
		String hql = "FROM Member  where username LIKE'%" + keyWord + "%' or account LIKE'%" + keyWord + "%'";
		Query query = factory.getCurrentSession().createQuery(hql);
		return query.getResultList();
	}
}
