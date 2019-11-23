package com.eeit109team6.finalproject.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eeit109team6.finalproject.dao.IProductDao;
import com.eeit109team6.finalproject.model.Category;
import com.eeit109team6.finalproject.model.Comment;
import com.eeit109team6.finalproject.model.OrderItem;
import com.eeit109team6.finalproject.model.Page;
import com.eeit109team6.finalproject.model.Product;
import com.eeit109team6.finalproject.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	IProductDao dao;
	@Autowired
	public void setDao(IProductDao dao) {
		this.dao = dao;
	}

	@Transactional
	@Override
	public List<Product> getAllProducts() {
		return dao.getAllProducts();
	}

	@Transactional
	@Override
	public void addProduct(Product product) {
		dao.addProduct(product);		
	}

	@Transactional
	@Override
	public List<Category> getAllCategories() {
		return dao.getAllCategories();
	}

	@Transactional
	@Override
	public Page<Product> getProductsByCategory(Integer category_id, Integer currentPage, Integer rows) {
		Page<Product> page = new Page<Product>();
		page.setCurrentPage(currentPage);
		page.setRows(rows);
		int totalCount = dao.getProductsTotalByCategory(category_id);
		page.setTotalCount(totalCount);
		int start = (currentPage - 1) * rows;
		List<Product> list = dao.getProductsByCategory(category_id, start, rows);
		page.setList(list);
		int totalPage = (totalCount % rows) == 0 ? (totalCount/rows) : (totalCount/rows)+1;
		page.setTotalPage(totalPage);
		return page;
	}

	@Transactional
	@Override
	public Product getProductById(int game_id) {
		return dao.getProductById(game_id);
	}

	@Transactional
	@Override
	public void deleteProductById(int game_id) {
		dao.deleteProductById(game_id);		
	}
	
	@Transactional
	@Override
	public void reAddProductById(int game_id) {
		dao.reAddProductById(game_id);
	}

	@Transactional
	@Override
	public void updateProductById(Product product) {
		dao.updateProductById(product);
	}

	@Transactional
	@Override
	public List<Product> getAll() {
		return dao.getAll();
	}

	@Transactional
	@Override
	public Page<Product> getProductByKeyWord(String keyWord, Integer currentPage, Integer rows) {
		Page<Product> page = new Page<Product>();
		page.setCurrentPage(currentPage);
		page.setRows(rows);
		int totalCount = dao.findTotalCountProductByKeyWord(keyWord);
		page.setTotalCount(totalCount);
		int start = (currentPage - 1) * rows;
		List<Product> list = dao.getProductByKeyWord(keyWord, start, rows);
		page.setList(list);
		int totalPage = (totalCount % rows) == 0 ? (totalCount/rows) : (totalCount/rows)+1;
		page.setTotalPage(totalPage);
		return page;
	}


	@Transactional
	@Override
	public void addCategory(Category category) {
		dao.addCategory(category);
	}
	@Transactional
	@Override
	public Category getCategoryById(Integer category_id) {
		return dao.getCategoryById(category_id);
	}

	@Transactional
	@Override
	public Page<Product> getProductsByHigh(Integer currentPage, Integer rows) {
		Page<Product> page = new Page<Product>();
		page.setCurrentPage(currentPage);
		page.setRows(rows);
		int totalCount = dao.findTotalCount();
		page.setTotalCount(totalCount);
		int start = (currentPage - 1) * rows;
		List<Product> list = dao.getProductsByHigh(start, rows);
		page.setList(list);
		int totalPage = (totalCount % rows) == 0 ? (totalCount/rows) : (totalCount/rows)+1;
		page.setTotalPage(totalPage);
		return page;
	}

	@Transactional
	@Override
	public Page<Product> getProductsByLow(Integer currentPage, Integer rows) {
		Page<Product> page = new Page<Product>();
		page.setCurrentPage(currentPage);
		page.setRows(rows);
		int totalCount = dao.findTotalCount();
		page.setTotalCount(totalCount);
		int start = (currentPage - 1) * rows;
		List<Product> list = dao.getProductsByLow(start, rows);
		page.setList(list);
		int totalPage = (totalCount % rows) == 0 ? (totalCount/rows) : (totalCount/rows)+1;
		page.setTotalPage(totalPage);
		return page;
	}

	@Transactional
	@Override
	public void addComment(Comment comment) {
		dao.addComment(comment);
	}

	@Transactional
	@Override
	public List<Comment> getCommentById(Integer game_id) {
		return dao.getCommentById(game_id);
	}
	
	@Transactional
	@Override
	public void editComment(Integer comment_id, String comment) {
		dao.editComment(comment_id, comment);
	}
	
	@Transactional
	@Override
	public void deleteCommentById(int comment_id) {
		dao.deleteCommentById(comment_id);
	}


	@Transactional
	@Override
	public Page<Product> findProductsByPage(Integer currentPage, Integer rows) {
		Page<Product> page = new Page<Product>();
		page.setCurrentPage(currentPage);
		page.setRows(rows);
		int totalCount = dao.findTotalCount();
		page.setTotalCount(totalCount);
		int start = (currentPage - 1) * rows;
		List<Product> list = dao.findByPage(start, rows);
		page.setList(list);
		int totalPage = (totalCount % rows) == 0 ? (totalCount/rows) : (totalCount/rows)+1;
		page.setTotalPage(totalPage);
		return page;
	}

	@Transactional
	@Override
	public List<Product> getCancelProducts() {
		return dao.getCancelProducts();
	}

	@Transactional
	@Override
	public List<Product> getProductTop8() {
		return dao.getProductTop8();
	}

	@Transactional
	@Override
	public List<OrderItem> getOrderItem() {
		return dao.getOrderItem();
	}

	
	

	

}
