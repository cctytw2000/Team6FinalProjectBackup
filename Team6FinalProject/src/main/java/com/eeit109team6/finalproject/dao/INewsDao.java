package com.eeit109team6.finalproject.dao;

import java.util.List;
import java.util.Map;

import com.eeit109team6.finalproject.model.Message;
import com.eeit109team6.finalproject.model.News;
import com.eeit109team6.finalproject.model.NewsType;

public interface INewsDao {
//====================================================消息類別=================================================
	void addNewsType(NewsType newsType);//新增消息類別
	NewsType getNewsTypeById(Integer newsTypeId);//取得newsTypeId
	List<NewsType> getAllNewsTypes();
	void updateNewsTypeById(NewsType newsType); //更新消息類別
	void deleteNewsTypeById(Integer newsTypeId); //刪除消息類別
//====================================================消息====================================================
	void addNews(News news); //新增消息
	List<News> getAllNews(); //查詢所有消息
	void deleteNewsShow(Integer newsId); //隱藏消息
	void reopenNews(Integer newsId); //發佈消息
	List<News> getAllNewsByTime(); 
	List<News> getAllNewsByViews(); 
	News getNewsById(Integer newsId); 
	void updateNewsById(News news); //更新消息
	List<News> getNewsByKeyWord(String keyWord);
//====================================================消息評論=================================================
	void addMemo(Message message); //新增消息評論
	List<Message> getMessagesByNewsId(Integer newsId); //依消息id取得評論
//====================================================未完成===================================================		
	
}
