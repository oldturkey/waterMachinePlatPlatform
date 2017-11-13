package com.terabits.service;

import com.terabits.meta.bo.TimeSpanBO;
import com.terabits.meta.bo.UserConsumeBO;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public interface UserService {
    //获取首页关于用户的统计数据
    public JSONObject getHomepageUserInfo() throws Exception;

    //获取用户统计页面用户的统计数据，包含首页的统计数据加当月用户增长和用户消费折线图
    public JSONObject getUserStaticticss() throws Exception;

    //为一组用户充值
    public JSONObject userRecharge(String clientToken, String[] phoneArray, double money);

    //查询某位用户的充值记录
    public JSONArray adminRechargeRecord(String phone);

    //根据给定条件查询用户消费数据
    public JSONArray selectDynamicConsumeRecord(UserConsumeBO userConsumeBO);

    //根据给定条件查询用户充值数据
    public JSONArray selectDynamicRechargeRecord(String openId, TimeSpanBO timeSpanBO);

    //查询用户信息
    public JSONArray getUserInfo(String phone);
}
