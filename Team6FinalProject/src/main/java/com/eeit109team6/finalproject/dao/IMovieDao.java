package com.eeit109team6.finalproject.dao;

import java.util.ArrayList;
import java.util.List;

import com.eeit109team6.finalproject.model.MovieInfo;


public interface IMovieDao {

	Integer addMovie(MovieInfo movieinfo); //新增影片
	void deleteMovieInfoById(int movie_ID); //刪除影片
	void updateMovieInfoById(MovieInfo movieinfo); //更新影片
	MovieInfo getMovieInfoByMovieID(Integer movie_ID); //查詢指定影片ID資訊

	
	List<MovieInfo> getMovieInfoByOwnerID(int owner_ID); //尋找使用者Owner_ID上傳的影片
	List<MovieInfo> getMovies();
	ArrayList<MovieInfo> getMovieInfoByOwnerID(Integer id);
	void updateMovieViews(Integer id);
	ArrayList<MovieInfo> getNewMovieInfo(Integer dateLength);
	
	
}
