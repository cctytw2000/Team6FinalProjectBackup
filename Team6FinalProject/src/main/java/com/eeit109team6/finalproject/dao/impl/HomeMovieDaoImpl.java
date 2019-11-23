package com.eeit109team6.finalproject.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.eeit109team6.finalproject.dao.IHomeMovieDao;
import com.eeit109team6.finalproject.model.HomeMovie;
import com.eeit109team6.finalproject.model.Movie;
import com.eeit109team6.finalproject.model.MovieInfo;


@Repository
public class HomeMovieDaoImpl implements IHomeMovieDao {

	public HomeMovieDaoImpl() {
	}

	SessionFactory factory;

	@Override
	@Autowired
	public void setSession(SessionFactory factory) {
		this.factory = factory;
	}

	@Override
	public HomeMovie findByid(Integer id) {
		HomeMovie homeMov = factory.getCurrentSession().get(HomeMovie.class, id);

		return homeMov;
	}

	@Override
	public Integer addMovie(Movie movie) {
		Session session = factory.getCurrentSession();
		Integer id = (Integer) session.save(movie);
		return id;
	}

	@Override
	public void deleteMovieById(Integer deleteMovieId) {
		Session session = factory.getCurrentSession();
		Movie movie = session.get(Movie.class, deleteMovieId);
		System.out.println("session.delete(movie)的deleteMovieId ================================= "+deleteMovieId);
		session.delete(movie);

	}

	@Override
	public void updateMovieById(Movie movie) {
		Session session = factory.getCurrentSession();
		session.update(movie);

	}

	@Override
	public List<Movie> findAll() {
		String hql = "FROM Movie";
		ArrayList<Movie> list = new ArrayList<>();
		Session session = factory.getCurrentSession();
		list = (ArrayList<Movie>) session.createQuery(hql).getResultList();
		return list;
	}

	@Override
	public Movie getMovieInfoByMovieID(Integer id) {
		String hql = "FROM Movie WHERE id = ?1";
		//指定 ? 帶入值為 setParameter(1, id) 的 id
		Query query = factory.getCurrentSession().createQuery(hql).setParameter(1, id);
		Movie movielist = (Movie)query.getResultList();
		return movielist;
	}

	@Override
	public void updateMovieid(HomeMovie homeMovie) {
		Session session = factory.getCurrentSession();
		session.update(homeMovie);

	}
	//Done
	@Override
	public Movie moviefindById(Integer movieId) {
		Movie movie = factory.getCurrentSession().get(Movie.class, movieId);
		return movie;
	}


}
