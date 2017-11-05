package com.terabits.mapper;

import com.terabits.meta.bo.TimeSpanBO;
import com.terabits.meta.po.FeedbackPO;
import com.terabits.meta.po.LogPO;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface LogMapper {
    /**
     * 插入新的日志记录
     * @param logPO
     * @return
     * @throws Exception
     */
    public int insertLog(LogPO logPO) throws Exception;

    /**
     * 依据时间删除日志内容，内容过多可以使用这个接口操作
     * @param timeSpanBO
     * @return
     * @throws Exception
     */
    public int deleteLogByTime(TimeSpanBO timeSpanBO) throws Exception;

    /**
     * 依据日志类型以及时间返回日志记录
     * @param timeSpanBO
     * @return
     * @throws Exception
     */
    public List<LogPO> selectLogByTypeAndTime(@Param("type") int type, TimeSpanBO timeSpanBO) throws Exception;
}
