package com.terabits.dao;


import com.terabits.meta.bo.TimeSpanBO;
import com.terabits.meta.po.User.RechargeOrderPO;

import java.util.List;

/**
 * Created by Administrator on 2017/11/8.
 */
public interface RechargeOrderDAO {

    /**
     * 更具用户openId查询充值记录信息
     *
     * @param openId
     * @param timeSpanBO
     * @return
     * @throws Exception
     */
    public List<RechargeOrderPO> selectRechargeRecordByOpenIdAndTime(String openId, TimeSpanBO timeSpanBO) throws Exception;


    /**
     * 通过openId和时间计算一段时间内用户的充值金额总和
     *
     * @param openId
     * @param timeSpanBO
     * @return
     * @throws Exception
     */
    public Double sumRechargePaymentByOpenIdAndTime(String openId, TimeSpanBO timeSpanBO) throws Exception;


}
