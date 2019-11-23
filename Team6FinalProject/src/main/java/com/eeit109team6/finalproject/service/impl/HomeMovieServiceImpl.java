package com.eeit109team6.finalproject.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eeit109team6.finalproject.dao.IHomeMovieDao;
import com.eeit109team6.finalproject.dao.IMemberDao;
import com.eeit109team6.finalproject.model.HomeMovie;
import com.eeit109team6.finalproject.model.Member;
import com.eeit109team6.finalproject.model.MemberLevel;
import com.eeit109team6.finalproject.model.Movie;
import com.eeit109team6.finalproject.service.IHomeMovieService;
import com.eeit109team6.finalproject.service.IMemberService;

@Service
public class HomeMovieServiceImpl implements IHomeMovieService {
	IHomeMovieDao dao;

	@Autowired
	public void setDao(IHomeMovieDao dao) {
		this.dao = dao;
	}

	@Override
	@Transactional
	public HomeMovie findById(Integer id) {

		return dao.findByid(id);
	}

	@Override
	public Integer addMovie(Movie movie) {
		// TODO Auto-generated method stub
		return dao.addMovie(movie);
	}

	@Override
	public void deleteMovieById(Integer deleteMovieId) {
	 
		dao.deleteMovieById(deleteMovieId);
	}

	@Override
	public void updateMovieById(Movie movie) {
		dao.updateMovieById(movie);
		
	}

	@Override
	public List<Movie> findAll() {
		return dao.findAll();
	}

	@Override
	public Movie getMovieInfoByMovieID(Integer id) {
		return dao.getMovieInfoByMovieID(id);
	}

	@Override
	public void updateMovieid(HomeMovie homeMovie) {
		dao.updateMovieid(homeMovie);
		
	}

	@Override
	public Movie moviefindById(Integer movieId) {
		return dao.moviefindById(movieId);
	}

}
