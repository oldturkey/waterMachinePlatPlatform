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
public class AccountController {
    @Autowired
    private AdminDAO adminDAO;
    @Autowired
    private AdminService adminService;

    //*************************************��ѯ�����˻�������Ϣ**************************************************************
    @RequestMapping(value = "/account/info", method = RequestMethod.GET)
    public @ResponseBody
    JSONObject getAccount(HttpServletRequest request,
                          HttpServletResponse response,
                          @RequestHeader("Authorization") String token
    ) throws Exception {
        System.out.println("***********************" + token);
        JSONObject jsonObject = new JSONObject();
        AdminPO adminPO = JWT.unsign(token, AdminPO.class);
        System.out.print("********************************************************");
        System.out.print(adminPO);
        System.out.print("*********************************************************");
        if (adminPO == null)
            jsonObject.put("status", 0);
        //��������Ա���Բ�ѯ�����û��Ļ�����Ϣ�͹���ĵ��
        if (adminPO.getType() == 1) {
            jsonObject.put("status", 1);
            jsonObject.put("info", adminService.getAllAccount());
        } else {
            //��ͨ����Աֻ�ܲ鿴�Լ��Ļ�����Ϣ����ص��
            jsonObject.put("status", 1);
            jsonObject.put("info", adminService.getSingleAcount(adminPO));
        }
        return jsonObject;
    }


    //************************************�޸�����***************************************************************
    @RequestMapping(value = "/account/password/change", method = RequestMethod.POST)
    public @ResponseBody
    JSONObject changePassword(HttpServletRequest request,
                              HttpServletResponse response,
                              @RequestHeader("Authorization") String token,
                              @RequestParam("account") String account,
                              @RequestParam("password") String password,
                              @RequestParam("changePassword") String changePassword
    ) throws Exception {
        JSONObject jsonObject = new JSONObject();
        AdminPO adminPO = JWT.unsign(token, AdminPO.class);
        System.out.print("********************************************************");
        System.out.print(adminPO);
        System.out.print("*********************************************************");
        if (adminPO == null) {
            jsonObject.put("status", 0);
            return jsonObject;
        }

        if (!adminPO.getName().equals(account)) {
            jsonObject.put("status", 2);
            return jsonObject;
        }
        if (!adminPO.getPassword().equals(password)) {
            jsonObject.put("status", 3);
            return jsonObject;
        }
        adminPO.setPassword(changePassword);
        adminDAO.updateByAdmin(adminPO);
        jsonObject.put("status", 1);
        return jsonObject;
    }


    //************************************�������û�***************************************************************
    @RequestMapping(value = "/account/create", method = RequestMethod.POST)
    public @ResponseBody
    JSONObject createAcount(HttpServletRequest request,
                            HttpServletResponse response,
                            @RequestHeader("Authorization") String token,
                            @RequestParam("account") String account,
                            @RequestParam("password") String password,
                            @RequestParam("email") String email,
                            @RequestParam(value = "displayid[]") String[] displayid,
                            @RequestParam("type") String type
    ) throws Exception {
        /*****************************
         * ��֤token*
         */
        JSONObject jsonObject = new JSONObject();
        AdminPO adminPO = JWT.unsign(token, AdminPO.class);
        System.out.print("********************************************************");
        System.out.print(adminPO);
        System.out.print("*********************************************************");
        if (adminPO == null) {
            jsonObject.put("status", 0);
            return jsonObject;
        }
        /****************************
         * �жϹ���Ա�Ƿ�ӵ�д����û�Ȩ��*
         */
        if (adminPO.getType() != 1) {
            jsonObject.put("status", 2);
            return jsonObject;
        }
        /****************************
         * �������û���������terminalAdmin���*
         */
        AdminPO newAdminPO = new AdminPO();
        newAdminPO.setName(account);
        newAdminPO.setPassword(password);
        newAdminPO.setEmail(email);
        newAdminPO.setType(Integer.parseInt(type));
        int result = adminDAO.insertAdminPO(newAdminPO);
        for (String device : displayid) {
            TerminalAdminPO terminalAdminPO = new TerminalAdminPO();
            terminalAdminPO.setAdminName(account);
            terminalAdminPO.setDisplayid(device);
            adminDAO.insertTerminalAdmin(terminalAdminPO);
        }
        jsonObject.put("status", 1);
        return jsonObject;
    }
}
