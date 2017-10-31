package com.terabits.service;

import com.terabits.meta.po.Statistic.AuxcalPO;
import com.terabits.meta.po.Statistic.TotalPO;

import java.util.List;

public interface StatisticService {

    public AuxcalPO selectTodayAuxcal(String day) ;
    public TotalPO selectTotal() ;

    public int updateTodayAuxcal(AuxcalPO auxcalPO);
    public int updateTotal(TotalPO totalPO);

}
