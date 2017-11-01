package com.terabits.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.terabits.meta.po.AdminPO;
import com.terabits.service.AdminService;
import com.terabits.utils.JWT;

import net.sf.json.JSONObject;

@Controller
public class MainPageController {
	
	@Autowired
	private AdminService adminService;
	
	@RequestMapping(value = "/home/device/info",method = RequestMethod.GET)
    public @ResponseBody
    JSONObject login(@RequestParam(value = "Authorization") String clientToken,
                        HttpServletResponse response)throws Exception{
		AdminPO adminPO = JWT.unsign(clientToken, AdminPO.class);
		if (adminPO == null) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("status", 0);
			return jsonObject;
		}
		if (!adminService.authConfirm(adminPO.getType(), "/home/device/info")) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("status", 0);
			return jsonObject;
		}
		return null;
    }
}
