package com.eeit109team6.finalproject.dao;

import java.util.ArrayList;

import com.eeit109team6.finalproject.model.Member;
import com.eeit109team6.finalproject.model.MemberDetail;

public interface IMemberDetailDao {
	public void add(MemberDetail md);

	public void update(MemberDetail md);

	public void delete(MemberDetail md);

	public ArrayList<MemberDetail> fintAll();

	public MemberDetail fintById(Member m);

}
