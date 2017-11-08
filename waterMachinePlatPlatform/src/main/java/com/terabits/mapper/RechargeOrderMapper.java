package com.terabits.mapper;

import com.terabits.meta.bo.TimeSpanBO;
import com.terabits.meta.po.User.RechargeOrderPO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Administrator on 2017/11/8.
 */
public interface RechargeOrderMapper {
    /**
     * 根据orderId查询订单，主要为了查这笔交易的金额，与微信回调做比对，防止交易被篡改
     *
     * @param orderId
     * @return
     * @throws Exception
     */
    public RechargeOrderPO selectPaymentByOrderId(@Param("orderId") String orderId) throws Exception;

    /**
     * 查询某用户全部充值记录
     *
     * @param openId
     * @return
     * @throws Exception
     */
    public List<RechargeOrderPO> selectPaymentByOpenIdAndTime(@Param("openId") String openId, @Param("timeSpanBO") TimeSpanBO timeSpanBO) throws Exception;

    /**
     * 通过openId和时间计算一段时间内用户的充值金额总和
     * @param openId
     * @param timeSpanBO
     * @return
     * @throws Exception
     */
    public Double sumRechargePaymentByOpenIdAndTime(@Param("openId") String openId, @Param("timeSpanBO") TimeSpanBO timeSpanBO) throws Exception;

}
