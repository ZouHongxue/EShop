package com.zhx.shop.service;

import com.zhx.shop.dao.ProductDao;

public class CartService {
	
	public String addCart(String name,String pid,int count) {
		ProductDao productDao = new ProductDao();
		if (productDao.orderExist(name, pid)>0) {
			if (productDao.updateOrder(name, pid, count)>0) {
				return "添加订单成功";
			}else {
				return "添加订单失败";
			}
		}else {
			if (productDao.addOrder(name, pid, count)>0) {
				return "新增订单成功";
			}else {
				return "新增订单失败";
			}
		}
	}
	
	
	
}
