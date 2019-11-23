package com.eeit109team6.finalproject.service;

import java.util.List;

import com.eeit109team6.finalproject.model.Category;
import com.eeit109team6.finalproject.model.Comment;
import com.eeit109team6.finalproject.model.OrderItem;
import com.eeit109team6.finalproject.model.Page;
import com.eeit109team6.finalproject.model.Product;

public interface ProductService {
	
	List<Product> getAllProducts();
	public Product getProductById(int game_id);
	List<Category> getAllCategories();
	Page<Product> getProductsByCategory(Integer category_id, Integer currentPage, Integer rows);
	void addProduct(Product product);
	void deleteProductById(int game_id);
	void reAddProductById(int game_id);
	void updateProductById(Product product);
	List<Product> getAll();
	Page<Product> getProductByKeyWord(String keyWord, Integer currentPage, Integer rows);
	void addCategory(Category category);
	Category getCategoryById(Integer category_id);
	Page<Product> getProductsByHigh(Integer currentPage, Integer rows); //依價格高到低查詢商品(不含已下架)
	Page<Product> getProductsByLow(Integer currentPage, Integer rows); //依價格低到高查詢所有商品(不含已下架)
	void addComment(Comment comment);
	List<Comment> getCommentById(Integer game_id);
	void editComment(Integer comment_id, String comment);
	void deleteCommentById(int comment_id);
	Page<Product> findProductsByPage(Integer currentPage, Integer rows);
	List<Product> getCancelProducts();
	List<Product> getProductTop8();
	List<OrderItem> getOrderItem();
}
