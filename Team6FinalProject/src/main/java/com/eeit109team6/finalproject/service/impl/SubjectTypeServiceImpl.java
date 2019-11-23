package com.eeit109team6.finalproject.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eeit109team6.finalproject.dao.ISubjectTypeDao;
import com.eeit109team6.finalproject.model.SubjectType;
import com.eeit109team6.finalproject.service.ISubjectTypeService;

@Service
public class SubjectTypeServiceImpl implements ISubjectTypeService {
	ISubjectTypeDao dao;
	
	@Autowired
	public void setDao(ISubjectTypeDao dao) {
		this.dao = dao;
	}
	
	public SubjectTypeServiceImpl() {
	}

	@Transactional
	@Override
	public List<SubjectType> getAllSubjectType() {
		System.out.println("進入SubjectTypeServiceImpl");
		return dao.getAllSubjectType();
	}

	@Transactional
	@Override
	public SubjectType getSubjectTypeById(int subjectTypeId) {
		return dao.getSubjectTypeById(subjectTypeId);
	}

	@Transactional
	@Override
	public void addSubjectType(SubjectType subjectType) {
		dao.addSubjectType(subjectType);
	}

	@Transactional
	@Override
	public void updateSubjectType(SubjectType subjectType) {
		dao.updateSubjectType(subjectType);
	}

}
