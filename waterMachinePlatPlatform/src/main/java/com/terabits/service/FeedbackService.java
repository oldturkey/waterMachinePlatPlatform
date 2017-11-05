package com.terabits.service;

import com.terabits.meta.bo.TimeSpanBO;
import com.terabits.meta.po.FeedbackPO;

import java.util.List;

public interface FeedbackService {
    /**
     * 插入反馈记录
     * @param feedbackPO
     * @return 受影响的行数
     */
    public int insertFeedback(FeedbackPO feedbackPO) throws Exception;

    /**
     * 通过id更新反馈的状态
     *
     * @param status,id
     * @return 受影响的行数
     */
    public int updateFeedbackStatusById(int status, int id) throws Exception;

    /**
     * 通过电话号码来查询反馈
     *
     * @param phone
     * @return该电话号码相关的所有反馈
     */
    public List<FeedbackPO> selectFeedbackByPhoneAndTime(String phone, TimeSpanBO timeSpanBO) throws Exception;

    /**
     * 通过电话号码删除反馈
     *
     * @param id
     * @return 受影响的行数
     */
    public int deleteFeedbackById(int id) throws Exception;

    /**
     * 通过时间段删除已经解决的反馈
     *
     * @param timeSpanBO
     * @return 受影响的行数
     */
    public int deleteFeedbackByTimeOnlySolved(TimeSpanBO timeSpanBO, int status) throws Exception;
}
