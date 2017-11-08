package com.terabits.interceptor;

import com.terabits.meta.po.AdminPO;
import com.terabits.service.AdminService;
import com.terabits.utils.JWT;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Administrator on 2017/11/7.
 */
public class LoginInterceptor implements HandlerInterceptor {

    @Autowired
    private AdminService adminService;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
        String token = request.getParameter("Authorization");
        AdminPO adminPO = JWT.unsign(token, AdminPO.class);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("status", 0);
        if (adminPO == null) {
            response.getWriter().print(jsonObject);
            return false;
        }
        if (!adminService.authConfirm(adminPO.getType(), request.getServletPath())) {
            response.getWriter().print(jsonObject);
            return false;
        }
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object arg2, Exception arg3)
            throws Exception {
        //System.out.println("LoginInterceptor:afterCompletion");
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object arg2, ModelAndView arg3)
            throws Exception {
        //System.out.println("LoginInterceptor:postHandle");
    }
}
