package com.terabits.controller;

import com.terabits.constant.Constant;
import com.terabits.dao.AdminDAO;
import com.terabits.meta.po.AdminPO;
import com.terabits.utils.JWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.terabits.service.AdminService;

import net.sf.json.JSONObject;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Properties;

@Controller
public class LoginController {

    @Autowired
    private AdminService adminService;
    @Autowired
    private AdminDAO adminDAO;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public @ResponseBody
    JSONObject login(@RequestParam(value = "name") String name,
                     @RequestParam(value = "password") String password) throws Exception {
        return adminService.login(name, password);
    }

    //*************************************************************************************

    /********
     * ��֤�û����������Ƿ�ƥ��,���ƥ�䣬���û������ʼ����ʼ�����һ�������ӣ�ָ���޸����롱ҳ��
     * ���޸�����ҳ�棬�����ж������Ƿ���Ч�������ӱ���������߳���30���Ӽ�ʧЧ����session��ʵ����������
     * @param name
     * @param email
     */
    @RequestMapping(value = "/password/found/check/name", method = RequestMethod.POST)
    public @ResponseBody
    JSONObject checkNameAndEmail(@RequestParam(value = "name") String name,
                                 @RequestParam(value = "email") String email,
                                 HttpServletRequest request,
                                 HttpServletResponse response
    ) throws Exception {
        //int status= adminService.checkEmailAndName(name, email);
        AdminPO adminPO = adminDAO.selectAdmin(name);
        int status;
        if (adminPO == null) {
            status = 0;
        } else {
            String tmpEmail = adminPO.getEmail();
            System.out.println(tmpEmail + "   " + email);
            if (tmpEmail.equals(email))
                status = 1;
            else
                status = 2;
        }
        //************************���ʼ�**********************************
        if (status == 1) {
            HttpSession session = request.getSession();
            session.setAttribute("status", "on");
            sendMail(email, adminPO);
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("status", status);
        return jsonObject;
    }

    //**********************�����ʼ�****************************************************************
    void sendMail(String email, AdminPO adminPO) {
        JavaMailSenderImpl sender = new JavaMailSenderImpl();

        //**********************���û�������**************************
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
            //**********************���÷����ʼ�������***************
            /********
             * "����������������"��һ�������ӣ�ָ���޸�����ҳ�档ҳ���URLҲ�Ǵ˴���Ƶ�һ���ѵ�*
             */
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            helper.setFrom("support@terabits.cn"); // ������
            helper.setTo(email); // �ռ���
            helper.setSubject("�ǻ�������ˮƽ̨�����һ�"); // ����


            String token = JWT.sign(adminPO, 48 * 1800L * 1000L);
            String resetPassHref = Constant.BasePath + "token=" + token;
            String emailContent = "����ظ����ʼ�.������������,��������<br/><a href=" + resetPassHref + " target='_BLANK'>�����������������</a>" +
                    "<br/>tips:���ʼ�����30����,���ӽ���ʧЧ����Ҫ��������'�һ�����'";
            System.out.println(resetPassHref);
            System.out.println("  ");
            System.out.println(emailContent);

            helper.setText(emailContent, true);
            sender.send(message);
            System.out.println("������ϣ�");
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @RequestMapping(value = "/password/found/check/time", method = RequestMethod.POST)
    public @ResponseBody
    JSONObject checkTime(HttpServletRequest request) throws Exception {
        int state;
        HttpSession session = request.getSession();
        if (session.getAttribute("status") == null)
            state = 0;
        else
            state = 1;

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("status", state);
        return jsonObject;
    }

    @RequestMapping(value = "/password/found/newpass", method = RequestMethod.POST)
    public @ResponseBody
    JSONObject newPass(@RequestParam("token") String token,
                       @RequestParam("password") String password,
                       HttpServletRequest request) throws Exception {
        AdminPO adminPO = JWT.unsign(token, AdminPO.class);
        adminPO.setPassword(password);
        int result = adminDAO.updateByAdmin(adminPO);

        HttpSession session = request.getSession();
        session.removeAttribute("status");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("status", result);
        return jsonObject;
    }
}
