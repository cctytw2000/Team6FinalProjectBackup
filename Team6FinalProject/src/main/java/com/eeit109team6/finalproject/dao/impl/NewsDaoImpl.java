package com.eeit109team6.finalproject.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.eeit109team6.finalproject.dao.INewsDao;
import com.eeit109team6.finalproject.model.Comment;
import com.eeit109team6.finalproject.model.Message;
import com.eeit109team6.finalproject.model.News;
import com.eeit109team6.finalproject.model.NewsType;
import com.eeit109team6.finalproject.model.Product;

@Repository
public class NewsDaoImpl implements INewsDao {

	public NewsDaoImpl() {
	}

	SessionFactory sessionFactory;

	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

//====================================================消息類別=================================================

	@Override
	public void addNewsType(NewsType newsType) {
		Session session = sessionFactory.getCurrentSession();
		session.save(newsType);
	}

	@Override
	public NewsType getNewsTypeById(Integer newsTypeId) {
		Session session = sessionFactory.getCurrentSession();
		NewsType newsType = session.get(NewsType.class, newsTypeId);
		return newsType;
	}

	@Override
	public List<NewsType> getAllNewsTypes() {
		String hql = "From NewsType";
		Session session = sessionFactory.getCurrentSession();
		List<NewsType> list = new ArrayList<>();
		list = session.createQuery(hql).getResultList();
		return list;
	}

	@Override
	public void updateNewsTypeById(NewsType newsType) {
		Session session = sessionFactory.getCurrentSession();
		session.clear();
		session.update(newsType);
	}

	@Override
	public void deleteNewsTypeById(Integer newsTypeId) {
		Session session = sessionFactory.getCurrentSession();
		NewsType nt = session.get(NewsType.class, newsTypeId);
		session.delete(nt);
	}

//====================================================消息====================================================

	@Override
	public void addNews(News news) {
		Session session = sessionFactory.getCurrentSession();
		session.save(news);
	}

	@Override
	public List<News> getAllNews() {
		String hql = "FROM News";
		List<News> list = new ArrayList<>();
		Session session = sessionFactory.getCurrentSession();
		list = session.createQuery(hql).getResultList();
		return list;
	}

	@Override
	public void deleteNewsShow(Integer newsId) {
		Session session = sessionFactory.getCurrentSession();
		News news = session.get(News.class, newsId);
		news.setIsVisable(false);
		session.update(news);
	}

	@Override
	public void reopenNews(Integer newsId) {
		Session session = sessionFactory.getCurrentSession();
		News news = session.get(News.class, newsId);
		news.setIsVisable(true);
		session.update(news);
	}

	@Override
	public List<News> getAllNewsByTime() {
		String hql = "FROM News ORDER BY newsId DESC";
		List<News> list = new ArrayList<>();
		Session session = sessionFactory.getCurrentSession();
		list = session.createQuery(hql).getResultList();
		return list;
	}
	
	@Override
	public News getNewsById(Integer newsId) {
		Session session = sessionFactory.getCurrentSession();
		News news = session.get(News.class, newsId);
		return news;
	}
	
	@Override
	public void updateNewsById(News news) {
		Session session = sessionFactory.getCurrentSession();
		session.update(news);
	}

	@Override
	public List<News> getAllNewsByViews() {
		String hql = "FROM News ORDER BY Views DESC";
		List<News> list = new ArrayList<>();
		Session session = sessionFactory.getCurrentSession();
		list = session.createQuery(hql).getResultList();
		return list;
	}

	@Override
	public List<News> getNewsByKeyWord(String keyWord) {
		String hql = "FROM News n where n.title LIKE'%" + keyWord + "%' ORDER BY publicationdate DESC";
		System.out.println(hql);
		Session session = sessionFactory.getCurrentSession();
		List<News> list = session.createQuery(hql).getResultList();
		return list;
	}
	
//====================================================消息評論=================================================

	@Override
	public void addMemo(Message message) {
		Session session = sessionFactory.getCurrentSession();
		session.save(message);		
	}

	@Override
	public List<Message> getMessagesByNewsId(Integer newsId) {
		String hql = "FROM Message WHERE newsId = :newsId";
		Session session = sessionFactory.getCurrentSession();
		List<Message> list = session.createQuery(hql).setParameter("newsId", newsId).getResultList();
		return list;
	}

//====================================================未完成===================================================	
	

	
	

}
