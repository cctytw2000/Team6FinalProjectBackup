package com.eeit109team6.finalproject.service;

import java.util.List;

import com.eeit109team6.finalproject.model.SubjectType;

public interface ISubjectTypeService {
	List<SubjectType> getAllSubjectType();		//取得所有發文分類
	SubjectType getSubjectTypeById(int subjectTypeId);//依照id取得一個發文分類
	void addSubjectType(SubjectType subjectType); //新增一個發文分類
	void updateSubjectType(SubjectType subjectType);//修改發文分類

}
