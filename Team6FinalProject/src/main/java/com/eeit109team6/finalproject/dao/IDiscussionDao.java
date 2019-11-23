package com.eeit109team6.finalproject.dao;

import java.util.List;

import com.eeit109team6.finalproject.model.BoardType;
import com.eeit109team6.finalproject.model.Discussion;


public interface IDiscussionDao {
	
	List<Discussion> getAllArticles();		// 取得所有文章
	List<Discussion> getArticleByBoardTypeId(Integer boardId);//取得指定看板的所有文章
	List<Discussion> getArticleByBoardTypeIdBack(Integer boardId);//取得指定看板的含標記軟刪除的所有文章
	Discussion getArticleById(int articleId);//瀏覽單筆文章
	void addArticle(Discussion discussion); //新增一筆文章(樓主)
	void updateViews(Integer articleId);	//更新文章瀏覽次數
	void updateBoardViews(Integer boardId);	//更新看板瀏覽次數
	
	List<Discussion> getArticleTop6(); 		//首頁展示；熱門文章。查詢瀏覽次數最高的6篇文章
	List<Discussion> getLatestArticle(); 	//討論區主頁展示；最新文章。查詢最後發表的3篇文章
	
	List<BoardType> getBoardTopN();			//首頁展示；人氣排行。查詢文章最多的一批看板
	void updateArticle(Discussion discussion);//修改文章
	void physicalDeleteArticleById(Integer articleId);//硬刪除文章，從資料庫刪除，無法恢復
	void deleteArticleById(Integer articleId);//軟刪除文章
	void recoverArticleById(Integer articleId);//反刪除文章
	
}
