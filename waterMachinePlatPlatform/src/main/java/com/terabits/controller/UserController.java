package com.terabits.controller;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class UserController {
    @RequestMapping(value = "/user/statistics", method = RequestMethod.POST)
    public void interceptorTest(HttpServletResponse response) throws Exception{
        System.out.println("in user statistics");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("user", "bihan");
        jsonObject.put("pass", "lovezhe");
        response.getWriter().print(jsonObject);
    }
}
