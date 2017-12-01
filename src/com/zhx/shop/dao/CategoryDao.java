package com.zhx.shop.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ColumnListHandler;

import com.zhx.shop.util.JdbcUtil;

public class CategoryDao {
	
	public List<String> getCategory(){
		QueryRunner runner = new QueryRunner(JdbcUtil.getDataSource());
		String sql = "select * from category order by cid";
		List<String> list = null;
		try {
			list = runner.query(sql, new ColumnListHandler<String>("cname"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	public String getCategoryById(String id){
		QueryRunner runner = new QueryRunner(JdbcUtil.getDataSource());
		String sql = "select * from category where cid = ?";
		Object param = id;
		List<String> list = null;
		try {
			list = runner.query(sql, new ColumnListHandler<String>("cname"),param);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list.get(0);
	}
}
