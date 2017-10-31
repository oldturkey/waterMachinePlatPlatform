package com.terabits.controller;
//*********************测试RequestMapping，返回主页*********************************************
import com.terabits.meta.po.User.UserPO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;

@Controller
public class testcontroller
{
    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String index(HttpServletResponse response)throws Exception{
        //response.getWriter().print("hihao");
        return "index";
    }

}
