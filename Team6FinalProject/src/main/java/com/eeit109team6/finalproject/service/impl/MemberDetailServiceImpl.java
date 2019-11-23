package com.eeit109team6.finalproject.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eeit109team6.finalproject.dao.IMemberDao;
import com.eeit109team6.finalproject.dao.IMemberDetailDao;
import com.eeit109team6.finalproject.model.Member;
import com.eeit109team6.finalproject.model.MemberDetail;
import com.eeit109team6.finalproject.service.IMemberDetailService;

@Service

public class MemberDetailServiceImpl implements IMemberDetailService {
	IMemberDetailDao dao;

	@Autowired
	public void setDao(IMemberDetailDao dao) {
		this.dao = dao;
	}

	@Transactional
	@Override
	public void add(MemberDetail md) {
		dao.add(md);

	}
	@Transactional
	@Override
	public void update(MemberDetail md) {
		dao.update(md);

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
	@Transactional
	@Override
	public MemberDetail fintById(Member m) {

		return dao.fintById(m);
	}

}
