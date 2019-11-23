package com.eeit109team6.finalproject.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.eeit109team6.finalproject.model.HomeMovie;
import com.eeit109team6.finalproject.model.Movie;

public interface IHomeMovieService {
//	=============================================== HomeMovie =======================================================
	HomeMovie findById(Integer id);
	void updateMovieid(HomeMovie homeMovie); //更新版塊影片
	
//	=============================================== Movie =======================================================
	Integer addMovie(Movie movie); //新增影片
	void deleteMovieById(Integer deleteMovieId); //刪除影片
	void updateMovieById(Movie movie); //更新影片
	List<Movie> findAll();//查詢全部影片
	Movie getMovieInfoByMovieID(Integer id); //查詢指定影片ID資訊
	Movie moviefindById(Integer movieId); // 查詢指定影片名稱 movieName 資訊
}