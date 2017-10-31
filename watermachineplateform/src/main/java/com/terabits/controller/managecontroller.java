package com.terabits.controller;

import com.terabits.meta.po.Statistic.AuxcalPO;
import com.terabits.meta.po.Statistic.TotalPO;
import com.terabits.service.StatisticService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
//*********************************************经营管理**************************************************************
    /*
    JsonObject
    {
        manage1:
        {
            todayrecharge:
            todayconsume:
            remain:
        }
        manage2:
        [
            {
                key:
                date:
                recharge:
                consume:
            }
            {
                ……
            }
        ]
    }*/
@Controller
public class managecontroller {

    @Autowired
    private StatisticService statisticService;

    @RequestMapping(value = "/manage",method = RequestMethod.GET)
    public void manage1(HttpServletResponse response)throws Exception{
        JSONObject jsonObject=new JSONObject();
        //***************获取当天的总充值、总消费，历史总计余额*************************************************
        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");//可以方便地修改日期格式
        String today = dateFormat.format( now );
        AuxcalPO auxcalPO=statisticService.selectTodayAuxcal(today);


        TotalPO totalPO=statisticService.selectTotal();
        double remain=totalPO.getRemain();

        JSONObject manage1=new JSONObject();
        manage1.put("recharge",auxcalPO.getRecharge());
        manage1.put("present",auxcalPO.getPresent());
        manage1.put("todayrecharge",auxcalPO.getRecharge()+auxcalPO.getPresent());
        manage1.put("todayconsume",auxcalPO.getPayment());
        manage1.put("remain",remain);
        //System.out.println(manage1);
        //**************************获取前一个月的充值，消费情况****************************************************
        JSONObject manage2=new JSONObject();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        AuxcalPO auxcalPO1=new AuxcalPO();
        String[] date=new String[30];
        double[] recharge=new double[30];
        double[] payment=new double[30];
        for(int i=0;i<30;i++) {
            c.setTime(new Date());
            c.add(Calendar.DATE, -(30-i));//向前追溯i天
            Date d = c.getTime();
            String tmp = format.format(d);

            auxcalPO1=statisticService.selectTodayAuxcal(tmp);
            date[i]=tmp;
            recharge[i]=auxcalPO1.getRecharge()+auxcalPO1.getPresent();
            payment[i]=auxcalPO1.getPayment();
        }
        manage2.put("date",date);
        manage2.put("recharge",recharge);
        manage2.put("payment",payment);
        //System.out.println(manage2);

        //******************************************************************************
        jsonObject.put("manage1",manage1);
        jsonObject.put("manage2",manage2);
        response.getWriter().print(jsonObject);
    }


}
