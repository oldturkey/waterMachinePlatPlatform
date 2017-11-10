package com.terabits.controller;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.microsoft.azure.sdk.iot.service.transport.http.HttpResponse;
import com.terabits.constant.Constant;

import net.sf.json.JSONObject;


import java.sql.Timestamp;
import java.util.Properties;
import java.util.UUID;

@Controller
public class Test {
	@RequestMapping(value="/testBase",method = RequestMethod.GET)
	public void testBase(HttpServletResponse response) throws Exception
	{
		JSONObject jsonObject =new JSONObject();
		jsonObject.put("name", "xu");
		jsonObject.put("password", "123");
		System.out.print("***********************");
		System.out.println(jsonObject);
		response.getWriter().println(jsonObject);
	}
	
	@RequestMapping(value="/sendMailSimple",method=RequestMethod.POST)
	public void sendMailSimple(HttpServletRequest request,HttpServletResponse response)throws Exception{

		ApplicationContext context = new ClassPathXmlApplicationContext( "Mail.xml");
        JavaMailSenderImpl sender = (JavaMailSenderImpl)context.getBean("mailSender");
        
        //�����ʼ�
        MimeMessage message=sender.createMimeMessage();
        try {
            //ʹ��MimeMessageHelper����Mime�����ʼ�
            MimeMessageHelper helper= new MimeMessageHelper(message,true);
            helper.setFrom(Constant.EmailFrom);
            helper.setTo(Constant.EmailTo);
            //����
            message.setSubject("Spring MVC");
            
            
            //�ڶ�������true������Ϣ������multipart����
            helper.setText("<a href=\"http://www.baidu.com\">���</a>",true);
            sender.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException("�ʼ�����ʧ��");
        }
	}
	
	//*************************163������Գɹ�*********************************
	@RequestMapping(value="/sendMail",method=RequestMethod.POST)
	public void sendMail(HttpServletRequest request,HttpServletResponse response)throws Exception{
		
		
		ApplicationContext context = new ClassPathXmlApplicationContext( "Mail.xml");
        JavaMailSenderImpl sender = (JavaMailSenderImpl)context.getBean("mailSender");
        
        //�����ʼ�
        MimeMessage message=sender.createMimeMessage();
        try {
        	
            //ʹ��MimeMessageHelper����Mime�����ʼ�
            MimeMessageHelper helper= new MimeMessageHelper(message,true);
            helper.setFrom("18860902309@163.com");
            helper.setTo("21632104@zju.edu.cn");
            message.setSubject("�ǻ�������ˮƽ̨�����һ�");
            //�ڶ�������true������Ϣ������multipart����
            helper.setText("<a href=\"http://www.baidu.com\">���</a>",true);
            sender.send(message);
            
        } catch (MessagingException e) {
            throw new RuntimeException("�ʼ�����ʧ��");
        }
	}
	//**************************QQ������Գɹ�*****************************************
	@RequestMapping(value="/testQQ",method=RequestMethod.POST)
	public void testQQ(@RequestParam(value="name") String name,
					 @RequestParam(value="email") String email)throws Exception{
		
		JavaMailSenderImpl sender = new JavaMailSenderImpl();

        sender.setHost("smtp.qq.com");
        sender.setPort(465);
        sender.setUsername("1255479000@qq.com");
        sender.setPassword("nboobqabiotybafb"); // ����Ҫ�������룬�������¼���������

        Properties pro = System.getProperties(); // �������ȱһ����
        pro.put("mail.smtp.auth", "true");
        pro.put("mail.smtp.ssl.enable", "true");
        pro.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

        sender.setJavaMailProperties(pro);

        MimeMessage message = sender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom("1255479000@qq.com"); // ������ 
            helper.setTo(email); // �ռ���  
            helper.setSubject("Title"); // ����
            helper.setText("Content"); // ����
            sender.send(message);
            System.out.println("������ϣ�");
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
	
	//*********************************��Ѷ��ҵ���䣬���Գɹ�*********************************
	@RequestMapping(value="/testTenxun",method=RequestMethod.POST)
	public void testTenxun(@RequestParam(value="name") String name,
					 @RequestParam(value="email") String email)throws Exception{
		
		JavaMailSenderImpl sender = new JavaMailSenderImpl();

        sender.setHost("smtp.exmail.qq.com");
        sender.setPort(465);
        sender.setUsername("support@terabits.cn");
        sender.setPassword("Tb12345678"); // ����Ҫ�������룬�������¼���������

        Properties pro = System.getProperties(); // �������ȱһ����
        pro.put("mail.smtp.auth", "true");
        pro.put("mail.smtp.ssl.enable", "true");
        pro.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

        sender.setJavaMailProperties(pro);

        MimeMessage message = sender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom("support@terabits.cn"); // ������ 
            helper.setTo(email); // �ռ���  
            helper.setSubject("Title"); // ����
            helper.setText("Content"); // ����
            sender.send(message);
            System.out.println("������ϣ�");
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
	
}
