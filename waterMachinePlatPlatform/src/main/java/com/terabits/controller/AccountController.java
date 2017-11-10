package com.terabits.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.terabits.dao.AdminDAO;
import com.terabits.meta.po.AdminPO;
import com.terabits.meta.po.TerminalAdminPO;
import com.terabits.service.AdminService;
import com.terabits.utils.JWT;

import net.sf.json.JSONObject;
/*********
 * @version V1.0
 * @author Administrator
 */
@Controller
public class AccountController 
{
	@Autowired
	private AdminDAO adminDAO;
	@Autowired
	private AdminService adminService;
	//*************************************查询所有账户基本信息**************************************************************
	@RequestMapping(value = "/account/info",method = RequestMethod.GET)
	public @ResponseBody JSONObject getAccount (HttpServletRequest request,
												HttpServletResponse response,
												@RequestHeader("Authorization") String token
												)throws Exception{
		System.out.println("***********************"+token);
		JSONObject jsonObject=new JSONObject();
		AdminPO adminPO = JWT.unsign(token, AdminPO.class);
		System.out.print("********************************************************");
        System.out.print(adminPO);
		System.out.print("*********************************************************");
        if (adminPO == null) 
            jsonObject.put("status", 0);
        //超级管理员可以查询所有用户的基本信息和管理的电表
        if(adminPO.getType()==1){
        	jsonObject.put("status", 1);
        	jsonObject.put("info", adminService.getAllAccount());
        }else{
        	//普通管理员只能查看自己的基本信息和相关电表
        	jsonObject.put("status", 1);
        	jsonObject.put("info", adminService.getSingleAcount(adminPO));
        }
		return jsonObject;
	}
	
	
	//************************************修改密码***************************************************************
	@RequestMapping(value = "/account/password/change",method = RequestMethod.POST)
	public @ResponseBody JSONObject changePassword (HttpServletRequest request,
													HttpServletResponse response,
													@RequestHeader("Authorization") String token,
													@RequestParam("account") String account,
													@RequestParam("password") String password,
													@RequestParam("changePassword") String changePassword
													)throws Exception{
		JSONObject jsonObject=new JSONObject();
		AdminPO adminPO = JWT.unsign(token, AdminPO.class);
		System.out.print("********************************************************");
        System.out.print(adminPO);
		System.out.print("*********************************************************");
        if (adminPO == null) {
            jsonObject.put("status", 0);
            return jsonObject;
        }
        
        if(!adminPO.getName().equals(account)){
        	jsonObject.put("status", 2);
        	return jsonObject;
        }
        if(!adminPO.getPassword().equals(password)){
        	jsonObject.put("status", 3);
        	return jsonObject;
        }
        adminPO.setPassword(changePassword);
        adminDAO.updateByAdmin(adminPO);
        jsonObject.put("status", 1);
		return jsonObject;
	}
	

	//************************************创建新用户***************************************************************
	@RequestMapping(value = "/account/create",method = RequestMethod.POST)
	public @ResponseBody JSONObject createAcount (HttpServletRequest request,
												  HttpServletResponse response,
												  @RequestHeader("Authorization") String token,
												  @RequestParam("account") String account,
												  @RequestParam("password") String password,
												  @RequestParam("email") String email,
												  @RequestParam(value="displayid[]") String[] displayid,
												  @RequestParam("type") String type
												 )throws Exception{
		/*****************************
		 * 验证token*
		 */
		JSONObject jsonObject=new JSONObject();
		AdminPO adminPO = JWT.unsign(token, AdminPO.class);
		System.out.print("********************************************************");
        System.out.print(adminPO);
		System.out.print("*********************************************************");
        if (adminPO == null) {
            jsonObject.put("status", 0);
            return jsonObject;
        }
		/****************************
		 * 判断管理员是否拥有创建用户权限*
		 */
        if(adminPO.getType()!=1){
        	jsonObject.put("status", 2);
        	return jsonObject;
        }
		/****************************
		 * 创建新用户，并更新terminalAdmin表格*
		 */
		AdminPO newAdminPO=new AdminPO();
		newAdminPO.setName(account);
		newAdminPO.setPassword(password);
		newAdminPO.setEmail(email);
		newAdminPO.setType(Integer.parseInt(type));
		int result=adminDAO.insertAdminPO(newAdminPO);
		for (String device:displayid){
			TerminalAdminPO terminalAdminPO=new TerminalAdminPO();
			terminalAdminPO.setAdminName(account);
			terminalAdminPO.setDisplayid(device);
			adminDAO.insertTerminalAdmin(terminalAdminPO);
		}
		jsonObject.put("status", 1);
		return jsonObject;
	}
}
