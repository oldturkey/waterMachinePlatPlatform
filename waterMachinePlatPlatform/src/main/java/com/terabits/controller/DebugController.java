package com.terabits.controller;

import com.terabits.meta.po.AdminPO;
import com.terabits.service.AdminService;
import com.terabits.service.DebugService;
import com.terabits.utils.JWT;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/debug")
public class DebugController {

    @Autowired
    private DebugService debugService;
    @Autowired
    private AdminService adminService;

    @RequestMapping(value = "/device", method = RequestMethod.POST)
    public @ResponseBody
    JSONObject parameter(@RequestHeader("Authorization") String clientToken,
                         @RequestParam(value = "displayIdS") String displayIdS,
                         @RequestParam(value = "openTime") String openTime,
                         @RequestParam(value = "coldPulse") String coldPulse,
                         @RequestParam(value = "hotPulse") String hotPulse,
                         @RequestParam(value = "heartRate") String heartRate) throws Exception {

        AdminPO adminPO = JWT.unsign(clientToken, AdminPO.class);
        if (adminPO == null) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("status", 0);
            return jsonObject;
        }
        if (!adminService.authConfirm(adminPO.getType(), "/debug/device")) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("status", 0);
            return jsonObject;
        }

        int result = debugService.debugManipulate(displayIdS, openTime, coldPulse, hotPulse, heartRate);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("status", result);
        return jsonObject;
    }
}
