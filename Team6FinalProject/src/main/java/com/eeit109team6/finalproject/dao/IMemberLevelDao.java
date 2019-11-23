package com.eeit109team6.finalproject.dao;

import java.util.ArrayList;

import com.eeit109team6.finalproject.model.MemberLevel;

public interface IMemberLevelDao {

	public MemberLevel findById(Integer levelId);

	ArrayList<MemberLevel> findAll();

}
