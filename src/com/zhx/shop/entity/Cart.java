package com.zhx.shop.entity;

public class Cart {
	
	private double orderId;
	private String pid;
	private String pimage;
	private String pname;
	private double shop_price;
	private int count;
	
	public Cart() {
		super();
	}
	
	public Cart(double orderId, String pid, String pimage, String pname, double shop_price, int count) {
		super();
		this.orderId = orderId;
		this.pid = pid;
		this.pimage = pimage;
		this.pname = pname;
		this.shop_price = shop_price;
		this.count = count;
	}

	public double getOrderId() {
		return orderId;
	}

	public void setOrderId(double orderId) {
		this.orderId = orderId;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getPimage() {
		return pimage;
	}

	public void setPimage(String pimage) {
		this.pimage = pimage;
	}

	public double getShop_price() {
		return shop_price;
	}

	public void setShop_price(double shop_price) {
		this.shop_price = shop_price;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
}
