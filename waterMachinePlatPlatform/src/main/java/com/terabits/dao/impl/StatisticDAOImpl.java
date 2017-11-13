package com.terabits.dao.impl;

import com.terabits.dao.StatisticDAO;
import com.terabits.mapper.StatisticMapper;
import com.terabits.meta.po.Statistic.AuxcalPO;
import com.terabits.meta.po.Statistic.TotalPO;
import com.terabits.utils.DBTools;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

/**
 * Created by Administrator on 2017/11/8.
 */
@Repository("statisticDAO")
public class StatisticDAOImpl implements StatisticDAO {
    public AuxcalPO selectTodayAuxcal(String day) {
        SqlSession session = DBTools.getSession();
        StatisticMapper statisticMapper = session.getMapper(StatisticMapper.class);
        AuxcalPO auxcalPO = new AuxcalPO();
        try {
            auxcalPO = statisticMapper.selectTodayAuxcal(day);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            session.close();
        }
        return auxcalPO;
    }

    public TotalPO selectTotal() {
        SqlSession session = DBTools.getSession();
        StatisticMapper statisticMapper = session.getMapper(StatisticMapper.class);
        TotalPO totalPO = new TotalPO();
        try {
            totalPO = statisticMapper.selectTotal();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            session.close();
        }
        return totalPO;
    }

    public int updateTodayAuxcal(AuxcalPO auxcalPO){
        SqlSession session = DBTools.getSession();
        StatisticMapper statisticMapper = session.getMapper(StatisticMapper.class);
        int result=0;
        try {
            result=statisticMapper.updateTodayAuxcal(auxcalPO);
            session.commit();
        }catch (Exception e){
            e.printStackTrace();
            session.rollback();
        }finally {
            session.close();
        }
        return result;
    }

    public int updateTotal(TotalPO totalPO){
        SqlSession session = DBTools.getSession();
        StatisticMapper statisticMapper = session.getMapper(StatisticMapper.class);
        int result=0;
        try {
            result=statisticMapper.updateTotal(totalPO);
            session.commit();
        }catch (Exception e){
            e.printStackTrace();
            session.rollback();
        }finally {
            session.close();
        }
        return result;
    }
}
