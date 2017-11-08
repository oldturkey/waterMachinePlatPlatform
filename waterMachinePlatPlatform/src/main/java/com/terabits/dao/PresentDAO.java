package com.terabits.dao;

import com.terabits.meta.bo.TimeSpanBO;

/**
 * Created by Administrator on 2017/11/8.
 */
public interface PresentDAO {

    double sumPresentByPhoneAndTime(String phone, TimeSpanBO timeSpanBO);
}
