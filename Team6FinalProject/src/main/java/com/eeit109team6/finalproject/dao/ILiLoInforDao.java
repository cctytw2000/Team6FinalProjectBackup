package com.eeit109team6.finalproject.dao;

import java.util.ArrayList;
import java.util.Map;

import com.eeit109team6.finalproject.model.LiLoInfo;

public interface ILiLoInforDao {

	public Boolean add(LiLoInfo l);

	ArrayList<LiLoInfo> findById(Integer memberId);

	Map countLogin(String now, String three_days_after);





}
