package com.terabits.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.terabits.meta.po.AdminPO;
import com.terabits.service.AdminService;
import com.terabits.service.DeviceService;
import com.terabits.utils.JWT;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
public class MainPageController {
	
	@Autowired
	private AdminService adminService;
	@Autowired
	private DeviceService deviceService;
	
	@RequestMapping(value = "/home/device/info",method = RequestMethod.GET)
    public @ResponseBody
    JSONObject deviceInfo(@RequestParam(value = "Authorization") String clientToken, 
    		HttpServletResponse response) throws Exception {
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
		JSONObject jsonObject = deviceService.getDeviceInfo(adminPO.getName(), adminPO.getType());
		jsonObject.put("status", 1);
		return jsonObject;
    }
	
	@RequestMapping(value = "/home/delivery/hour-record",method = RequestMethod.GET)
    public @ResponseBody
    JSONObject supplyHourRecord(@RequestParam(value = "Authorization") String clientToken, 
    		HttpServletResponse response) throws Exception {
		AdminPO adminPO = JWT.unsign(clientToken, AdminPO.class);
		if (adminPO == null) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("status", 0);
			return jsonObject;
		}
		if (!adminService.authConfirm(adminPO.getType(), "/home/delivery/hour-record")) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("status", 0);
			return jsonObject;
		}
		//获取系统时间
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar calendar = Calendar.getInstance();
		String endTime = df.format(calendar.getTime());
		calendar.add(Calendar.HOUR, -1);
		String beginTime = df.format(calendar.getTime());
		JSONObject jsonObject = new JSONObject();
		JSONArray supply = deviceService.getDeviceSupplyRecord(adminPO.getName(), adminPO.getType(), null, null, null, beginTime, endTime);
		jsonObject.put("status", 1);
		jsonObject.put("record", supply);
		return jsonObject;
    }
	
	@RequestMapping(value = "/home/device/hour-alarm",method = RequestMethod.GET)
    public @ResponseBody
    JSONObject alarmRecord(@RequestParam(value = "Authorization") String clientToken, 
    		HttpServletResponse response) throws Exception {
		AdminPO adminPO = JWT.unsign(clientToken, AdminPO.class);
		if (adminPO == null) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("status", 0);
			return jsonObject;
		}
		if (!adminService.authConfirm(adminPO.getType(), "/home/device/hour-alarm")) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("status", 0);
			return jsonObject;
		}
		//获取系统时间
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar calendar = Calendar.getInstance();
		String endTime = df.format(calendar.getTime());
		calendar.add(Calendar.HOUR, -1);
		String beginTime = df.format(calendar.getTime());
		JSONObject jsonObject = new JSONObject();
		JSONArray alarm = deviceService.getDeviceOfflineAlarm(adminPO.getName(), adminPO.getType(), null, beginTime, endTime);
		jsonObject.put("status", 1);
		jsonObject.put("alarm", alarm);
		return jsonObject;
    }
	
}
