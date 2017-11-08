package com.terabits.mapper;

import com.terabits.meta.bo.TimeSpanBO;
import org.springframework.data.repository.query.Param;

/**
 * Created by Administrator on 2017/11/8.
 */
public interface PresentMapper {
    /**
     * 根据用户电话以及时间返回总赠送金额之和
     * @param phone
     * @param timeSpanBO
     * @return
     * @throws Exception
     */
    Double sumPresentByPhoneAndTime(@Param("phone") String phone, @Param("timeSpanBO")
            TimeSpanBO timeSpanBO) throws Exception;
}

