package com.eeit109team6.finalproject.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.eeit109team6.finalproject.dao.ILiLoInforDao;
import com.eeit109team6.finalproject.model.LiLoInfo;
import com.eeit109team6.finalproject.model.Member;

@Repository
public class LiLoInforDaoJdbcImpl implements ILiLoInforDao {

	private SessionFactory sessionFactory;

	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public LiLoInforDaoJdbcImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public Boolean add(LiLoInfo l) {

		sessionFactory.getCurrentSession().save(l);

		return true;

	}

	@Override
	public ArrayList<LiLoInfo> findById(Integer memberId) {
		System.out.println("memberId = " + memberId);
		List<LiLoInfo> imfoList = null;
		Query query = sessionFactory.getCurrentSession().createQuery("from LiLoInfo where memberID = ?1 ");
		query.setParameter(1, memberId);
		imfoList = (ArrayList<LiLoInfo>) query.getResultList();

		return (ArrayList<LiLoInfo>) imfoList;

	}

	@Override
	public Map countLogin(String now,String three_days_after) {
		System.out.println("countLogin");
		Map<String, Integer> data = new TreeMap();
		System.out.println("data" + data);
		Query query = sessionFactory.getCurrentSession()
				.createSQLQuery("select CONVERT(date,loginTime) as 'time' , COUNT(loginTime) as 'count'"
						+ "from LiLoInfo WHERE type = 'Login' and CONVERT(date,loginTime) BETWEEN '"+three_days_after+"' and '"+now+"'    GROUP BY CONVERT(date,loginTime) order by CONVERT(date,LiLoInfo.loginTime) desc");

		System.out.println("query=" + query);
		List<Object[]> rows = query.getResultList();
		System.out.println("rows=" + rows);
		for (Object[] row : rows) {

			data.put(row[0].toString(), Integer.valueOf(row[1].toString()));
		}
		return data;

	}

}
