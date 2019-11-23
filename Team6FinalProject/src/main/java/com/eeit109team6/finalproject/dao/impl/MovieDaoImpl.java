package com.eeit109team6.finalproject.dao.impl;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.eeit109team6.finalproject.model.Discussion;
import com.eeit109team6.finalproject.model.MovieInfo;

import net.bytebuddy.dynamic.scaffold.MethodRegistry.Handler.ForAbstractMethod;

import com.eeit109team6.finalproject.controller.MemberController;
import com.eeit109team6.finalproject.dao.IMovieDao;

@Repository
public class MovieDaoImpl implements IMovieDao {

	SessionFactory factory;

	@Autowired
	public void setFactory(SessionFactory factory) {
		this.factory = factory;
	}

	// DONE
	@Override
	public Integer addMovie(MovieInfo movieInfo) {
		Session session = factory.getCurrentSession();
		Integer id = (Integer) session.save(movieInfo);
		return id;
	}

	//For MemberController movieservice.updateMovieViews(id); // 設定 點擊率加一   getClick_Sum() + 1
	@Override
	public void updateMovieViews(Integer id) {
		System.out.println("updateMovieViews=" + id);
		MovieInfo movie = factory.getCurrentSession().get(MovieInfo.class, id);

		movie.setClick_Sum(movie.getClick_Sum() + 1);
	}
	//刪除單一影片
	@Override
	public void deleteMovieInfoById(int movie_ID) {
		Session session = factory.getCurrentSession();
		MovieInfo movieinfo = session.get(MovieInfo.class, movie_ID);
		session.delete(movieinfo);
	}

	// DONE
	@Override
	public void updateMovieInfoById(MovieInfo movieInfo) {
		Session session = factory.getCurrentSession();
		session.update(movieInfo);
		//		factory.getCurrentSession().update(movieInfo);
		//		session.clear();
		//		session.update(movieInfo);	
	}

	@Override
	public MovieInfo getMovieInfoByMovieID(Integer movie_ID) {
		Session session = factory.getCurrentSession();
		MovieInfo movieInfo = session.get(MovieInfo.class, movie_ID);
		// get 不管 movie_ID的名稱，強制使用Table PK 作為條件
		//		session.save(movieInfo);
		return (MovieInfo) movieInfo;
	}

	// DONE
	@Override
	public List<MovieInfo> getMovies() {
		String hql = "FROM MovieInfo";
		List<MovieInfo> list = new ArrayList<>();
		Session session = factory.getCurrentSession();
		list = session.createQuery(hql).getResultList();
		return list;

	}

	// DONE
	@Override
	public List<MovieInfo> getMovieInfoByOwnerID(int owner_ID) {
		String hql = "FROM MovieInfo WHERE owner_ID = owner_ID";  
		List<MovieInfo> list = new ArrayList<>();
		Session session = factory.getCurrentSession();
		list = session.createQuery(hql).getResultList();
		return list;
	}

	//For MemberController movieservice.getMovieInfoByID(mem.getMember_id());
	@Override
	public ArrayList<MovieInfo> getMovieInfoByOwnerID(Integer id) {
		String hql = "FROM MovieInfo WHERE owner_ID = ?1";
		//指定 ? 帶入值為 setParameter(1, id) 的 id
		Query query = factory.getCurrentSession().createQuery(hql).setParameter(1, id);
		ArrayList<MovieInfo> MovieInfoArrayList = (ArrayList<MovieInfo>) query.getResultList();
		return MovieInfoArrayList;
	}

	@Override
	public ArrayList<MovieInfo> getNewMovieInfo(Integer dateLength) {
		String hql = "FROM MovieInfo order by Time desc";

		Query query = factory.getCurrentSession().createQuery(hql);
		ArrayList<MovieInfo> MovieInfoArrayList = (ArrayList<MovieInfo>) query.getResultList();
		return MovieInfoArrayList;
	}

}
