package com.terabits.controller;

import com.terabits.meta.bo.TimeSpanBO;
import com.terabits.meta.po.AdminPO;
import com.terabits.service.AdminService;
import com.terabits.service.LogService;
import com.terabits.utils.JWT;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/log")
public class LogController {

    @Autowired
    private LogService logService;
    @Autowired
    private AdminService adminService;

    public final static int LOGIN_LOG = 1;
    public final static int CREATE_LOG = 2;
    public final static int DEVICE_OPERATION_LOG = 3;
    public final static int MONEY_GIVEN_LOG = 4;
    public final static int DEBUG_LOG = 5;
    public final static int FEEDBACK_SOLVE_LOG = 6;

    @RequestMapping(value = "/account/login", method = RequestMethod.GET)
    public @ResponseBody
    JSONObject login_log(@RequestHeader("Authorization") String clientToken,
                         @RequestParam(value = "beginTime") String beginTime,
                         @RequestParam(value = "endTime") String endTime) throws Exception {
        AdminPO adminPO = JWT.unsign(clientToken, AdminPO.class);
        if (adminPO == null) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("status", 0);
            return jsonObject;
        }
        if (!adminService.authConfirm(adminPO.getType(), "/log/account/login")) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("status", 0);
            return jsonObject;
        }

        JSONArray info_raw = JSONArray.fromObject(logService.selectLogByTypeAndTime(LOGIN_LOG,new TimeSpanBO(beginTime,endTime)));
        JSONArray info = new JSONArray();
        for(int i = 0; i < info_raw.size(); i++) {
            JSONObject each = info_raw.getJSONObject(i);
            each.put("time",each.get("gmtCreate"));
            each.remove("id");
            each.remove("gmtCreate");
            each.remove("gmtModified");
            each.remove("result");
            info.add(each);
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("status",1);
        jsonObject.put("info",info);
        return jsonObject;
    }

    @RequestMapping(value = "/account/create", method = RequestMethod.GET)
    public @ResponseBody
    JSONObject create_log(@RequestHeader("Authorization") String clientToken,
                         @RequestParam(value = "beginTime") String beginTime,
                         @RequestParam(value = "endTime") String endTime) throws Exception {

        AdminPO adminPO = JWT.unsign(clientToken, AdminPO.class);
        if (adminPO == null) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("status", 0);
            return jsonObject;
        }
        if (!adminService.authConfirm(adminPO.getType(), "/log/account/create")) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("status", 0);
            return jsonObject;
        }

        JSONArray info_raw = JSONArray.fromObject(logService.selectLogByTypeAndTime(CREATE_LOG,new TimeSpanBO(beginTime,endTime)));
        JSONArray info = new JSONArray();
        for(int i = 0; i < info_raw.size(); i++) {
            JSONObject each = info_raw.getJSONObject(i);
            each.put("time",each.get("gmtCreate"));
            each.remove("id");
            each.remove("gmtCreate");
            each.remove("gmtModified");
            each.remove("result");
            info.add(each);
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("status",1);
        jsonObject.put("info",info);
        return jsonObject;
    }

    @RequestMapping(value = "/account/add-delete", method = RequestMethod.GET)
    public @ResponseBody
    JSONObject add_delete_log(@RequestHeader("Authorization") String clientToken,
                          @RequestParam(value = "beginTime") String beginTime,
                          @RequestParam(value = "endTime") String endTime) throws Exception {


        AdminPO adminPO = JWT.unsign(clientToken, AdminPO.class);
        if (adminPO == null) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("status", 0);
            return jsonObject;
        }
        if (!adminService.authConfirm(adminPO.getType(), "/log/account/add-delete")) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("status", 0);
            return jsonObject;
        }

        JSONArray info_raw = JSONArray.fromObject(logService.selectLogByTypeAndTime(DEVICE_OPERATION_LOG,new TimeSpanBO(beginTime,endTime)));
        JSONArray info = new JSONArray();
        for(int i = 0; i < info_raw.size(); i++) {
            JSONObject each = info_raw.getJSONObject(i);
            each.put("time",each.get("gmtCreate"));
            each.remove("id");
            each.remove("gmtCreate");
            each.remove("gmtModified");
            each.remove("result");
            info.add(each);
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("status",1);
        jsonObject.put("info",info);
        return jsonObject;
    }

    @RequestMapping(value = "/account/present-provide", method = RequestMethod.GET)
    public @ResponseBody
    JSONObject present_provide_log(@RequestHeader("Authorization") String clientToken,
                          @RequestParam(value = "beginTime") String beginTime,
                          @RequestParam(value = "endTime") String endTime) throws Exception {

        AdminPO adminPO = JWT.unsign(clientToken, AdminPO.class);
        if (adminPO == null) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("status", 0);
            return jsonObject;
        }
        if (!adminService.authConfirm(adminPO.getType(), "/log/account/present-provide")) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("status", 0);
            return jsonObject;
        }

        JSONArray info_raw = JSONArray.fromObject(logService.selectLogByTypeAndTime(MONEY_GIVEN_LOG,new TimeSpanBO(beginTime,endTime)));
        JSONArray info = new JSONArray();
        for(int i = 0; i < info_raw.size(); i++) {
            JSONObject each = info_raw.getJSONObject(i);
            each.put("time",each.get("gmtCreate"));
            each.remove("id");
            each.remove("gmtCreate");
            each.remove("gmtModified");
            each.remove("result");
            info.add(each);
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("status",1);
        jsonObject.put("info",info);
        return jsonObject;
    }

    @RequestMapping(value = "/account/debug", method = RequestMethod.GET)
    public @ResponseBody
    JSONObject debug_log(@RequestHeader("Authorization") String clientToken,
                                   @RequestParam(value = "beginTime") String beginTime,
                                   @RequestParam(value = "endTime") String endTime) throws Exception {
        AdminPO adminPO = JWT.unsign(clientToken, AdminPO.class);
        if (adminPO == null) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("status", 0);
            return jsonObject;
        }
        if (!adminService.authConfirm(adminPO.getType(), "/log/account/debug")) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("status", 0);
            return jsonObject;
        }
        JSONArray info_raw = JSONArray.fromObject(logService.selectLogByTypeAndTime(DEBUG_LOG,new TimeSpanBO(beginTime,endTime)));
        JSONArray info = new JSONArray();
        for(int i = 0; i < info_raw.size(); i++) {
            JSONObject each = info_raw.getJSONObject(i);
            each.put("time",each.get("gmtCreate"));
            each.remove("id");
            each.remove("gmtCreate");
            each.remove("gmtModified");
            each.remove("result");
            info.add(each);
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("status",1);
        jsonObject.put("info",info);
        return jsonObject;
    }

    @RequestMapping(value = "/account/feedback", method = RequestMethod.GET)
    public @ResponseBody
    JSONObject feedback_log(@RequestHeader("Authorization") String clientToken,
                         @RequestParam(value = "beginTime") String beginTime,
                         @RequestParam(value = "endTime") String endTime) throws Exception {

        AdminPO adminPO = JWT.unsign(clientToken, AdminPO.class);
        if (adminPO == null) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("status", 0);
            return jsonObject;
        }
        if (!adminService.authConfirm(adminPO.getType(), "/log/account/feedback")) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("status", 0);
            return jsonObject;
        }

        JSONArray info_raw = JSONArray.fromObject(logService.selectLogByTypeAndTime(FEEDBACK_SOLVE_LOG,new TimeSpanBO(beginTime,endTime)));
        JSONArray info = new JSONArray();
        for(int i = 0; i < info_raw.size(); i++) {
            JSONObject each = info_raw.getJSONObject(i);
            each.put("time",each.get("gmtCreate"));
            each.remove("id");
            each.remove("gmtCreate");
            each.remove("gmtModified");
            each.remove("result");
            info.add(each);
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("status",1);
        jsonObject.put("info",info);
        return jsonObject;
    }
}