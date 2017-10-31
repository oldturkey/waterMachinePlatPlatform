package com.terabits.service.impl;

import com.terabits.mapper.StatisticMapper;
import com.terabits.mapper.UserMapper;
import com.terabits.meta.po.User.ConsumeOrderPO;
import com.terabits.meta.po.User.UserPO;
import com.terabits.service.UserService;
import com.terabits.utils.DBTools;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

//增删改 session.commit(),session.rollback(),session.close()
//查询操作，session.close()
@Service("userService")
public class UserServiceImpl implements UserService{
    /**
     * 通过设备编号查询订单记录
     */
    public List<ConsumeOrderPO> selectConsumeOrderByDisplayId(String displayId) {
        SqlSession session = DBTools.getSession();
        UserMapper userMapper  = session.getMapper(UserMapper.class);
        List<ConsumeOrderPO> consumeOrderPOList=new ArrayList<ConsumeOrderPO>();
        try {
            consumeOrderPOList=userMapper.selectConsumeOrderByDisplayId(displayId);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            session.close();
        }
        return consumeOrderPOList;
    }

    /**
     * 查询某段时间消费记录
     */
    public List<ConsumeOrderPO> selectConsumeOrderByTime(String displayId,String beginTime, String endTime) {
        SqlSession session = DBTools.getSession();
        UserMapper userMapper  = session.getMapper(UserMapper.class);
        List<ConsumeOrderPO> consumeOrderPOList=new ArrayList<ConsumeOrderPO>();
        try {
            consumeOrderPOList=userMapper.selectConsumeOrderByTime(displayId,beginTime,endTime);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            session.close();
        }
        return consumeOrderPOList;
    }

    public UserPO selectUserByNumber(String phone){
        SqlSession session = DBTools.getSession();
        UserMapper userMapper  = session.getMapper(UserMapper.class);
        UserPO userPO=new UserPO();
        try {
            userPO=userMapper.selectUserByNumber(phone);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            session.close();
        }
        return userPO;
    }

    public int updateUser(UserPO userPO){
        SqlSession session = DBTools.getSession();
        UserMapper userMapper  = session.getMapper(UserMapper.class);
        int result=0;
        try {
            result=userMapper.updateUser(userPO);
            session.commit();
        }catch (Exception e){
            e.printStackTrace();
            session.rollback();
        }finally {
            session.close();
        }
        return result;
    }

    public List<UserPO> selectAllUser(){
        SqlSession session = DBTools.getSession();
        UserMapper userMapper  = session.getMapper(UserMapper.class);
        List<UserPO> userPOList=new ArrayList<UserPO>();
        try {
            userPOList=userMapper.selectAllUser();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            session.close();
        }
        return userPOList;
    }
}
