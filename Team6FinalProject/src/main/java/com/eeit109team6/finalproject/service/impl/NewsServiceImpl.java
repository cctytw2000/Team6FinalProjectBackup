package com.eeit109team6.finalproject.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eeit109team6.finalproject.dao.INewsDao;
import com.eeit109team6.finalproject.model.Message;
import com.eeit109team6.finalproject.model.News;
import com.eeit109team6.finalproject.model.NewsType;
import com.eeit109team6.finalproject.service.INewsService;

@Service
public class NewsServiceImpl implements INewsService{

	public NewsServiceImpl(){
	}
	
	INewsDao dao;
	@Autowired
	public void setDao(INewsDao dao) {
		this.dao = dao;
	}

//====================================================消息類別=================================================

	@Transactional
	@Override
	public void addNewsType(NewsType newsType) {
		dao.addNewsType(newsType);		
	}
	
	@Transactional
	@Override
	public NewsType getNewsTypeById(Integer newsTypeId) {
		return dao.getNewsTypeById(newsTypeId);
	}
	
	@Transactional
	@Override
	public List<NewsType> getAllNewsTypes() {
		return dao.getAllNewsTypes();
	}
	
	@Transactional
	@Override
	public void updateNewsTypeById(NewsType newsType) {
		dao.updateNewsTypeById(newsType);
	}
	
	@Transactional
	@Override
	public void deleteNewsTypeById(Integer newsTypeId) {
		dao.deleteNewsTypeById(newsTypeId);
	}

//====================================================消息====================================================
	
	@Transactional
	@Override
	public void addNews(News news) {
		dao.addNews(news);	
	}
	
	@Transactional
	@Override
	public List<News> getAllNews() {
		return dao.getAllNews();
	}
	
	@Transactional
	@Override
	public void deleteNewsShow(Integer newsId) {
		dao.deleteNewsShow(newsId);
	}

	@Transactional
	@Override
	public void reopenNews(Integer newsId) {
		dao.reopenNews(newsId);		
	}
	
	@Transactional
	@Override
	public List<News> getAllNewsByTime() {
		return dao.getAllNewsByTime();
	}
	
	@Transactional
	@Override
	public News getNewsById(Integer newsId) {
		return dao.getNewsById(newsId);
	}
	
	@Transactional
	@Override
	public void updateNewsById(News news) {
		dao.updateNewsById(news);
	}

	@Transactional
	@Override
	public List<News> getAllNewsByViews() {
		return dao.getAllNewsByViews();
	}
	
	@Transactional
	@Override
	public List<News> getNewsByKeyWord(String keyWord) {
		return dao.getNewsByKeyWord(keyWord);
	}

//====================================================消息評論=================================================
	
	@Transactional
	@Override
	public void addMemo(Message message) {
		dao.addMemo(message);		
	}
	
	@Transactional
	@Override
	public List<Message> getMessagesByNewsId(Integer newsId) {
		return dao.getMessagesByNewsId(newsId);
	}

//====================================================未完成====================================================
	

}
