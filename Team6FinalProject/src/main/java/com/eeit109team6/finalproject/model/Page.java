package com.eeit109team6.finalproject.model;

import java.util.List;

public class Page<T> {
	private List<T> list; //數據
	private Integer rows; //每頁顯示多少條記錄
	private Integer currentPage; //當前頁碼
	private Integer totalPage; //總共多少頁->可以產生頁數超連結
	private Integer totalCount; //總記錄數
	public List<T> getList() {
		return list;
	}
	public void setList(List<T> list) {
		this.list = list;
	}
	public Integer getRows() {
		return rows;
	}
	public void setRows(Integer rows) {
		this.rows = rows;
	}
	public Integer getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}
	public Integer getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}
	public Integer getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}
	
	//測試用
	@Override
	public String toString() {
		return "totalCount="+totalCount+", rows="+rows+", list="+list;
	}
	
	
	
}
