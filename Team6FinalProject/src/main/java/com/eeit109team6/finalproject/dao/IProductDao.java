package com.eeit109team6.finalproject.dao;

import java.util.List;

import com.eeit109team6.finalproject.model.Category;
import com.eeit109team6.finalproject.model.Comment;
import com.eeit109team6.finalproject.model.OrderItem;
import com.eeit109team6.finalproject.model.Product;

public interface IProductDao {

	List<Product> getAllProducts(); //查詢所有商品(不含已下架)
	public Product getProductById(int game_id); //依照id查詢單筆商品詳細資訊
	List<Category> getAllCategories(); //查詢所有商品分類
	int getProductsTotalByCategory(Integer category_id); //計算依分類查詢商品的總數
	List<Product> getProductsByCategory(Integer category_id, Integer start, Integer rows); //依照分類查詢商品(不含已下架)
	void addProduct(Product product); //新增商品
	void deleteProductById(int game_id); //刪除商品，將is_remove改為1，表示商品已下架，但資料庫依然有紀錄
	void reAddProductById(int game_id); //重新上架商品，將is_remove改為0，表示將已下架商品重新上架
	void updateProductById(Product product); //更新商品
	List<Product> getAll(); //查詢所有商品(含已下架)
	int findTotalCountProductByKeyWord(String keyWord); //計算關鍵字查詢資料總數
	List<Product> getProductByKeyWord(String keyWord, Integer start, Integer rows); //關鍵字查詢
	void addCategory(Category category); //新增商品分類
	Category getCategoryById(Integer category_id); //依分類id查詢分類，以便之後可新增、更新商品
	List<Product> getProductsByHigh(Integer start, Integer rows); //依價格高到低查詢商品(不含已下架)
	List<Product> getProductsByLow(Integer start, Integer rows); //依價格低到高查詢所有商品(不含已下架)
	void addComment(Comment comment); //新增商品評論
	List<Comment> getCommentById(Integer game_id); //依商品id取得評論
	void deleteCommentById(int comment_id); //依商品id刪除評論
	void editComment(Integer comment_id, String comment); //更新商品評論
	int findTotalCount(); //查詢商品總記錄數量
	List<Product> findByPage(int start, Integer rows); //依照頁碼查詢每頁商品
	List<Product> getCancelProducts(); //查詢已下架商品
	List<Product> getProductTop8(); //查詢8筆最新商品
	List<OrderItem> getOrderItem(); //查詢訂單細項，取得後可統計商品購買次數
}
