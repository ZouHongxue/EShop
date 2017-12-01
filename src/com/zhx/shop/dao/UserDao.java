package com.zhx.shop.dao;

import java.sql.SQLException;
import java.util.List;


import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.zhx.shop.entity.User;
import com.zhx.shop.util.JdbcUtil;

public class UserDao {
	
	public int userReg(User user){
		
		QueryRunner runner = new QueryRunner(JdbcUtil.getDataSource());
		String sql ="insert into user values(?,?,?,?,?,?)";
		Object[] params = {user.getName(),user.getPwd(),user.getEmail(),user.getNikyname(),
				user.getSex(),user.getBirthday()};
		int rs = 0; //操作结果
		try {
			rs = runner.update(sql, params);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}
	
	
	public boolean checkUser(String username,String pwd){
		QueryRunner runner = new QueryRunner(JdbcUtil.getDataSource());
		String sql ="select * from user where name = ? and pwd = ?";
		Object[] params = {username,pwd};
		List<User> list= null;
		try {
			list = runner.query(sql, new BeanListHandler<User>(User.class),params);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (list!=null && list.size()>0) {
			return true;
		}else {
			return false;
		}
		
	}
}
