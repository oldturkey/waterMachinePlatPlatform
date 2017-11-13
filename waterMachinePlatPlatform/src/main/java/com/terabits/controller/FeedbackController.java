package com.terabits.controller;

import com.terabits.meta.bo.TimeSpanBO;
import com.terabits.meta.po.AdminPO;
import com.terabits.service.AdminService;
import com.terabits.service.FeedbackService;
import com.terabits.utils.JWT;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/feedback")
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;
    @Autowired
    private AdminService adminService;

    @RequestMapping(value = "/query")
    /**
     * 根据时间以及手机号码获取反馈信息
     */
    public @ResponseBody
    JSONObject parameter(@RequestHeader("Authorization") String clientToken,
                         @RequestParam(value = "beginTime") String beginTime,
                         @RequestParam(value = "endTime") String endTime,
                         @RequestParam(value = "phone") String phone) throws Exception {

        AdminPO adminPO = JWT.unsign(clientToken, AdminPO.class);
        if (adminPO == null) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("status", 0);
            return jsonObject;
        }
        if (!adminService.authConfirm(adminPO.getType(), "/feedback/query")) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("status", 0);
            return jsonObject;
        }

        JSONArray info = JSONArray.fromObject(feedbackService.selectFeedbackByPhoneAndTime(phone, new TimeSpanBO(beginTime, endTime)));
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("status", 1);
        jsonObject.put("info", info);
        return jsonObject;
    }

    @RequestMapping(value = "/delete")
    /**
     * 根据反馈Id删除反馈
     */
    public @ResponseBody
    JSONObject parameter(@RequestHeader("Authorization") String clientToken,
                         @RequestParam(value = "id") int id) throws Exception {

        AdminPO adminPO = JWT.unsign(clientToken, AdminPO.class);
        if (adminPO == null) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("status", 0);
            return jsonObject;
        }
        if (!adminService.authConfirm(adminPO.getType(), "/feedback/delete")) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("status", 0);
            return jsonObject;
        }

        int result = feedbackService.deleteFeedbackById(id);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("status", 1);
        jsonObject.put("result", result);
        return jsonObject;
    }

    @RequestMapping(value = "/update")
    /**
     * 根据Id和前端传过来的状态码更新反馈状态
     */
    public @ResponseBody
    JSONObject parameter(@RequestHeader("Authorization") String clientToken,
                         @RequestParam(value = "id") int id,
                         @RequestParam(value = "status") int updateStatus) throws Exception {

        AdminPO adminPO = JWT.unsign(clientToken, AdminPO.class);
        if (adminPO == null) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("status", 0);
            return jsonObject;
        }
        if (!adminService.authConfirm(adminPO.getType(), "/feedback/update")) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("status", 0);
            return jsonObject;
        }

        feedbackService.updateFeedbackStatusById(updateStatus, id);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("status", 1);
        return jsonObject;
    }
}
