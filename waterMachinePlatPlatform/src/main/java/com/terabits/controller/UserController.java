package com.terabits.controller;


import com.terabits.meta.bo.TimeSpanBO;
import com.terabits.meta.bo.UserConsumeBO;
import com.terabits.meta.po.AdminPO;
import com.terabits.service.AdminService;
import com.terabits.service.UserService;
import com.terabits.utils.JWT;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private AdminService adminService;

    @RequestMapping(value = "/user/statistics", method = RequestMethod.GET)
    public @ResponseBody JSONObject userStatistic() throws Exception{
        JSONObject jsonObject = userService.getUserStaticticss();
        return jsonObject;
    }
    //*******************************************************************************************************************************
    /*给个人用户充值
    更新指定账户添加余额
    添加管理员操作记录
    更新今天的present
    更新历史recharge*/
    @RequestMapping(value = "/user/info/recharge", method = RequestMethod.POST)
    public @ResponseBody
    JSONObject rechargePerson(@RequestParam(value = "Authorization") String token,
                              @RequestParam(value = "money") double money,
                              @RequestParam(value = "phone") String[] phoneArray) throws Exception {

        return userService.userRecharge(token, phoneArray, money);

    }

    @RequestMapping(value = "/user/info/provide", method = RequestMethod.GET)
    public @ResponseBody
    JSONObject adminRechargeRecord(HttpServletRequest request) throws Exception{
        String phone = null;
        if(request.getParameter("phone") != null){
            phone = request.getParameter("phone");
        }
        JSONArray jsonArray = userService.adminRechargeRecord(phone);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("status", 1);
        jsonObject.put("info", jsonArray);
        return jsonObject;
    }

    @RequestMapping(value = "/user/info", method = RequestMethod.GET)
    public @ResponseBody
    JSONObject getUserInfo(HttpServletRequest request) throws Exception{
        String phone = null;
        if(request.getParameter("phone") != null){
            phone = request.getParameter("phone");
        }
        JSONArray jsonArray = userService.getUserInfo(phone);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("status", 1);
        jsonObject.put("info", jsonArray);
        return jsonObject;
    }

    @RequestMapping(value = "/query/payment", method = RequestMethod.GET)
    public @ResponseBody
    JSONObject selectDynamicConsumeRecord(@RequestParam(value = "Authorization") String clientToken,
                                         HttpServletRequest request) throws Exception {
        AdminPO adminPO = JWT.unsign(clientToken, AdminPO.class);
        if (adminPO == null) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("status", 0);
            return jsonObject;
        }
        if (!adminService.authConfirm(adminPO.getType(), "/query/device/supply")) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("status", 0);
            return jsonObject;
        }
        UserConsumeBO userConsumeBO = new UserConsumeBO();
        if(request.getParameter("displayid") != null){
            userConsumeBO.setDisplayid(request.getParameter("displayid"));
        }
        if(request.getParameter("location") != null){
            userConsumeBO.setLocation(request.getParameter("location"));
        }
        if(request.getParameter("phone") != null){
            userConsumeBO.setPhone(request.getParameter("phone"));
        }
        if(request.getParameter("beginTime") != null){
            userConsumeBO.setBeginTime(request.getParameter("beginTime"));
        }
        if(request.getParameter("endTime") != null){
            userConsumeBO.setEndTime(request.getParameter("endTime"));
        }
        JSONArray jsonArray = userService.selectDynamicConsumeRecord(userConsumeBO);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("status",1);
        jsonObject.put("info",jsonArray);
        return jsonObject;
    }

    @RequestMapping(value = "/query/recharge", method = RequestMethod.GET)
    public @ResponseBody JSONObject dynamicRechargeRecord(@RequestParam(value = "Authorization") String clientToken,
                                                          HttpServletRequest request) throws Exception {
        AdminPO adminPO = JWT.unsign(clientToken, AdminPO.class);
        if (adminPO == null) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("status", 0);
            return jsonObject;
        }
        if (!adminService.authConfirm(adminPO.getType(), "/query/device/supply")) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("status", 0);
            return jsonObject;
        }
        String phone = "";
        TimeSpanBO timeSpanBO = new TimeSpanBO();
        if(request.getParameter("phone") != null){
            phone = request.getParameter("phone");
        }
        if(request.getParameter("beginTime") != null){
            timeSpanBO.setBeginTime(request.getParameter("beginTime"));
        }
        if(request.getParameter("endTime") != null){
            timeSpanBO.setEndTime(request.getParameter("endTime"));
        }
        JSONArray jsonArray = userService.selectDynamicRechargeRecord(phone, timeSpanBO);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("status", 1);
        jsonObject.put("info", jsonArray);
        return jsonObject;
    }

}
