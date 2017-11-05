package com.terabits.service.impl;

import com.terabits.dao.LogDAO;
import com.terabits.meta.bo.TimeSpanBO;
import com.terabits.meta.po.LogPO;
import com.terabits.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("logService")
public class LogServiceImpl implements LogService{

    @Autowired
    private LogDAO logDAO;

    public int insertLog(LogPO logPO) throws Exception{
        int result = logDAO.insertLog(logPO);
        return result;
    }

    public int deleteLogByTime(TimeSpanBO timeSpanBO) throws Exception{
        int result = logDAO.deleteLogByTime(timeSpanBO);
        return result;
    }

    public List<LogPO> selectLogByTypeAndTime(int type, TimeSpanBO timeSpanBO) throws Exception{
        List<LogPO> logPOS = logDAO.selectLogByTypeAndTime(type, timeSpanBO);
        return logPOS;
    }
}
