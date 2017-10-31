package com.terabits.mapper;

import com.terabits.meta.po.Statistic.AuxcalPO;
import com.terabits.meta.po.Statistic.TotalPO;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StatisticMapper {

    //查询指定日期的总充值，总消费，总流量
    public AuxcalPO selectTodayAuxcal(@Param("day") String day) throws Exception;
    //查询历史统计
    public TotalPO selectTotal() throws Exception;

    //更新当日统计
    public int updateTodayAuxcal(AuxcalPO auxcalPO)throws Exception;
    //更新历史统计
    public int updateTotal(TotalPO totalPO) throws Exception;
}
