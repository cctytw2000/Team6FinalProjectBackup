package com.eeit109team6.finalproject.service;

import java.util.List;

import com.eeit109team6.finalproject.model.Reply;

public interface IReplyService {
		List<Reply> getReplyByArticle(Integer articleId);	//瀏覽文章時，帶出同主題的回覆串
		void addReply(Reply reply);		//新增回覆
		void updateReply(Integer replyId);	//修改回覆
		void deleteReply(Integer replyId);	//刪除回覆

	
}
