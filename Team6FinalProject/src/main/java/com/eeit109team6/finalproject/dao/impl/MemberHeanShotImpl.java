package com.eeit109team6.finalproject.dao.impl;

import java.util.ArrayList;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.eeit109team6.finalproject.dao.IMemberHeadShotDao;
import com.eeit109team6.finalproject.model.MemberHeadShot;

@Repository
public class MemberHeanShotImpl implements IMemberHeadShotDao {
	private SessionFactory sessionFactory;

	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void add(MemberHeadShot mhs) {

		sessionFactory.getCurrentSession().save(mhs);

	}

	@Override
	public void delete(Integer id) {

		MemberHeadShot mhs = sessionFactory.getCurrentSession().get(MemberHeadShot.class, id);
		sessionFactory.getCurrentSession().delete(mhs);
	}

	@Override
	public ArrayList<MemberHeadShot> findAll() {
		Query query = sessionFactory.getCurrentSession().createQuery("from MemberHeadShot");
		ArrayList<MemberHeadShot> member = (ArrayList<MemberHeadShot>) query.getResultList();
		return member;
	}

	@Override
	public MemberHeadShot findById(Integer id) {
		MemberHeadShot mhs = sessionFactory.getCurrentSession().get(MemberHeadShot.class, id);
		return mhs;
	}

	@Override
	public ArrayList<MemberHeadShot> findByMemberId(Integer id) {
		Query query = sessionFactory.getCurrentSession().createQuery("from MemberHeadShot where memberId = ?1");
		query.setParameter(1, id);
		ArrayList<MemberHeadShot> memberHeadShot = (ArrayList<MemberHeadShot>) query.getResultList();
		return memberHeadShot;
	}
}
