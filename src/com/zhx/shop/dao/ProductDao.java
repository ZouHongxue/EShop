package com.zhx.shop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ColumnListHandler;

import com.zhx.shop.entity.Cart;
import com.zhx.shop.entity.OrderInfo;
import com.zhx.shop.entity.Product;
import com.zhx.shop.util.JdbcUtil;

public class ProductDao {

	/**
	 * 获取商品
	 * @param type  商品类型
	 * @return
	 */
	public List<Product> getProduct(String type){
		QueryRunner runner = new QueryRunner(JdbcUtil.getDataSource());
		String sql = "select * from product where 1=1";
		List<Object> params = new ArrayList<Object>();
		if ("hot".equals(type)&&!"".equals(type)) {
			sql+=" and is_hot = ?";
			params.add(1);
		}
		if ("new".equals(type)&&!"".equals(type)) {
			sql+=" order by pdate desc limit 9";
		}
		List<Product> list = null;
		if (params.size()>0) {
			Object[]p = new Object[params.size()];
			for (int i = 0; i < p.length; i++) {
				p[i] = params.get(i);
			}
			try {
				list = runner.query(sql, new BeanListHandler<Product>(Product.class),p);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
			try {
				list = runner.query(sql, new BeanListHandler<Product>(Product.class));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
	}
	
	
	public Product getProductById(String id){
		QueryRunner runner = new QueryRunner(JdbcUtil.getDataSource());
		String sql = "select * from product where pid = ?";
		Object param = id;
		Product product = null;
		try {
			product = runner.query(sql,new BeanHandler<Product>(Product.class),param);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return product;
	}
	
	public int addOrder(String name,String pid,int count){
		QueryRunner runner = new QueryRunner(JdbcUtil.getDataSource());
		String sql = "insert into orderlist (name,pid,count,is_clear) values(?,?,?,0)";
		Object[]params = {name,pid,count};
		int rs = 0;
		try {
			rs = runner.execute(sql, params);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}
	
	public int updateOrder(String name,String pid,int count){
		QueryRunner runner = new QueryRunner(JdbcUtil.getDataSource());
		String sql = "update orderlist set count = count +? where name=? and pid=?";
		Object[]params = {count,name,pid};
		int rs = 0;
		try {
			rs = runner.update(sql, params);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}
	
	public int orderExist(String name ,String pid){
		QueryRunner runner = new QueryRunner(JdbcUtil.getDataSource());
		String sql = "select * from orderlist where name=? and pid = ?";
		Object[]params = {name,pid};
		List<String> list = null;
		try {
			list = runner.query(sql,new ColumnListHandler<String>("pid"),params);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list.size();
	}
	
	public List<Cart> getCart(String name){
		QueryRunner runner = new QueryRunner(JdbcUtil.getDataSource());
		String sql = "SELECT o.orderId,o.pid,p.pimage,p.pname,p.shop_price,o.count FROM product as p,orderlist as o "
				+ "WHERE o.`name`=? and o.pid=p.pid and o.is_clear=0;";
		Object param = name;
		List<Cart> list = null;
		try {
			list = runner.query(sql, new BeanListHandler<Cart>(Cart.class),param);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	public int delOrder(String orderId){
		QueryRunner runner = new QueryRunner(JdbcUtil.getDataSource());
		String sql = "delete from orderlist where orderId = ?";
		Object param = orderId;
		int rs = 0;
		try {
			rs = runner.execute(sql, param);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}
	
	public int addOrderInfo(OrderInfo orderInfo){
		Connection connection = null;
		PreparedStatement prst = null;
		Statement statement = null;
		ResultSet rs = null;
		int id = 0;
		try {
			connection = JdbcUtil.getDataSource().getConnection();
			statement = connection.createStatement();
			prst = connection.prepareStatement("insert into orderinfo (address,name,tel,pay_way,t_total)"
					+ "VALUES(?,?,?,?,?);");
			prst.setString(1, orderInfo.getAddress());
			prst.setString(2, orderInfo.getName());
			prst.setString(3, orderInfo.getTel());
			prst.setString(4, orderInfo.getPay_way());
			prst.setDouble(5, orderInfo.getT_total());
			prst.execute();
			rs = statement.executeQuery("select @@identity");
			while (rs.next()) {
				id = rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return id;
	}
	
	public int updateInfoId(Double orderId,double infoId){
		QueryRunner runner = new QueryRunner(JdbcUtil.getDataSource());
		String sql= "update orderlist set infoId = ?,is_clear=1 where orderId = ?";
		Object[] params ={infoId,orderId};
		int rs = 0;
		try {
			rs = runner.execute(sql, params);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}
	
	public int delOrderInfoById(double infoId){
		QueryRunner runner = new QueryRunner(JdbcUtil.getDataSource());
		String sql = "delete from orderinfo where infoId = ?";
		Object param = infoId;
		int rs = 0;
		try {
			rs = runner.execute(sql, param);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}
}
