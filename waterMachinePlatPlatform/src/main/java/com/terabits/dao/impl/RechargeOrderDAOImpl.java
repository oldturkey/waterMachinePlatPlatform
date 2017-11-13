package com.terabits.dao.impl;

import com.terabits.dao.RechargeOrderDAO;
import com.terabits.mapper.RechargeOrderMapper;
import com.terabits.meta.bo.TimeSpanBO;
import com.terabits.meta.po.User.RechargeOrderPO;
import com.terabits.utils.DBTools;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/11/8.
 */
@Repository("rechargeOrderDAO")
public class RechargeOrderDAOImpl implements RechargeOrderDAO {
    public List<RechargeOrderPO> selectRechargeRecordByOpenIdAndTime(String openId, TimeSpanBO timeSpanBO) throws Exception {
        SqlSession session = DBTools.getSession();
        RechargeOrderMapper rechargeOrderMapper = session.getMapper(RechargeOrderMapper.class);
        List<RechargeOrderPO> rechargeOrderPOS = new ArrayList<RechargeOrderPO>();
        try {
            rechargeOrderPOS = rechargeOrderMapper.selectPaymentByOpenIdAndTime(openId, timeSpanBO);
        } catch (Exception e) {
            e.printStackTrace();
            session.rollback();
        } finally {
            session.close();
        }
        return rechargeOrderPOS;
    }

    public Double sumRechargePaymentByOpenIdAndTime(String openId, TimeSpanBO timeSpanBO) throws Exception {
        SqlSession session = DBTools.getSession();
        RechargeOrderMapper rechargeOrderMapper = session.getMapper(RechargeOrderMapper.class);
        Double sum = 0.0;
        try {
            sum = rechargeOrderMapper.sumRechargePaymentByOpenIdAndTime(openId, timeSpanBO);
        } catch (Exception e) {
            e.printStackTrace();
            session.rollback();
        } finally {
            session.close();
        }
        return sum;
    }
}
