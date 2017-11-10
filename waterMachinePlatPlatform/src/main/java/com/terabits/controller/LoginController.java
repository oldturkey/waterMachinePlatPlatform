package com.terabits.controller;

import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.mail.search.StringTerm;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.terabits.constant.Constant;
import com.terabits.dao.AdminDAO;
import com.terabits.meta.po.AdminPO;
import com.terabits.service.AdminService;
import com.terabits.utils.JWT;

import net.sf.json.JSONObject;
import net.sf.json.processors.JsDateJsonBeanProcessor;

@Controller
//@RestController=@Controller+@ResponseBody
public class LoginController {
	
	@Autowired
	private AdminService adminService;
	@Autowired
	private AdminDAO adminDAO;

	//********************************登录******************************************************
	//@RequestMapping(value="/login",method=RequestMethod.POST) 两种方式实现
	//@GetMapping(value="/login") get对应的请求
	@PostMapping(value = "/login")
	@ResponseBody//表示返回类型，如果不用，return JSONObject将无法执行
    public JSONObject login(@RequestParam(value = "name") String name,
                     @RequestParam(value = "password") String password) throws Exception{
		System.out.print("********************");
		System.out.println(name+"    "+password);
        return adminService.login(name, password);
    }
	
	
	//*************************************************************************************
	/********
	 * 验证用户名和邮箱是否匹配,如果匹配，向用户发送邮件。邮件中有一个超链接，指向“修改密码”页面
	 * 在修改密码页面，首先判断链接是否有效。当链接被点过，或者超过30分钟即失效，用session来实现上述过程
	 * @param name
	 * @param email
	 */
	@RequestMapping(value="/password/found/check/name",method=RequestMethod.POST)
	public @ResponseBody JSONObject checkNameAndEmail(@RequestParam(value="name") String name,
													  @RequestParam(value="email") String email,
													  HttpServletRequest request,
													  HttpServletResponse response
													  )throws Exception{
		//int status= adminService.checkEmailAndName(name, email);
		AdminPO adminPO=adminDAO.selectAdmin(name);
		int status;
		if(adminPO==null){
			status=0;
		}else {
			String tmpEmail=adminPO.getEmail();
			System.out.println(tmpEmail+"   "+email);
			if(tmpEmail.equals(email))
				status=1;
			else
				status=2;
		}
		//************************发邮件**********************************
		if(status==1){
			HttpSession session = request.getSession(); 
			session.setAttribute("status", "on");
			sendMail(email,adminPO);
		}
		JSONObject jsonObject=new JSONObject();
		jsonObject.put("status", status);
		return jsonObject;
	}
	
	//**********************发送邮件****************************************************************
	void sendMail(String email,AdminPO adminPO){
		JavaMailSenderImpl sender = new JavaMailSenderImpl();

		//**********************设置基本参数**************************
        sender.setHost("smtp.exmail.qq.com");
        sender.setPort(465);
        sender.setUsername("support@terabits.cn");
        sender.setPassword("Tb12345678"); // 这里要用邀请码，不是你登录邮箱的密码
        Properties pro = System.getProperties(); // 下面各项缺一不可
        pro.put("mail.smtp.auth", "true");
        pro.put("mail.smtp.ssl.enable", "true");
        pro.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        sender.setJavaMailProperties(pro);

        
        MimeMessage message = sender.createMimeMessage();
        try {
        	//**********************设置发送邮件的内容***************
        	/********
        	 * "点我重新设置密码"是一个超链接，指向修改密码页面。页面的URL也是此次设计的一个难点*
        	 */
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            helper.setFrom("support@terabits.cn"); // 发送人 
            helper.setTo(email); // 收件人  
            helper.setSubject("智慧物联饮水平台密码找回"); // 标题
            
            
            String token = JWT.sign(adminPO, 48 * 1800L * 1000L); 
            String resetPassHref = Constant.BasePath+"token="+token;
		    String emailContent = "请勿回复本邮件.点击下面的链接,重设密码<br/><a href="+resetPassHref +" target='_BLANK'>点击我重新设置密码</a>" +
		          "<br/>tips:本邮件超过30分钟,链接将会失效，需要重新申请'找回密码'";
		    System.out.println(resetPassHref);
		    System.out.println("  ");
		    System.out.println(emailContent);
		    
	        helper.setText(emailContent,true);
            sender.send(message);
            System.out.println("发送完毕！");
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
		
	}
	
	@RequestMapping(value="/password/found/check/time",method=RequestMethod.POST)
	public @ResponseBody JSONObject checkTime(HttpServletRequest request)throws Exception{
		int state;
		HttpSession session = request.getSession(); 
		if(session.getAttribute("status")==null)
			state=0;
		else
			state=1;
		
		JSONObject jsonObject=new JSONObject();
		jsonObject.put("status", state);
		return jsonObject;
	}
	
	@RequestMapping(value="/password/found/newpass",method=RequestMethod.POST)
	public @ResponseBody JSONObject newPass(@RequestParam("token") String token,
											@RequestParam("password") String password,
											HttpServletRequest request)throws Exception{
		AdminPO adminPO = JWT.unsign(token, AdminPO.class);
		adminPO.setPassword(password);
		int result=adminDAO.updateByAdmin(adminPO);
		
		HttpSession session = request.getSession(); 
		session.removeAttribute("status"); 
		JSONObject jsonObject=new JSONObject();
		jsonObject.put("status", result);
		return jsonObject;
	}
}
