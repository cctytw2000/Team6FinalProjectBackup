package com.eeit109team6.finalproject.service;

import java.util.ArrayList;

import org.springframework.transaction.annotation.Transactional;

import com.eeit109team6.finalproject.model.MemberHeadShot;

public interface IMemberHeadShotService {

	void add(MemberHeadShot mhs);

	ArrayList<MemberHeadShot> findAll();

	ArrayList<MemberHeadShot> findByMemberId(Integer id);

	MemberHeadShot findById(Integer id);
	
	void delete(Integer id);
}