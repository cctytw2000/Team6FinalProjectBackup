package com.eeit109team6.finalproject.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.eeit109team6.finalproject.dao.IReplyDao;
import com.eeit109team6.finalproject.model.Reply;

@Repository
public class ReplyDaoImpl implements IReplyDao {
	
	SessionFactory factory;

	@Autowired
	public void setFactory(SessionFactory factory) {
		this.factory = factory;
	}

	@Override
	public List<Reply> getReplyByArticle(Integer articleId) {
		String hql = "FROM Reply WHERE articleId = :articleId";
		List<Reply> list = new ArrayList<>();
		Session session = factory.getCurrentSession();
		list = session.createQuery(hql).setParameter("articleId", articleId).getResultList();
		return list;
	}

	@Override
	public void addReply(Reply reply) {
		Session session = factory.getCurrentSession();
		session.save(reply);
	}
	

	@Override
	public void updateReply(Integer replyId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteReply(Integer replyId) {
		Session session = factory.getCurrentSession();
//		session.delete(replyId);
	}

}
