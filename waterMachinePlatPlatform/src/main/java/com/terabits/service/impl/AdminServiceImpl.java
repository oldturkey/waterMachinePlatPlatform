package com.terabits.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.terabits.dao.AdminDAO;
import com.terabits.meta.po.AdminPO;
import com.terabits.meta.vo.AuthorityVO;
import com.terabits.service.AdminService;
import com.terabits.utils.JWT;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Service("adminService")
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminDAO adminDAO;

    public JSONObject login(String name, String password) {
        AdminPO adminPO = adminDAO.selectAdmin(name);
        if (adminPO != null && adminPO.getPassword().equals(password)) {
            String token = JWT.sign(adminPO, 48 * 1800L * 1000L);   //签发半小时的token
            List<AuthorityVO> authorityVOS = adminDAO.selectAdminAuthority(adminPO.getType());
            JSONArray authorityJSON = JSONArray.fromObject(authorityVOS);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("status", 1);
            jsonObject.put("token", token);
            jsonObject.put("type", adminPO.getType());
            jsonObject.put("authority", authorityJSON);
            return jsonObject;
        } else {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("status", 0);
            return jsonObject;
        }
    }

    public boolean authConfirm(int type, String url) {
        if (adminDAO.selectAuthorityByTypeAndUrl(type, url) == 1) {
            return true;
        }
        return false;
    }

}
