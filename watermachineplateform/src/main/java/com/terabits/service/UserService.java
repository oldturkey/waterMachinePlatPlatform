package com.terabits.service;

import com.terabits.meta.po.User.ConsumeOrderPO;
import com.terabits.meta.po.User.UserPO;

import java.util.List;

public interface UserService {

    /**
     * 通过设备编号查询订单记录
     */
    public List<ConsumeOrderPO> selectConsumeOrderByDisplayId(String displayId) ;

    /**
     * 查询某段时间消费记录
     */
    public List<ConsumeOrderPO> selectConsumeOrderByTime(String displayId,String beginTime, String endTime) ;

    public int updateUser(UserPO userPO);

    public UserPO selectUserByNumber(String phone);

    public List<UserPO> selectAllUser();


}
