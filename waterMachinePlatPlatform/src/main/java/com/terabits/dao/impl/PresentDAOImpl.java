package com.terabits.dao.impl;

import com.terabits.dao.PresentDAO;
import com.terabits.mapper.PresentMapper;
import com.terabits.meta.bo.TimeSpanBO;
import com.terabits.service.impl.UserServiceImpl;
import com.terabits.utils.DBTools;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

/**
 * Created by Administrator on 2017/11/8.
 */
@Repository("presentDAO")
public class PresentDAOImpl implements PresentDAO {

    private static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    //根据用户手机号和时间返回赠送金额总和
    public double sumPresentByPhoneAndTime(String phone, TimeSpanBO timeSpanBO){
        SqlSession session = DBTools.getSession();
        PresentMapper presentMapper = session.getMapper(PresentMapper.class);
        Double sum = 0.0;
        try {
            sum = presentMapper.sumPresentByPhoneAndTime(phone, timeSpanBO);
        } catch (Exception e) {
            logger.error("presentMapper.sumPresentByPhoneAndTime error in PresentDAOImpl" + e.toString());
            e.printStackTrace();
        } finally {
            session.close();
        }
        return sum;
    }
}
