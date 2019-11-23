package com.eeit109team6.finalproject.dao.impl;

import java.util.ArrayList;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.eeit109team6.finalproject.dao.IMemberLevelDao;
import com.eeit109team6.finalproject.model.Member;
import com.eeit109team6.finalproject.model.MemberLevel;

@Repository
public class MemberLevelDaoJdbcImpl implements IMemberLevelDao {

	private SessionFactory sessionFactory;

	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public MemberLevelDaoJdbcImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public MemberLevel findById(Integer levelId) {

		return sessionFactory.getCurrentSession().get(MemberLevel.class, levelId);
	}

	@Override
	public ArrayList<MemberLevel> findAll() {
		Query query = sessionFactory.getCurrentSession().createQuery("from MemberLevel");
		ArrayList<MemberLevel> level = (ArrayList<MemberLevel>) query.getResultList();
		return level;

	}

}
