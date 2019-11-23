package com.eeit109team6.finalproject.dao.impl;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.eeit109team6.finalproject.dao.IMemberDetailDao;
import com.eeit109team6.finalproject.model.Member;
import com.eeit109team6.finalproject.model.MemberDetail;

@Repository
public class MemberDetailDaoJdbcImpl implements IMemberDetailDao {

	private Connection conn;
	private SessionFactory sessionFactory;

	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public MemberDetailDaoJdbcImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void add(MemberDetail md) {
		System.out.println("----新增會員資料- - - -");
		System.out.println("身分證字號:" + md.getIdnumber());
		System.out.println("性別:" + md.getSex());
		System.out.println("地址:" + md.getAddress());
		System.out.println("生日:" + md.getBirth());
		sessionFactory.getCurrentSession().save(md);
		System.out.println("會員資料新增完成");
		System.out.println("--------------------");

	}

	@Override
	public void update(MemberDetail md) {
		sessionFactory.getCurrentSession().update(md);

	}

	@Override
	public void delete(MemberDetail md) {
		// TODO Auto-generated method stub

	}

	@Override
	public ArrayList<MemberDetail> fintAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MemberDetail fintById(Member m) {
		List<MemberDetail> memList = null;
		Query query = sessionFactory.getCurrentSession().createQuery("from MemberDetail where memberID = ?1 ");
		System.out.println(" m.getMember_id()=" + m.getMember_id());
		query.setParameter(1, m.getMember_id());
		memList = (List<MemberDetail>) query.getResultList();

		if (memList.size() != 0) {
			memList.get(0);
			return memList.get(0);
		} else {
			return null;
		}
	}

}
