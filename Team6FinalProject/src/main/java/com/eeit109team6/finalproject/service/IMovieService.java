package com.eeit109team6.finalproject.service;

import java.util.ArrayList;
import java.util.List;

import com.eeit109team6.finalproject.model.MovieInfo;

public interface IMovieService {
	
	Integer addMovie(MovieInfo movieinfo); //新增影片

	void deleteMovieInfoById(int movie_ID); //刪除影片

	void updateMovieInfoById(MovieInfo movieinfo); //更新影片

	MovieInfo getMovieInfoByMovieID(Integer movie_ID); //搜尋單一影片ID的資訊
	
	List<MovieInfo> getMovieInfoByOwnerID(int owner_ID); //尋找使用者Owner_ID上傳的影片
	
	List<MovieInfo> getMovies();

	ArrayList<MovieInfo> getMovieInfoByID(Integer id);

	void updateMovieViews(Integer id);

	ArrayList<MovieInfo> getNewMovieInfo(Integer dateLength);
}
