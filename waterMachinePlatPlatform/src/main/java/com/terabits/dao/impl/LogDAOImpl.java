package com.terabits.dao.impl;

import com.terabits.dao.LogDAO;
import com.terabits.mapper.LogMapper;
import com.terabits.meta.bo.TimeSpanBO;
import com.terabits.meta.po.LogPO;
import com.terabits.utils.DBTools;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository("logDAO")
public class LogDAOImpl implements LogDAO{

    public int insertLog(LogPO logPO) throws Exception{
        SqlSession session = DBTools.getSession();
        LogMapper logMapper = session.getMapper(LogMapper.class);
        int result = 0;
        try {
            result = logMapper.insertLog(logPO);
            session.commit();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            session.close();
        }
        return result;
    }

    public int deleteLogByTime(TimeSpanBO timeSpanBO) throws Exception{
        SqlSession session = DBTools.getSession();
        LogMapper logMapper = session.getMapper(LogMapper.class);
        int result = 0;
        try {
            result = logMapper.deleteLogByTime(timeSpanBO);
            session.commit();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            session.close();
        }
        return result;
    }

    public List<LogPO> selectLogByTypeAndTime(int type, TimeSpanBO timeSpanBO) throws Exception{
        SqlSession session = DBTools.getSession();
        LogMapper logMapper = session.getMapper(LogMapper.class);
        List<LogPO> logPOS = new ArrayList<>();
        try {
            logPOS = logMapper.selectLogByTypeAndTime(type,timeSpanBO);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            session.close();
        }
        return logPOS;
    }
}
