package com.terabits.controller;

import com.terabits.meta.po.Admin.AdminRecordPO;
import com.terabits.meta.po.Statistic.AuxcalPO;
import com.terabits.meta.po.Statistic.TotalPO;
import com.terabits.meta.po.User.UserPO;
import com.terabits.service.AdminService;
import com.terabits.service.StatisticService;
import com.terabits.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class rechargecontroller {
    //*********************************************给所有用户充值**************************************************************
    /*
    JsonObject
    {
        adminname
        money:
    }*/
    @Autowired
    private AdminService adminService;
    @Autowired
    private UserService userService;
    @Autowired
    private StatisticService statisticService;
    @RequestMapping(value = "/rechargeAll",method = RequestMethod.POST)
    public void rechargeAll(@RequestParam(value = "adminName") String adminname,
                         @RequestParam(value = "money") String money,
                         HttpServletResponse response)throws Exception{
        //标志
        int flag=1;
        System.out.println(adminname+"  "+money);
        //*************************插入充值记录***************************
        AdminRecordPO adminRecordPO=new AdminRecordPO();
        adminRecordPO.setAdminName(adminname);
        double tmp=Double.valueOf(money).doubleValue();
        adminRecordPO.setMoney(tmp);
        adminRecordPO.setPhone("all");

        int result=adminService.insertAdminRecord(adminRecordPO);
        if(result==0)
            flag=0;
        //***********************查找测试成功*********************************
        /*List<AdminRecordPO> adminRecordPOList=new ArrayList<AdminRecordPO>();
        adminRecordPOList=adminService.selectAllAdminRecord();
        int i=adminRecordPOList.size();
        response.getWriter().print(i);*/
        //*************************对所有用户充值*************************************
        List<UserPO> userPOList=userService.selectAllUser();
        int userNo=userPOList.size();
        System.out.println("userNo:"+userNo);
        for(UserPO userPO:userPOList){
            System.out.println(userPO);
            userPO.setRemain(userPO.getRemain()+Double.valueOf(money).doubleValue());
            result=userService.updateUser(userPO);
            if(result==0)
                flag=0;
        }
        //*************************更新当日present***************************
        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");//可以方便地修改日期格式
        String today = dateFormat.format( now );
        AuxcalPO auxcalPO=statisticService.selectTodayAuxcal(today);
        auxcalPO.setPresent(auxcalPO.getPresent()+userNo*Double.valueOf(money).doubleValue());
        result=statisticService.updateTodayAuxcal(auxcalPO);
        if(result==0)
            flag=0;
        //***************************更新总充值金额，总余额************************************
        TotalPO totalPO=statisticService.selectTotal();
        totalPO.setRecharge(totalPO.getRecharge()+userNo*Double.valueOf(money).doubleValue());
        totalPO.setRemain(totalPO.getRemain()+userNo*Double.valueOf(money).doubleValue());
        result=statisticService.updateTotal(totalPO);
        if(result==0)
            flag=0;

        if(flag==1)
            response.getWriter().print(1);
        else
            response.getWriter().print(0);
    }
//*******************************************************************************************************************************
/*给个人用户充值
    更新指定账户添加余额
    添加管理员操作记录
    更新今天的present
    更新历史recharge*/
    @RequestMapping(value = "/rechargePerson",method = RequestMethod.POST)
    public void rechargePerson(@RequestParam(value = "adminName") String adminName,
                               @RequestParam(value = "money") String money,
                               @RequestParam(value = "phone") String str,
                               HttpServletResponse response)throws Exception{
        int flag=1,result;
        //System.out.println(adminName + "  " + money + "  " + str);
        String[] phoneArray=str.split(",");
        for(String phone:phoneArray) {
            System.out.println(adminName + "  " + money + "  " + phone);
            //*********************对指定用户添加余额******************
            UserPO userPO = userService.selectUserByNumber(phone);
            if (userPO != null) {
                userPO.setRemain(userPO.getRemain() + Double.valueOf(money).doubleValue());

                result = userService.updateUser(userPO);
                if(result==0)
                    flag=0;
                //*******************插入充值记录********************
                AdminRecordPO adminRecordPO = new AdminRecordPO();
                adminRecordPO.setPhone(phone);
                adminRecordPO.setMoney(Double.valueOf(money).doubleValue());
                adminRecordPO.setAdminName(adminName);
                result = adminService.insertAdminRecord(adminRecordPO);
                if(result==0)
                    flag=0;
            }
        }
        //***********************更新当日present*********************
        int userNo=phoneArray.length;
        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");//可以方便地修改日期格式
        String today = dateFormat.format( now );
        AuxcalPO auxcalPO=statisticService.selectTodayAuxcal(today);
        auxcalPO.setPresent(auxcalPO.getPresent()+userNo*Double.valueOf(money).doubleValue());
        result=statisticService.updateTodayAuxcal(auxcalPO);
        if(result==0)
            flag=0;
        //************************更新total**************************
        TotalPO totalPO=statisticService.selectTotal();
        totalPO.setRecharge(totalPO.getRecharge()+userNo*Double.valueOf(money).doubleValue());
        totalPO.setRemain(totalPO.getRemain()+userNo*Double.valueOf(money).doubleValue());
        result=statisticService.updateTotal(totalPO);
        if(result==0)
            flag=0;

        if(flag==1)
            response.getWriter().print(1);
        else
            response.getWriter().print(0);
    }
}
