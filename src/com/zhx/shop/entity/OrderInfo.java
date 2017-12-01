package com.zhx.shop.entity;

public class OrderInfo {
	private double t_total;
	private String pay_way;
	private String address;
	private String name;
	private String tel;
	private Object[]orderId;
	
	public OrderInfo() {
		super();
	}

	public OrderInfo(double t_total, String pay_way, String address, String name, String tel, 
			Object[] orderId) {
		super();
		this.t_total = t_total;
		this.pay_way = pay_way;
		this.address = address;
		this.name = name;
		this.tel = tel;
		this.orderId = orderId;
	}

	public double getT_total() {
		return t_total;
	}

	public void setT_total(double t_total) {
		this.t_total = t_total;
	}

	public String getPay_way() {
		return pay_way;
	}

	public void setPay_way(String pay_way) {
		this.pay_way = pay_way;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public Object[] getOrderId() {
		return orderId;
	}

	public void setOrderId(Object[] orderId) {
		this.orderId = orderId;
	}
	
}
