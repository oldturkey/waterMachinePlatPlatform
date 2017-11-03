package com.terabits.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.terabits.meta.bo.AdminTerminalBO;
import com.terabits.meta.po.AdminPO;
import com.terabits.meta.po.TerminalPO;
import com.terabits.service.AdminService;
import com.terabits.service.DeviceService;
import com.terabits.utils.JWT;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
public class DeviceController {
	
	@Autowired
	private AdminService adminService;
	@Autowired
	private DeviceService deviceService;
	
	@RequestMapping(value = "/device/statistics",method = RequestMethod.GET)
    public @ResponseBody
    JSONObject deviceStatistic(@RequestParam(value = "Authorization") String clientToken, 
    		HttpServletResponse response) throws Exception {
		AdminPO adminPO = JWT.unsign(clientToken, AdminPO.class);
		if (adminPO == null) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("status", 0);
			return jsonObject;
		}
		if (!adminService.authConfirm(adminPO.getType(), "/device/statistics")) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("status", 0);
			return jsonObject;
		}
		JSONObject jsonObject = deviceService.getDeviceStatictic(adminPO.getName(), adminPO.getType());
		return jsonObject;
    }
	
	@RequestMapping(value = "/device/supply",method = RequestMethod.GET)
    public @ResponseBody
    JSONObject deviceSupplyInfo(@RequestParam(value = "Authorization") String clientToken, 
    		HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String displayid = request.getParameter("displayid");
		String location = request.getParameter("location");
		
		AdminPO adminPO = JWT.unsign(clientToken, AdminPO.class);
		if (adminPO == null) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("status", 0);
			return jsonObject;
		}
		if (!adminService.authConfirm(adminPO.getType(), "/device/supply")) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("status", 0);
			return jsonObject;
		}
		JSONObject jsonObject = new JSONObject();
		JSONArray supplyInfo = deviceService.getDeviceSupplyInfo(adminPO.getName(), adminPO.getType(), displayid, location);
		jsonObject.put("status", 1);
		jsonObject.put("info", supplyInfo);
		return jsonObject;
    }
	
	@RequestMapping(value = "/device/add",method = RequestMethod.POST)
    public @ResponseBody
    JSONObject deviceAdd(@RequestParam(value = "Authorization") String clientToken,
    		@RequestParam(value = "imei") String imei, 
    		@RequestParam(value = "address") String address,
    		@RequestParam(value = "sim") String sim,
    		@RequestParam(value = "account") List<String> account,
    		HttpServletResponse response) throws Exception {
		AdminPO adminPO = JWT.unsign(clientToken, AdminPO.class);
		if (adminPO == null) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("status", 0);
			return jsonObject;
		}
		if (!adminService.authConfirm(adminPO.getType(), "/device/add")) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("status", 0);
			return jsonObject;
		}
		if (imei.length() != 15 || sim.length() != 15) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("status", 3);
			return jsonObject;
		}
		
		TerminalPO terminalPO = new TerminalPO();
		terminalPO.setImei(imei);
		terminalPO.setLocation(address);
		terminalPO.setSimid(sim);
		terminalPO.setState(10);
		Map<String, Object> params = new HashMap<String, Object>();
		List<AdminTerminalBO> adminTerminalBOs = new ArrayList<AdminTerminalBO>();
		for (String adminName : account) {
			AdminTerminalBO adminTerminalBO = new AdminTerminalBO(adminName, imei);
			adminTerminalBOs.add(adminTerminalBO);
		}
		params.put("list", adminTerminalBOs);
		int result = deviceService.addTerminal(terminalPO, params);
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("status", result);
		return jsonObject;
    }
	
	@RequestMapping(value = "/device/delete",method = RequestMethod.POST)
    public @ResponseBody
    JSONObject deviceDelete(@RequestParam(value = "Authorization") String clientToken,
    		@RequestParam(value = "displayid") String displayid, 
    		HttpServletResponse response) throws Exception {
		AdminPO adminPO = JWT.unsign(clientToken, AdminPO.class);
		if (adminPO == null) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("status", 0);
			return jsonObject;
		}
		if (!adminService.authConfirm(adminPO.getType(), "/device/delete")) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("status", 0);
			return jsonObject;
		}
		int result = deviceService.deleteTerminal(displayid);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("status", result);
		return jsonObject;
    }
	
	@RequestMapping(value = "/device/connect",method = RequestMethod.GET)
    public @ResponseBody
    JSONObject deviceConnect(@RequestParam(value = "Authorization") String clientToken,
    		HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String displayid = request.getParameter("displayid");
		String beginTime = request.getParameter("beginTime");
		String endTime = request.getParameter("endTime");
		
		AdminPO adminPO = JWT.unsign(clientToken, AdminPO.class);
		if (adminPO == null) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("status", 0);
			return jsonObject;
		}
		if (!adminService.authConfirm(adminPO.getType(), "/device/connect")) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("status", 0);
			return jsonObject;
		}
		JSONObject jsonObject = deviceService.getDeviceConnectInfo(adminPO.getName(), adminPO.getType(), displayid, beginTime, endTime);
		jsonObject.put("status", 1);
		return jsonObject;
    }
}
