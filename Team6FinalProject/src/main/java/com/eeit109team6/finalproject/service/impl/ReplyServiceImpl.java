package com.eeit109team6.finalproject.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eeit109team6.finalproject.dao.IReplyDao;
import com.eeit109team6.finalproject.model.Reply;
import com.eeit109team6.finalproject.service.IReplyService;

@Service
public class ReplyServiceImpl implements IReplyService {
	IReplyDao dao;
	
	@Autowired
	public void setDao(IReplyDao dao) {
		this.dao = dao;
	}

	public ReplyServiceImpl() {
	}

	@Transactional
	@Override
	public List<Reply> getReplyByArticle(Integer articleId) {
		return dao.getReplyByArticle(articleId);
	}

	@Transactional
	@Override
	public void addReply(Reply reply) {
		System.out.println("進入 addReply()，reply:"+reply);
		
		dao.addReply(reply);
	}

	@Transactional
	@Override
	public void updateReply(Integer replyId) {
		// TODO Auto-generated method stub
		
	}

	@Transactional
	@Override
	public void deleteReply(Integer replyId) {
		// TODO Auto-generated method stub
		
	}

}
