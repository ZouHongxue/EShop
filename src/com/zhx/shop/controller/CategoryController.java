package com.zhx.shop.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhx.shop.dao.CategoryDao;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/category")
public class CategoryController {
	
	@RequestMapping(value="/getCategory.do",produces="text/html;charset=utf-8")
	@ResponseBody
	public String getCategory(){
		CategoryDao categoryDao = new CategoryDao();
		List<String> list = categoryDao.getCategory();
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("category", list);
		return jsonObject.toString();
	}
	
	@RequestMapping(value="/getCategoryById.do",produces="text/html;charset=utf-8")
	@ResponseBody
	public String getCategoryById(String id){
		CategoryDao categoryDao = new CategoryDao();
		String rs = categoryDao.getCategoryById(id);
		return rs;
	}
}
