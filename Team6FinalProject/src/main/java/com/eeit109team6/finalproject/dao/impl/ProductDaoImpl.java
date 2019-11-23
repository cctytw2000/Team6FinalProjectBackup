package com.eeit109team6.finalproject.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.eeit109team6.finalproject.dao.IProductDao;
import com.eeit109team6.finalproject.model.Category;
import com.eeit109team6.finalproject.model.Comment;
import com.eeit109team6.finalproject.model.OrderItem;
import com.eeit109team6.finalproject.model.Product;

@Repository
public class ProductDaoImpl implements IProductDao {

	SessionFactory factory;

	@Autowired
	public void setSession(SessionFactory factory) {
		this.factory = factory;
	}

	@Override
	public List<Product> getAllProducts() {
		String hql = "FROM Product p WHERE p.is_remove = 0";
		List<Product> list = new ArrayList<>();
		Session session = factory.getCurrentSession();
		list = session.createQuery(hql).getResultList();
		return list;
	}

	@Override
	public void addProduct(Product product) {
		Session session = factory.getCurrentSession();
		session.save(product);
	}

	@Override
	public List<Category> getAllCategories() {
//		String hql = "SELECT DISTINCT p.category FROM Product p";
		String hql = "FROM Category";
		Session session = factory.getCurrentSession();
		List<Category> list = new ArrayList<>();
		list = session.createQuery(hql).getResultList();
		return list;
	}

	@Override
	public int getProductsTotalByCategory(Integer category_id) {
		String hql = "FROM Product WHERE category_id = :category_id AND is_remove = 0";
		List<Product> list = new ArrayList<>();
		Session session = factory.getCurrentSession();
		list = session.createQuery(hql).setParameter("category_id", category_id).getResultList();

		return list.size();
	}

	@Override
	public List<Product> getProductsByCategory(Integer category_id, Integer start, Integer rows) {
		String hql = "FROM Product WHERE category_id = :category_id AND is_remove = 0";
		List<Product> list = new ArrayList<>();
		Session session = factory.getCurrentSession();
		list = session.createQuery(hql).setParameter("category_id", category_id).setFirstResult(start)
				.setMaxResults(rows).getResultList();
		return list;
	}

	@Override
	public Product getProductById(int game_id) {
		Session session = factory.getCurrentSession();
		Product product = session.get(Product.class, game_id);
		return product;
	}

	// 將is_remove改為1，表示商品已下架，但資料庫依然有紀錄
	@Override
	public void deleteProductById(int game_id) {
		Session session = factory.getCurrentSession();
		Product product = session.get(Product.class, game_id);
		product.setIs_remove(1);
		session.update(product);
	}

	// 將is_remove改為0，表示將已下架商品重新上架
	@Override
	public void reAddProductById(int game_id) {
		Session session = factory.getCurrentSession();
		Product product = session.get(Product.class, game_id);
		product.setIs_remove(0);
		session.update(product);
	}

	@Override
	public void updateProductById(Product product) {
		Session session = factory.getCurrentSession();
		session.clear();
		session.update(product);
	}

	@Override
	public List<Product> getAll() {
		String hql = "FROM Product";
		List<Product> list = new ArrayList<>();
		Session session = factory.getCurrentSession();
		list = session.createQuery(hql).getResultList();
		return list;
	}

	@Override
	public int findTotalCountProductByKeyWord(String keyWord) {
		String hql = "FROM Product p where p.name LIKE'%" + keyWord + "%'";
		List<Product> list = new ArrayList<>();
		Session session = factory.getCurrentSession();
		list = session.createQuery(hql).getResultList();

		return list.size();
	}

	@Override
	public List<Product> getProductByKeyWord(String keyWord, Integer start, Integer rows) {
		String hql = "FROM Product p where p.name LIKE'%" + keyWord + "%'";
		Session session = factory.getCurrentSession();
		List<Product> list = session.createQuery(hql).setFirstResult(start).setMaxResults(rows).getResultList();
		return list;
	}

	@Override
	public void addCategory(Category category) {
		Session session = factory.getCurrentSession();
		session.save(category);
	}

	@Override
	public Category getCategoryById(Integer category_id) {
		Session session = factory.getCurrentSession();
		Category category = session.get(Category.class, category_id);
		return category;
	}

	@Override
	public List<Product> getProductsByHigh(Integer start, Integer rows) {
		String hql = "FROM Product WHERE is_remove = 0 ORDER BY price DESC"; // 高到低
		Session session = factory.getCurrentSession();
		List<Product> list = new ArrayList<>();
		list = session.createQuery(hql).setFirstResult(start).setMaxResults(rows).getResultList();
		return list;
	}

	@Override
	public List<Product> getProductsByLow(Integer start, Integer rows) {
		String hql = "FROM Product WHERE is_remove = 0 ORDER BY price ASC"; // 低到高
		Session session = factory.getCurrentSession();
		List<Product> list = new ArrayList<>();
		list = session.createQuery(hql).setFirstResult(start).setMaxResults(rows).getResultList();
		return list;
	}

	@Override
	public void addComment(Comment comment) {
		Session session = factory.getCurrentSession();
		session.save(comment);
	}

	@Override
	public List<Comment> getCommentById(Integer game_id) {
		String hql = "FROM Comment WHERE game_id = :game_id AND is_remove = 0 ORDER BY time DESC";
		Session session = factory.getCurrentSession();
		List<Comment> list = session.createQuery(hql).setParameter("game_id", game_id).getResultList();
		return list;
	}

	@Override
	public void editComment(Integer comment_id, String comment) {
		String hql = "update Comment set comment = :comment where comment_id = :comment_id";
		Session session = factory.getCurrentSession();
		session.createQuery(hql).setParameter("comment", comment).setParameter("comment_id", comment_id)
				.executeUpdate();
	}

	@Override
	public void deleteCommentById(int comment_id) {
		String hql = "update Comment set is_remove = 1 where comment_id = :comment_id";
		Session session = factory.getCurrentSession();
		session.createQuery(hql).setParameter("comment_id", comment_id).executeUpdate();
	}

	@Override
	public int findTotalCount() {
		String hql = "FROM Product p WHERE p.is_remove = 0";
		List<Product> list = new ArrayList<>();
		Session session = factory.getCurrentSession();
		list = session.createQuery(hql).getResultList();

		return list.size();
	}

	@Override
	public List<Product> findByPage(int start, Integer rows) {
		String hql = "FROM Product p WHERE p.is_remove = 0";
		Session session = factory.getCurrentSession();
		List<Product> list = session.createQuery(hql).setFirstResult(start).setMaxResults(rows).getResultList();

		return list;
	}

	@Override
	public List<Product> getCancelProducts() {
		String hql = "FROM Product p WHERE p.is_remove = 1";
		List<Product> list = new ArrayList<>();
		Session session = factory.getCurrentSession();
		list = session.createQuery(hql).getResultList();
		return list;
	}

	@Override
	public List<Product> getProductTop8() {
		String hql = "FROM Product p WHERE p.is_remove = 0 ORDER BY date DESC";
		Session session = factory.getCurrentSession();
		List<Product> list = session.createQuery(hql).setMaxResults(8).getResultList(); 
		return list;
	}

	@Override
	public List<OrderItem> getOrderItem() {
		String hql = "FROM OrderItem";
		Session session = factory.getCurrentSession();
		List<OrderItem> list = session.createQuery(hql).getResultList(); 
		return list;
	}

}
