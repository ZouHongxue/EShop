package com.zhx.shop.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhx.shop.dao.UserDao;
import com.zhx.shop.entity.User;

@Controller
@RequestMapping("/user")
public class UserController {

	@RequestMapping("/tologin.do")
	public String toLogin(){
		return "login";
	}
	
	@RequestMapping("/toreg.do")
	public String toReg(){
		return "register";
	}
	
	@RequestMapping("/login.do")
	public String login(String username,String password,HttpServletRequest request){
		UserDao userDao = new UserDao();
		boolean rs = userDao.checkUser(username, password);
		HttpSession session = request.getSession();
		if (rs) {
			session.setAttribute("user", username);
			return "redirect:/jsp/index.jsp";
		}else {
			session.setAttribute("msg", "Failed");
			return "redirect:/jsp/login.jsp";
		}
	}
	
	@RequestMapping("/reg.do")
	@ResponseBody
	public String reg(String username,String password,String email,String name,
			String sex,String birthday){
		User user = new User(username, password, email, name, sex, birthday);
		UserDao userDao = new UserDao();
		int rs = userDao.userReg(user);
		return rs+"";
	}
	
	@RequestMapping("/logout.do")
	public String logout(HttpServletRequest request){
		HttpSession session = request.getSession();
		session.removeAttribute("user");
		return "redirect:/jsp/login.jsp";
	}
}
