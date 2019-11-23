package com.eeit109team6.finalproject.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.rowset.serial.SerialBlob;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.eeit109team6.finalproject.model.Category;
import com.eeit109team6.finalproject.model.Comment;
import com.eeit109team6.finalproject.model.Member;
import com.eeit109team6.finalproject.model.OrderItem;
import com.eeit109team6.finalproject.model.Page;
import com.eeit109team6.finalproject.model.Product;
import com.eeit109team6.finalproject.service.ProductService;

@Controller
public class ProductController {
	ProductService service;

	@Autowired
	public void setService(ProductService service) {
		this.service = service;
	}
	
	ServletContext context;
	
	@Autowired
	public void setContext(ServletContext context) {
		this.context = context;
	}
	
	// 查詢所有商品--> 商城前台 products.jsp
	@RequestMapping("/products")
	public String list(Model model, HttpSession session) {
		Member mem = new Member();
		mem.setAccount("sandy60108@yahoo.com.tw");
		mem.setPassword("a14789632");
		mem.setUsername("andy");
		List<Product> list = service.getAllProducts();
		model.addAttribute("products", list);
		session.setAttribute("products", list);
		model.addAttribute("Member", mem);
		return "products";
	}

	// 商城管理 --> manager.jsp
	@RequestMapping("/manager")
	public String manager(Model model) {
		return "manager";
	}

	// 提供新增商品時的表單--> addProduct.jsp
	@RequestMapping(value = "/productsBack/add", method = RequestMethod.GET)
	public String getAddNewProductForm(Model model) {
	
		Product product = new Product();
		model.addAttribute("product", product);
		
		List<Category> list = service.getAllCategories();
		Map<Integer, String> categoryMap = new HashMap<>();
		for(Category c : list) {
			categoryMap.put(c.getCategory_id(), c.getCategory());
		}
		model.addAttribute("categoryMap", categoryMap);
		
		return "addProduct";
	}

	// 新增商品--> 商城後台 productsBack.jsp
	@RequestMapping(value = "/productsBack/add", method = RequestMethod.POST)
	public String processAddNewProductForm(@ModelAttribute("product") Product product) {
		Date date = new Date();
		product.setDate(date);
		//測試上傳圖片
		MultipartFile productImage = product.getProductImage();
		String originalFilename = productImage.getOriginalFilename();
		if(productImage != null && !productImage.isEmpty()) {
			try {
				byte[] b = productImage.getBytes();
				Blob blob = new SerialBlob(b);
				product.setPhoto(blob);
			} catch(Exception e) {
				e.printStackTrace();
				throw new RuntimeException("檔案上傳發生異常:"+e.getMessage());
			}
		}
		Integer c_ = product.getCategory_();   //取回分類id
		Category c = service.getCategoryById(c_);  //利用id取得Category
		product.setCategory(c);  //設定商品的Category，會自動關聯
		//測試上傳圖片
		service.addProduct(product);
		return "redirect:/productsBack";
	}

	// 依照分類查詢商品
	@RequestMapping("/queryCategory")
	public String getProductsByCategory(@RequestParam("category_id") Integer category_id,Integer currentPage,
			Integer rows, Model model) {
		if(currentPage == null || "".equals(currentPage+"")) {
			currentPage=1;
		}
		if(rows == null || "".equals(rows+"")) {
			rows=4;
		}
		Page<Product> page = service.getProductsByCategory(category_id, currentPage, rows);
		model.addAttribute("pages", page);
		
		model.addAttribute("category_id", category_id);
		
		return "productsByCategory";
	}

	// 查詢所有商品分類並存入Model
	@ModelAttribute("categories")
	public List<Category> getCategories() {
		return service.getAllCategories();
	}

	// 查詢單筆商品詳細資料--> product.jsp
	@RequestMapping("/product")
	public String getProductById(@RequestParam("game_id") Integer game_id, Model model, HttpSession session) {
		Member mem = new Member();
		mem.setAccount("sandy60108@yahoo.com.tw");
		mem.setPassword("a14789632");
		mem.setUsername("andy");
		model.addAttribute("Member", mem);
		List<Product> list = service.getAllProducts();
		session.setAttribute("products", list);
		Product product = service.getProductById(game_id);
		model.addAttribute("product", product);
		
		List<Comment> comment = service.getCommentById(game_id);
		model.addAttribute("comments", comment);
		
		return "product";
	}
	
	// 查詢單筆商品詳細資料--> product.jsp
		@RequestMapping("/productComment.json")
		public String getProductComment(@RequestParam("game_id") Integer game_id, Model model, HttpSession session) {
			
			List<Comment> comment = service.getCommentById(game_id);
			model.addAttribute("comments", comment);
			
			return "product";
		}
	

	// 查詢所有商品--> 商城後台 productsBack.jsp
	@RequestMapping("/productsBack")
	public String listBack(Model model) {
		System.out.println("productsBack");
		List<Product> list = service.getAllProducts();
		model.addAttribute("products", list);
		List<Product> c_list = service.getCancelProducts();
		model.addAttribute("cancelProduct", c_list);
		Product product = new Product();
		model.addAttribute("product", product);
		List<Category> categories = service.getAllCategories();
		Map<Integer, String> categoryMap = new HashMap<>();
		for(Category c : categories) {
			categoryMap.put(c.getCategory_id(), c.getCategory());
		}
		model.addAttribute("categoryMap", categoryMap);
		
		List<OrderItem> o_list = service.getOrderItem();
		Map<String, Integer> map = new HashMap<>();
		for(OrderItem oi : o_list) {
			if(map.containsKey(oi.getProduct().getName())) {
//				System.out.println("如果有"+oi.getProduct().getName());
				int count = map.get(oi.getProduct().getName());
				count++;
				map.put(oi.getProduct().getName(), count);
			}else{
//				System.out.println("如果沒有"+oi.getProduct().getName());
				map.put(oi.getProduct().getName(), 1);
			}
		}
		model.addAttribute("countMap", map);
		
		return "productsBack";
	}

	
	//所有商品的json格式--> 商城後台 productsBack.jsp
	@RequestMapping("/productsBackjson.json")
	public String productsBackJson(Model model) {
		System.out.println("productsBackjson");
	
		ArrayList<Product> list = (ArrayList<Product>) service.getAllProducts();
		model.addAttribute("ProductJson", list); //送架上商品的json

		ArrayList<Product> c_list = (ArrayList<Product>) service.getCancelProducts();
		model.addAttribute("CanProductJson", c_list); //送架下商品的json
		
		return "productsBack";
	}
	
	
	// 商品下架--> 商城後台 productsBack.jsp
	@RequestMapping("/productsBack/products/delete")
	public String deleteProductById(@RequestParam("game_id") Integer game_id, Model model) {
		service.deleteProductById(game_id);
		List<Product> products = service.getAllProducts();
		model.addAttribute("products", products);
		return "redirect:/productsBack";
	}
	
	// 將已下架商品重新上架--> 商城後台 productsBack.jsp
	@RequestMapping("/productsBack/products/reAdd")
	public String reAddProductById(@RequestParam("game_id") Integer game_id, Model model) {
		service.reAddProductById(game_id);
		List<Product> products = service.getAllProducts();
		model.addAttribute("products", products);
		return "redirect:/productsBack";
	}

	// 提供更新商品時的表單--> updateProduct.jsp
	@RequestMapping(value = "/productsBack/products/update", method = RequestMethod.GET)
	public String getUpdateProductForm(@RequestParam("game_id") Integer game_id, Model model) {
		Product product = service.getProductById(game_id);
		model.addAttribute("product", product);
		
		List<Category> list = service.getAllCategories();
		Map<Integer, String> categoryMap = new HashMap<>();
		for(Category c : list) {
			categoryMap.put(c.getCategory_id(), c.getCategory());
		}
		model.addAttribute("categoryMap", categoryMap);
		
		return "updateProduct";
	}

	// 更新商品--> 商城後台 productsBack.jsp
	@RequestMapping(value = "/productsBack/products/update", method = RequestMethod.POST)
	public String processUpdateProductForm(@ModelAttribute("product") Product product, @RequestParam("game_id") Integer game_id) {
		Date date = new Date();
		product.setDate(date);
		
		//上傳圖片begin
		MultipartFile productImage = product.getProductImage();
		if(productImage.getSize() == 0) {
			Product original = service.getProductById(game_id);
			product.setPhoto(original.getPhoto());
		}else {
			String originalFilename = productImage.getOriginalFilename();
			if(productImage != null && !productImage.isEmpty()) {
				try {
					byte[] b = productImage.getBytes();
					Blob blob = new SerialBlob(b);
					product.setPhoto(blob);
				} catch(Exception e) {
					e.printStackTrace();
					throw new RuntimeException("檔案上傳發生異常:"+e.getMessage());
				}
			}
		}
		//上傳圖片end
		
		Integer c_ = product.getCategory_();   //取回分類id
		Category c = service.getCategoryById(c_);  //利用id取得Category
		product.setCategory(c);
		
		service.updateProductById(product);
		return "redirect:/productsBack";
	}
	
	
	
	// 查詢所有商品(含已下架)--> 商城後台 productsBack.jsp
	@RequestMapping("/productsBack/all")
	public String getAll(Model model) {
		List<Product> list = service.getAllProducts();
		model.addAttribute("products", list);
		return "redirect:/productsBack";
	}
	
	@RequestMapping(value="getPicture/{game_id}", method=RequestMethod.GET)
	public ResponseEntity<byte[]> getPicture(HttpServletResponse resp, @PathVariable Integer game_id){
		String filePath = "resources/images/NoImage.jpg";
		byte[] media = null;
		HttpHeaders headers = new HttpHeaders();
		int len = 0;
		Product product = service.getProductById(game_id);
		if(product != null) {
			Blob blob = product.getPhoto();
			if(blob != null) { //資料庫有圖片
				try {
					len = (int) blob.length();
					media = blob.getBytes(1, len);
				} catch(SQLException e) {
					throw new RuntimeException("ProductController的getPicture()發生SQLException:"+e.getMessage());
				}
			}else { //資料庫沒圖片
				media = toByteArray(filePath);
			}
		}
		headers.setCacheControl(CacheControl.noCache().getHeaderValue());
		ResponseEntity<byte[]> responseEntity = new ResponseEntity<>(media, headers, HttpStatus.OK);
		return responseEntity;
	}

	private byte[] toByteArray(String filePath) {
		byte[] b = null;
		String realPath = context.getRealPath(filePath);
		try {
			File file = new File(realPath);
			long size = file.length();
			b = new byte[(int)size];
			InputStream fis = context.getResourceAsStream(filePath);
			fis.read(b);
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		}
		return b;
	}
	
	//關鍵字查詢--> 商城前台 products.jsp
	@RequestMapping("/getProductByKeyWord")
	public String getProductByKeyWord( String keyWord, Model model,Integer currentPage,
			Integer rows) {
		if(currentPage == null || "".equals(currentPage+"")) {
			currentPage=1;
		}
		if(rows == null || "".equals(rows+"")) {
			rows=4;
		}

		
		Page<Product> page = service.getProductByKeyWord(keyWord, currentPage, rows);
		model.addAttribute("pages", page);
		
		model.addAttribute("keyWord", keyWord);
		
		return "productsByKeyWord";
	}
	
	//新增商品分類
	@RequestMapping("/productsBack/addCategory")
	public String addCategory(@RequestParam("category") String category) {
		Category c = new Category();
		c.setCategory(category);
		service.addCategory(c);
		return "redirect:/productsBack";
	}
	
	//低到高
	@RequestMapping("queryProductByLow")
	public String getProductByLow(Integer currentPage, 
			Integer rows, Model model, HttpSession session) {
		if(currentPage == null || "".equals(currentPage+"")) {
			currentPage=1;
		}
		if(rows == null || "".equals(rows+"")) {
			rows=4;
		}
		Page<Product> page = service.getProductsByLow(currentPage, rows);
		model.addAttribute("pages", page);
		
		return "productsByPriceL";
	}
	
	//高到低
	@RequestMapping("queryProductByHigh")
	public String getProductByHigh(Integer currentPage, 
			Integer rows, Model model, HttpSession session) {
		if(currentPage == null || "".equals(currentPage+"")) {
			currentPage=1;
		}
		if(rows == null || "".equals(rows+"")) {
			rows=4;
		}
		Page<Product> page = service.getProductsByHigh(currentPage, rows);
		model.addAttribute("pages", page);
		
		return "productsByPriceH";
	}
	
	//新增商品評論-> 商品前台
	@RequestMapping("/addComment")
	public String addComment(@RequestParam("comment") String comment, @RequestParam("game_id") Integer game_id,
			Model model, HttpSession session) {
		
		Member member = (Member)session.getAttribute("mem");
		if(member == null) {
			Member mem = new Member();
			mem.setAccount("sandy60108@yahoo.com.tw");
			mem.setPassword("a14789632");
			mem.setUsername("andy");
			model.addAttribute("Member", mem);
			model.addAttribute("msg", "您必須先登入!");
			return "jump";
		}
		
		Comment c = new Comment();
		
		c.setComment(comment);
		
		SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date d = new Date();
		String sd = sdFormat.format(d);
		c.setTime(sd);
		
		Product p = service.getProductById(game_id);
		c.setProduct(p);
		
		c.setMember_name(member.getUsername());
		
		c.setIs_remove(false);
		
		service.addComment(c);
		
		return "redirect:/product?game_id="+game_id;
	}
	
	//新增商品評論-> 商品後台
		@RequestMapping("/productsBack/addComment")
		public String addCommentProductBack(@RequestParam("comment") String comment, @RequestParam("game_id") Integer game_id,
				Model model, HttpSession session) {
			
			Member member = (Member)session.getAttribute("mem");
			if(member == null) {
				Member mem = new Member();
				mem.setAccount("sandy60108@yahoo.com.tw");
				mem.setPassword("a14789632");
				mem.setUsername("andy");
				model.addAttribute("Member", mem);
				model.addAttribute("msg", "您必須先登入才能進行評論!");
				return "jump";
			}
			
			Comment c = new Comment();
			
			c.setComment(comment);
			
			SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date d = new Date();
			String sd = sdFormat.format(d);
			c.setTime(sd);
			
			Product p = service.getProductById(game_id);
			c.setProduct(p);
			
			c.setMember_name(member.getUsername());
			
			c.setIs_remove(false);
			
			service.addComment(c);
			
			return "redirect:/productsBack/productBack?game_id="+game_id;
		}
	
	//編輯商品評論
	@RequestMapping("/edditComment")
	public String edditComment(@RequestParam("game_id") Integer game_id,
			@RequestParam("comment_id") Integer comment_id, @RequestParam("comment") String comment) {
		service.editComment(comment_id, comment);
		return "redirect:/product?game_id="+game_id;
	}
	
	//刪除商品評論
	@RequestMapping("/removeComment")
	public String removeComment(@RequestParam("game_id") Integer game_id,
			@RequestParam("comment_id") Integer comment_id) {
		service.deleteCommentById(comment_id);
		return "redirect:/productsBack/productBack?game_id="+game_id;
	}
	
	//依照頁碼查詢商品
	@RequestMapping("/findProductsByPage")
	public String findProductsByPage(Integer currentPage, 
			Integer rows, Model model, HttpSession session) {
		if(currentPage == null || "".equals(currentPage+"")) {
			currentPage=1;
		}
		if(rows == null || "".equals(rows+"")) {
			rows=4;
		}
		Page<Product> page = service.findProductsByPage(currentPage,rows);
//		System.out.println(page);
		model.addAttribute("pages", page);
		
		List<Product> list = service.getAllProducts();
		session.setAttribute("products", list);
		
		return "products";
	}
	
	// 查詢單筆商品詳細資料--> productBack.jsp
	@RequestMapping("/productsBack/productBack")
	public String getProductByIdToBack(@RequestParam("game_id") Integer game_id, Model model) {
		
		Product product = service.getProductById(game_id);
		model.addAttribute("product", product);
			
		List<Comment> comment = service.getCommentById(game_id);
		model.addAttribute("comments", comment);
		
		List<Category> list = service.getAllCategories();
		Map<Integer, String> categoryMap = new HashMap<>();
		for(Category c : list) {
			categoryMap.put(c.getCategory_id(), c.getCategory());
		}
		model.addAttribute("categoryMap", categoryMap);
			
		return "productBack";
	}
	
	// 新增商品--> 商城後台 productsBack.jsp
		@RequestMapping(value = "/productsBack/addProduct")
		public String addProduct(Integer category_id, String name, String publisher, Integer price, Integer stock,
				String game_desc, Integer is_remove, MultipartFile productImage) {
			Product product = new Product();
			product.setName(name);
			product.setPublisher(publisher);
			product.setPrice(price);
			product.setStock(stock);
			product.setGame_desc(game_desc);
			product.setIs_remove(is_remove);
			Date date = new Date();
			product.setDate(date);
			//測試上傳圖片
			
			String originalFilename = productImage.getOriginalFilename();
			if(productImage != null && !productImage.isEmpty()) {
				try {
					byte[] b = productImage.getBytes();
					Blob blob = new SerialBlob(b);
					product.setPhoto(blob);
				} catch(Exception e) {
					e.printStackTrace();
					throw new RuntimeException("檔案上傳發生異常:"+e.getMessage());
				}
			}
			Category c = service.getCategoryById(category_id);  //利用id取得Category
			product.setCategory(c);  //設定商品的Category，會自動關聯
			//測試上傳圖片
			service.addProduct(product);
			return "redirect:/productsBack";
		}
		
	//更新商品資訊
	@RequestMapping("/productsBack/updateProduct")
	public String updateProduct(@RequestParam("game_id") Integer game_id, Integer category_id, String name, String publisher, Integer price, Integer stock,
			String game_desc, MultipartFile productImage) {
		Product original = service.getProductById(game_id);
		original.setCategory(service.getCategoryById(category_id));
		original.setName(name);
		original.setPublisher(publisher);
		original.setPrice(price);
		original.setStock(stock);
		original.setGame_desc(game_desc);
		original.setIs_remove(0);
		
		//上傳圖片begin
				
				if(productImage.getSize() == 0) {
					original.setPhoto(original.getPhoto());
				}else {
					String originalFilename = productImage.getOriginalFilename();
					if(productImage != null && !productImage.isEmpty()) {
						try {
							byte[] b = productImage.getBytes();
							Blob blob = new SerialBlob(b);
							original.setPhoto(blob);
						} catch(Exception e) {
							e.printStackTrace();
							throw new RuntimeException("檔案上傳發生異常:"+e.getMessage());
						}
					}
				}
		//上傳圖片end
				
		service.updateProductById(original);
		
		return "redirect:/productsBack/productBack?game_id="+game_id;
	}
	
	
	
}