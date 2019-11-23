package com.eeit109team6.finalproject.service;

import java.util.ArrayList;

import com.eeit109team6.finalproject.model.MemberLevel;

public interface IMemberLevelService {

	public MemberLevel findById(Integer levelId);

	public ArrayList<MemberLevel> findAll();


}
