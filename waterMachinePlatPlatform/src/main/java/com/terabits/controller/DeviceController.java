package com.terabits.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.terabits.service.DeviceService;

import net.sf.json.JSONObject;

@Controller
public class DeviceController {
	@Autowired
	private DeviceService deviceService;
	
	@RequestMapping(value = "/test",method = RequestMethod.POST)
    public @ResponseBody
    JSONObject login(@RequestParam(value = "name") String name,
                        @RequestParam(value = "password") String password) throws Exception{
		System.out.println(deviceService.getDisplayIdByAdmin(name, 5));
        return null;
    }
}
