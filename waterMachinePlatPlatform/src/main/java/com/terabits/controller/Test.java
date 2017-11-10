package com.terabits.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import net.sf.json.JSONObject;

@Controller
public class Test {
	@RequestMapping(value="/test",method = RequestMethod.GET)
	public void test() throws Exception
	{
		JSONObject jsonObject =new JSONObject();
		jsonObject.put("name", "xu");
		jsonObject.put("password", "123");
		System.out.println(jsonObject);
	}
	
}
