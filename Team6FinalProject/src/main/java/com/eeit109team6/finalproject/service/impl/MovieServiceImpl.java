package com.eeit109team6.finalproject.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.eeit109team6.finalproject.model.MovieInfo;
import com.eeit109team6.finalproject.dao.IMovieDao;
import com.eeit109team6.finalproject.service.IMovieService;

@Service
public class MovieServiceImpl implements IMovieService {

	IMovieDao dao;

	@Autowired
	public void setDao(IMovieDao dao) {
		this.dao = dao;
	}

	@Transactional
	@Override
	public Integer addMovie(MovieInfo movieinfo) {
		return dao.addMovie(movieinfo);

	}

	@Transactional
	@Override
	public void deleteMovieInfoById(int movie_ID) {
		dao.deleteMovieInfoById(movie_ID);

	}

	@Transactional
	@Override
	public void updateMovieInfoById(MovieInfo movieinfo) {
		dao.updateMovieInfoById(movieinfo);
	}

	@Transactional
	@Override
	public MovieInfo getMovieInfoByMovieID(Integer movie_ID) {
		return dao.getMovieInfoByMovieID(movie_ID);
	}

	@Transactional
	@Override
	public List<MovieInfo> getMovies() {
		return dao.getMovies();
	}

	@Transactional
	@Override
	public List<MovieInfo> getMovieInfoByOwnerID(int owner_ID) {
		return dao.getMovieInfoByOwnerID(owner_ID);
	}

	@Transactional
	@Override
	public ArrayList<MovieInfo> getMovieInfoByID(Integer id) {
		// TODO Auto-generated method stub
		return dao.getMovieInfoByOwnerID(id);
	}

	@Transactional
	@Override
	public void updateMovieViews(Integer id) {
		dao.updateMovieViews(id);

	}

	@Transactional
	@Override
	public ArrayList<MovieInfo> getNewMovieInfo(Integer dateLength) {
		// TODO Auto-generated method stub
		return dao.getNewMovieInfo(dateLength);
	}

}
