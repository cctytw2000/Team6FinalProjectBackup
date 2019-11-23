package com.eeit109team6.finalproject.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eeit109team6.finalproject.dao.IMemberHeadShotDao;
import com.eeit109team6.finalproject.model.MemberHeadShot;
import com.eeit109team6.finalproject.service.IMemberHeadShotService;

@Service
public class MemberHeanShotServiceImpl implements IMemberHeadShotService {
	IMemberHeadShotDao dao;

	@Autowired
	public void setDao(IMemberHeadShotDao dao) {
		this.dao = dao;
	}

	@Override
	@Transactional
	public void add(MemberHeadShot mhs) {

		dao.add(mhs);
	}

	@Override
	@Transactional
	public ArrayList<MemberHeadShot> findAll() {

		return dao.findAll();
	}

	@Override
	@Transactional
	public ArrayList<MemberHeadShot> findByMemberId(Integer id) {

		return dao.findByMemberId(id);
	}

	@Override
	@Transactional
	public MemberHeadShot findById(Integer id) {

		return dao.findById(id);
	}

	@Override
	@Transactional
	public void delete(Integer id) {
		dao.delete(id);

	}
}
