package com.terabits.mapper;

import com.terabits.meta.bo.TimeSpanBO;
import com.terabits.meta.bo.UserConsumeBO;
import com.terabits.meta.po.User.ConsumeOrderPO;
import com.terabits.meta.po.User.RechargeOrderPO;
import com.terabits.meta.po.User.UserPO;
import com.terabits.meta.vo.UserConsumeVO;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by Administrator on 2017/11/8.
 */
public interface UserMapper {
    /**
     * 查询某台设备的所有消费记录，流量记录
     *
     * @param displayId
     * @return
     * @throws Exception
     */
    public List<ConsumeOrderPO> selectConsumeOrderByDisplayId(@Param("displayId") String displayId) throws Exception;


    /**
     * 查询某台设备某段时间的消费记录，流量记录
     *
     * @param displayId
     * @param beginTime
     * @param endTime
     * @return
     * @throws Exception
     */
    public List<ConsumeOrderPO> selectConsumeOrderByTime(@Param("displayId") String displayId, @Param("beginTime") String beginTime, @Param("endTime") String endTime) throws Exception;

    /**
     * 通过电话号码查询该用户
     *
     * @param phone
     * @return
     * @throws Exception
     */
    public UserPO selectUserByNumber(@Param("phone") String phone) throws Exception;

    /**
     * 通过电话号码和时间查询该用户的消费记录
     *
     * @param openId,timespanBO
     * @return
     * @throws Exception
     */
    public List<ConsumeOrderPO> selectConsumeOrderByOpenIdAndTime(@Param("openId") String openId, @Param("beginTime") String beginTime, @Param("endTime") String endTime) throws Exception;


    /**
     * 更新用户余额
     *
     * @param userPO
     * @return
     * @throws Exception
     */
    public int updateUser(UserPO userPO) throws Exception;


    /**
     * 查询所有用户
     *
     * @return
     * @throws Exception
     */
    public List<UserPO> selectAllUser() throws Exception;
    //public int addRemain(@Param("money") double money)throws Exception;

    /**
     * 查询所有的充值消费记录，用于计算平均值
     *
     * @param timeSpanBO
     * @return
     * @throws Exception
     */
    public List<RechargeOrderPO> selectAllRechargeRecord(TimeSpanBO timeSpanBO) throws Exception;

    /**
     * 查询所有的消费订单，用于计算统计
     *
     * @param timeSpanBO
     * @return
     * @throws Exception
     */
    public List<ConsumeOrderPO> selectAllConsumption(TimeSpanBO timeSpanBO) throws Exception;

    /**
     * 通过时间查询所有的新用户数量
     *
     * @param timeSpanBO
     * @return
     * @throws Exception
     */
    public int selectNewUserByTime(TimeSpanBO timeSpanBO) throws Exception;

    /**
     * 通过时间和OpenId来得到某一用户一段时间内消费情况的求和
     *
     * @param openId
     * @param timeSpanBO
     * @return
     * @throws Exception
     */
    public Double sumConsumePaymentByOpenIdAndTime(@Param("openId") String openId, @Param("timeSpanBO") TimeSpanBO timeSpanBO) throws Exception;

    /**
     * 通过时间和openId得到某一用户一段时间内的消费流量和
     * @param openId
     * @param timeSpanBO
     * @return
     * @throws Exception
     */
    public Double sumFlowByOpenIdAndTime(@Param("openId") String openId, @Param("timeSpanBO") TimeSpanBO timeSpanBO) throws Exception;

    /**
     * 根据提供的displayid, location, beginTime, endTime, phone查询订单
     * @param userConsumeBO
     * @return
     * @throws Exception
     */
    public List<UserConsumeVO> selectDynamicConsumeRecord(UserConsumeBO userConsumeBO) throws Exception;



}
