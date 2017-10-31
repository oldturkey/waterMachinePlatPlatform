package com.terabits.mapper;

import com.terabits.meta.po.User.ConsumeOrderPO;
import com.terabits.meta.po.User.RechargeRecordPO;
import com.terabits.meta.po.User.UserPO;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserMapper {
    //查询某台设备的所有消费记录，流量记录
    public List<ConsumeOrderPO> selectConsumeOrderByDisplayId(@Param("displayId") String displayId) throws Exception;
    //查询某台设备某段时间的消费记录，流量记录
    public List<ConsumeOrderPO> selectConsumeOrderByTime(@Param("displayId")String displayId,@Param("beginTime")String beginTime,@Param("endTime")String endTime) throws Exception;
    //通过电话号码查询该用户
    public UserPO selectUserByNumber(@Param("phone") String phone )throws Exception;
    //更新用户余额
    public int updateUser(UserPO userPO)throws Exception;
    //查询所有用户
    public List<UserPO> selectAllUser()throws Exception;
    //public int addRemain(@Param("money") double money)throws Exception;

}
