package com.terabits.mapper;

import com.terabits.meta.bo.TimeSpanBO;
import org.apache.ibatis.annotations.Param;
import com.terabits.meta.po.Device.TerminalPO;
import com.terabits.meta.bo.TerminalUpdateBO;
import com.terabits.meta.bo.CommunicationBO;
import com.terabits.meta.bo.NumberBO;
import com.terabits.meta.po.Device.OperationPO;

import java.util.List;

public interface DeviceMapper {


    //根据displayid查询对应终端
    public TerminalPO selectOneTerminal(@Param("displayId") String displayId) throws Exception;

    //返回全部终端数据
    public List<TerminalPO> selectAllTerminal() throws Exception;

    //返回当前查询饮水机的地址
    public String selectLocation(@Param("displayId") String displayId) throws Exception;
}
