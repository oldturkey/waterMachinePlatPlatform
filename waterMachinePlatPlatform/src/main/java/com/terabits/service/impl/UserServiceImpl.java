package com.terabits.service.impl;

import com.terabits.dao.AdminDAO;
import com.terabits.dao.PresentDAO;
import com.terabits.dao.RechargeOrderDAO;
import com.terabits.dao.StatisticDAO;
import com.terabits.dao.UserDAO;
import com.terabits.meta.bo.TimeSpanBO;
import com.terabits.meta.bo.UserConsumeBO;
import com.terabits.meta.po.AdminPO;
import com.terabits.meta.po.AdminRecordPO;
import com.terabits.meta.po.Statistic.AuxcalPO;
import com.terabits.meta.po.Statistic.TotalPO;
import com.terabits.meta.po.User.RechargeOrderPO;
import com.terabits.meta.po.User.UserPO;
import com.terabits.meta.vo.UserConsumeVO;
import com.terabits.service.UserService;
import com.terabits.utils.JWT;
import com.terabits.utils.TimeSpanUtil;
import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDAO userDAO;
    @Autowired
    private RechargeOrderDAO rechargeOrderDAO;
    @Autowired
    private PresentDAO presentDAO;
    @Autowired
    private StatisticDAO statisticDAO;
    @Autowired
    private AdminDAO adminDAO;
    //获取首页关于用户的统计数据
    public JSONObject getHomepageUserInfo() throws Exception{

        int userNumber = userDAO.selectAllUser().size();   //获取用户总数
        TimeSpanBO today = TimeSpanUtil.generateTodayTimeSpan();
        TimeSpanBO thisMonth = TimeSpanUtil.generateMonthTimeSpan();
        double rechargeToday = rechargeOrderDAO.sumRechargePaymentByOpenIdAndTime(null, today);
        double consumeToday = userDAO.sumConsumePaymentByOpenIdAndTime(null, today);
        double presentToday = presentDAO.sumPresentByPhoneAndTime(null, today);
        double rechargeThisMonth = rechargeOrderDAO.sumRechargePaymentByOpenIdAndTime(null, thisMonth);
        double consumeThisMonth = userDAO.sumConsumePaymentByOpenIdAndTime(null, thisMonth);
        double presentThisMonth = presentDAO.sumPresentByPhoneAndTime(null, thisMonth);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("userNumber", userNumber);
        jsonObject.put("sales-day", consumeToday);
        jsonObject.put("sales-month", consumeThisMonth);
        jsonObject.put("recharge-day", rechargeToday);
        jsonObject.put("recharge_month", rechargeThisMonth);
        jsonObject.put("present-day", presentToday);
        jsonObject.put("present-month", presentThisMonth);
        return jsonObject;
    }

    //获取用户统计页面用户的统计数据，包含首页的统计数据加当月用户增长和用户消费折线图
    public JSONObject getUserStaticticss() throws Exception{
        JSONObject jsonObject = getHomepageUserInfo();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        AuxcalPO auxcalPO1 = new AuxcalPO();
        String[] date = new String[30];
        double[] recharge = new double[30];
        double[] payment = new double[30];
        int[] newUserNo = new int[30];
        TimeSpanBO timeSpanBO = new TimeSpanBO();
        for (int i = 0; i < 30; i++) {
            c.setTime(new Date());
            c.add(Calendar.DATE, -(30 - i));//向前追溯i天
            Date d = c.getTime();
            String tmp = format.format(d);
            timeSpanBO.setBeginTime(tmp + " 00:00:00");
            timeSpanBO.setEndTime(tmp + " 23:59:59");
            auxcalPO1 = statisticDAO.selectTodayAuxcal(tmp);
            date[i] = tmp;
            recharge[i] = auxcalPO1.getRecharge();
            payment[i] = auxcalPO1.getPayment();
            newUserNo[i] = userDAO.selectNewUserByTime(timeSpanBO);
        }
        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put("time", date);
        jsonObject1.put("income", payment);
        jsonObject1.put("recharge", recharge);
        jsonObject.put("income-month", jsonObject1);
        JSONObject jsonObject2 = new JSONObject();
        jsonObject2.put("time", date);
        jsonObject2.put("number", newUserNo);
        jsonObject.put("userNumber-month", jsonObject2);
        return jsonObject;
    }
    //为一组用户充值
    public JSONObject userRecharge(String clientToken, String[] phoneArray, double money){
        AdminPO adminPO = JWT.unsign(clientToken, AdminPO.class);
        String adminName = adminPO.getName();
        int flag = 1, result;

        String phoneRecord = "";  //插入到管理员充值记录中
        for (String phone : phoneArray) {
            phoneRecord = phone + "," + phoneRecord;

            //*********************对指定用户添加余额******************
            UserPO userPO = userDAO.selectUserByNumber(phone);
            if (userPO != null) {
                userPO.setPresent(userPO.getPresent() + Double.valueOf(money).doubleValue());
                result = userDAO.updateUser(userPO);
                if (result == 0)
                    flag = 0;
            }
        }
        //*******************插入充值记录********************
        AdminRecordPO adminRecordPO = new AdminRecordPO();
        adminRecordPO.setPhone(phoneRecord);
        adminRecordPO.setMoney(Double.valueOf(money).doubleValue());
        adminRecordPO.setAdminName(adminName);
        result = adminDAO.insertAdminRecord(adminRecordPO);
        if (result == 0)
            flag = 0;
        //***********************更新当日present*********************
        int userNo = phoneArray.length;
        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");//可以方便地修改日期格式
        String today = dateFormat.format(now);
        AuxcalPO auxcalPO = statisticDAO.selectTodayAuxcal(today);
        auxcalPO.setPresent(auxcalPO.getPresent() + userNo * Double.valueOf(money).doubleValue());
        result = statisticDAO.updateTodayAuxcal(auxcalPO);
        if (result == 0)
            flag = 0;
        //************************更新total**************************
        TotalPO totalPO = statisticDAO.selectTotal();
        totalPO.setPresent(totalPO.getPresent() + userNo * Double.valueOf(money).doubleValue());
        totalPO.setRemain(totalPO.getRemain() + userNo * Double.valueOf(money).doubleValue());
        result = statisticDAO.updateTotal(totalPO);
        if (result == 0)
            flag = 0;
        JSONObject jsonObject = new JSONObject();
        if (flag == 1) {
            jsonObject.put("Status", 1);
        } else {
            jsonObject.put("Status", 0);
        }
        return jsonObject;
    }

    public JSONArray adminRechargeRecord(String phone){
        List<AdminRecordPO> adminRecordPOList = adminDAO.selectAllAdminRecord(phone);
        JSONArray jsonArray = JSONArray.fromObject(adminRecordPOList);
        return jsonArray;
    }

    public JSONArray selectDynamicConsumeRecord(UserConsumeBO userConsumeBO){
        List<UserConsumeVO> userConsumeVOS = userDAO.selectDynamicConsumeRecord(userConsumeBO);
        JSONArray jsonArray = JSONArray.fromObject(userConsumeVOS);
        return jsonArray;
    }

    public JSONArray selectDynamicRechargeRecord(String phone, TimeSpanBO timeSpanBO){
        String openId = "";
        if(phone != null){
            openId = userDAO.selectUserByNumber(phone).getOpenId();
        }
        List<RechargeOrderPO> rechargeOrderPOS = new ArrayList<>();
        try{
            rechargeOrderPOS = rechargeOrderDAO.selectRechargeRecordByOpenIdAndTime(openId, timeSpanBO);
        }catch (Exception e){
            e.printStackTrace();
        }
        JSONArray rechargeInfoWithOpenId = JSONArray.fromObject(rechargeOrderPOS);
        for(int i = 0; i < rechargeInfoWithOpenId.size(); i++){
            rechargeInfoWithOpenId.getJSONObject(i).put("phone", phone);
        }
        return rechargeInfoWithOpenId;
    }
}
