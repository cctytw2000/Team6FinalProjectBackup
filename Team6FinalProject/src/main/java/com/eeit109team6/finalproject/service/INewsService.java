package com.eeit109team6.finalproject.service;

import java.util.List;
import java.util.Map;

import com.eeit109team6.finalproject.model.Message;
import com.eeit109team6.finalproject.model.News;
import com.eeit109team6.finalproject.model.NewsType;

public interface INewsService {
//====================================================消息類別=================================================
	void addNewsType(NewsType newsType);
	NewsType getNewsTypeById(Integer newsTypeId);
	List<NewsType> getAllNewsTypes();
	void updateNewsTypeById(NewsType newsType); 
	void deleteNewsTypeById(Integer newsTypeId); 
//====================================================消息====================================================
	void addNews(News news); 
	List<News> getAllNews();
	void deleteNewsShow(Integer newsId); //隱藏消息
	void reopenNews(Integer newsId); //發佈消息
	List<News> getAllNewsByTime(); 
	List<News> getAllNewsByViews(); 
	News getNewsById(Integer newsId);
	void updateNewsById(News news);
	List<News> getNewsByKeyWord(String keyWord);
//====================================================消息評論=================================================
	void addMemo(Message message); //新增消息評論
	List<Message> getMessagesByNewsId(Integer newsId); //依消息id取得評論
//====================================================未完成===================================================	
}
