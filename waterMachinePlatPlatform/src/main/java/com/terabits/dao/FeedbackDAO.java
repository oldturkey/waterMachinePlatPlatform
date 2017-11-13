package com.terabits.dao;

import com.terabits.meta.bo.TimeSpanBO;
import com.terabits.meta.po.FeedbackPO;

import java.util.List;

public interface FeedbackDAO {
    /**
     * 插入新的反馈记录
     * @param feedbackPO
     * @return
     * @throws Exception
     */
    public int insertFeedback(FeedbackPO feedbackPO) throws Exception;

    /**
     * 依据传输给前端的反馈id号码更新反馈的解决状态
     * @param status
     * @param id
     * @return
     * @throws Exception
     */
    public int updateFeedbackStatusById(int status, int id) throws Exception;

    /**
     * 根据查询的手机号码以及时间来返回特定用户的反馈信息
     * @param phone
     * @param timeSpanBO
     * @return
     */
    public List<FeedbackPO> selectFeedbackByPhoneAndTime(String phone, TimeSpanBO timeSpanBO) throws Exception;

    /**
     * 根据前端返回来的id数值删除数据库中对应id的反馈记录
     * @param id
     * @return
     * @throws Exception
     */
    public int deleteFeedbackById(int id) throws Exception;

    /**
     * 删除已经处理过的反馈信息
     * @param timeSpanBO
     * @param status
     * @return
     */
    public int deleteFeedbackByTimeOnlySolved(TimeSpanBO timeSpanBO, int status) throws Exception;
}
