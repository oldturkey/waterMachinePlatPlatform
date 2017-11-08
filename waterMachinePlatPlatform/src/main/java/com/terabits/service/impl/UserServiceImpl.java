package com.terabits.service.impl;

import com.terabits.dao.PresentDAO;
import com.terabits.dao.RechargeOrderDAO;
import com.terabits.dao.UserDAO;
import com.terabits.meta.bo.TimeSpanBO;
import com.terabits.service.UserService;
import com.terabits.utils.TimeSpanUtil;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDAO userDAO;
    @Autowired
    private RechargeOrderDAO rechargeOrderDAO;
    @Autowired
    private PresentDAO presentDAO;
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

}
