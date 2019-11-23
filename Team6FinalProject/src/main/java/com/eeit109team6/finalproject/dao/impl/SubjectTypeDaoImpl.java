package com.eeit109team6.finalproject.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.eeit109team6.finalproject.dao.ISubjectTypeDao;
import com.eeit109team6.finalproject.model.BoardType;
import com.eeit109team6.finalproject.model.SubjectType;

@Repository
public class SubjectTypeDaoImpl implements ISubjectTypeDao {
	
	SessionFactory factory;

	@Autowired
	public void setFactory(SessionFactory factory) {
		this.factory = factory;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SubjectType> getAllSubjectType() {
		System.out.println("================進入SubjectTypeDaoImpl getAllSubjectType()");
		String hql = "FROM SubjectType";
		List<SubjectType> list = new ArrayList<>();
		Session session = factory.getCurrentSession();
		list = session.createQuery(hql).getResultList();
		System.out.println("session.createQuery(hql).getResultList():"+list);
		return list;
	}

	@Override
	public SubjectType getSubjectTypeById(int subjectTypeId) {
		Session session = factory.getCurrentSession();
		SubjectType subjectType = session.get(SubjectType.class, subjectTypeId);
		return subjectType;
	}

	@Override
	public void addSubjectType(SubjectType subjectType) {
		Session session = factory.getCurrentSession();
		session.save(subjectType);
	}

	@Override
	public void updateSubjectType(SubjectType subjectType) {
		Session session = factory.getCurrentSession();
		session.update(subjectType);
	}

}
