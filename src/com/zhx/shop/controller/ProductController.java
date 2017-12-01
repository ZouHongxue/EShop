package com.zhx.shop.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhx.shop.dao.ProductDao;
import com.zhx.shop.entity.Cart;
import com.zhx.shop.entity.OrderInfo;
import com.zhx.shop.entity.Product;
import com.zhx.shop.service.CartService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("/product")
public class ProductController {

	@RequestMapping(value="/getHotProduct.do",produces="text/html;charset=utf-8")
	@ResponseBody
	public String getHotProduct(String type){
		ProductDao productDao = new ProductDao();
		List<Product> list = productDao.getProduct(type);
		JSONObject json = new JSONObject();
		json.put("product", list);
		return json.toString();
	}
	
	@RequestMapping("/product_info.do")
	public String getProductById(String id,HttpServletRequest request){
		ProductDao productDao = new ProductDao();
		Product product = productDao.getProductById(id);
		HttpSession session = request.getSession();
		session.setAttribute("pro", product);
		return "redirect:/jsp/product_info.jsp";
	}
	
	@RequestMapping(value="/addcart.do",produces="text/html;charset=utf-8")
	@ResponseBody
	public String addCart(String pid,String count,HttpServletRequest request){
		HttpSession session = request.getSession();
		String name = (String) session.getAttribute("user");
		CartService cartService = new CartService();
		String rs = cartService.addCart(name, pid, Integer.valueOf(count));
		return rs;
	}
	
	@RequestMapping(value="/getcart.do",produces="text/html;charset=utf-8")
	@ResponseBody
	public String getCart(HttpServletRequest request){
		HttpSession session = request.getSession();
		String name = (String) session.getAttribute("user");
		ProductDao productDao = new ProductDao();
		List<Cart> list = productDao.getCart(name);
		JSONObject json = new JSONObject();
		json.put("cart", list);
		return json.toString();
	}
	
	@RequestMapping("/delorder.do")
	public String delOrder(String orderId,HttpServletRequest request){
		ProductDao productDao = new ProductDao();
		productDao.delOrder(orderId);
		return "redirect:/jsp/cart.jsp";
	}
	
	@RequestMapping("/addinfo.do")
	@ResponseBody
	public String addInfo(String t_total,String pay_way,String address,String name,String tel,String orderId){
//		System.out.println(t_total+"\t"+pay_way+"\t"+address+"\t"+name+"\t"+tel+"\t"+orderId);
		JSONArray json = JSONArray.fromObject(orderId);
		Object[] orderId1 = new Object[json.size()];
		if (json.size()>0) {
			for (int i = 0; i < json.size(); i++) {
				orderId1[i]= json.get(i);
			}
		}
		OrderInfo orderInfo = new OrderInfo(Double.valueOf(t_total), pay_way, address, name, tel, orderId1);
		ProductDao productDao = new ProductDao();
		int id = productDao.addOrderInfo(orderInfo);
		int s = 0;
		if (id>0) {
			for (int i = 0; i < orderId1.length; i++) {
				s+=productDao.updateInfoId(Double.valueOf((String)orderId1[i]), id);
			}
		}
		
		if (s==orderId1.length) {
			return "1";
		}else {
//			productDao.delOrderInfoById(id);
			return "0";
		}
	}
}
