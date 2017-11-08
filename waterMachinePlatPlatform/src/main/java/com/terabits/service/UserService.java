package com.terabits.service;

import net.sf.json.JSONObject;

public interface UserService {
    //获取首页关于用户的统计数据
    public JSONObject getHomepageUserInfo() throws Exception;
}
