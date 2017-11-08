package com.terabits.dao;

import com.terabits.meta.po.Statistic.AuxcalPO;
import com.terabits.meta.po.Statistic.TotalPO;
import org.springframework.stereotype.Repository;

/**
 * Created by Administrator on 2017/11/8.
 */

public interface StatisticDAO{

    public AuxcalPO selectTodayAuxcal(String day) ;

    public TotalPO selectTotal() ;

    public int updateTodayAuxcal(AuxcalPO auxcalPO);

    public int updateTotal(TotalPO totalPO);
}
