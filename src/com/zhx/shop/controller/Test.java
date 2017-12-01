package com.zhx.shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/test")
public class Test {
	
	@RequestMapping("/search.do")
	@ResponseBody
	public String search(String content) {
		
		if (content.equals("a")) {
			System.out.println(1);
			String[] res = {"a","ab","abc"};
			JSONObject json = new JSONObject();
			json.put("res", res);
			return json.toString();
		}
		return "";
	}
}
