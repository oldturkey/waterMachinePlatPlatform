package com.terabits.dao;

import com.terabits.meta.bo.TimeSpanBO;
import com.terabits.meta.bo.UserConsumeBO;
import com.terabits.meta.po.User.ConsumeOrderPO;
import com.terabits.meta.po.User.RechargeOrderPO;
import com.terabits.meta.po.User.UserPO;
import com.terabits.meta.vo.UserConsumeVO;

import java.util.List;

public interface UserDAO {
    /**
     * 通过设备编号查询订单记录
     */
    public List<ConsumeOrderPO> selectConsumeOrderByDisplayId(String displayId);

    /**
     * 查询某段时间消费记录
     */
    public List<ConsumeOrderPO> selectConsumeOrderByTime(String displayId, String beginTime, String endTime);

    /**
     * 通过openID或者openId和时间查询，时间可以为空
     * @param openId
     * @param timeSpanBO
     * @return
     */
    public List<ConsumeOrderPO> selectConsumeOrderByOpenIdAndTime(String openId, TimeSpanBO timeSpanBO);

    public int updateUser(UserPO userPO);

    public UserPO selectUserByNumber(String phone);

    public List<UserPO> selectAllUser();

    public List<RechargeOrderPO> selectAllRechargeRecord(TimeSpanBO timeSpanBO);

    public List<ConsumeOrderPO> selectAllConsumption(TimeSpanBO timeSpanBO);

    public int selectNewUserByTime(TimeSpanBO timeSpanBO);

    public Double sumConsumePaymentByOpenIdAndTime(String openId, TimeSpanBO timeSpanBO);

    public Double sumFlowByOpenIdAndTime(String openId, TimeSpanBO timeSpanBO);

    public List<UserConsumeVO> selectDynamicConsumeRecord(UserConsumeBO userConsumeBO);

}
