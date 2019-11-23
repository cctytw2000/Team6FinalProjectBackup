package com.eeit109team6.finalproject.dao;

import java.util.ArrayList;

import com.eeit109team6.finalproject.model.MemberHeadShot;

public interface IMemberHeadShotDao {

	public void add(MemberHeadShot mhs);

	public ArrayList<MemberHeadShot> findAll();

	ArrayList<MemberHeadShot> findByMemberId(Integer id);

	MemberHeadShot findById(Integer id);

	void delete(Integer id);


}
