package com.terabits.service.impl;

import com.terabits.dao.FeedbackDAO;
import com.terabits.meta.bo.TimeSpanBO;
import com.terabits.meta.po.FeedbackPO;
import com.terabits.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("feedbackService")
public class FeedbackServiceImpl implements FeedbackService{

    @Autowired
    private FeedbackDAO feedbackDAO;

    public int insertFeedback(FeedbackPO feedbackPO) throws Exception{
        int result = feedbackDAO.insertFeedback(feedbackPO);
        return result;
    }

    public int updateFeedbackStatusById(int status, int id) throws Exception{
        int affectLines = feedbackDAO.updateFeedbackStatusById(status,id);
        return affectLines;
    }

    public List<FeedbackPO> selectFeedbackByPhoneAndTime(String phone, TimeSpanBO timeSpanBO) throws Exception{
        List<FeedbackPO> feedbackPOS = new ArrayList<>();
        feedbackPOS = feedbackDAO.selectFeedbackByPhoneAndTime(phone,timeSpanBO);
        return feedbackPOS;
    }

    public int deleteFeedbackById(int id) throws Exception{
        int result = feedbackDAO.deleteFeedbackById(id);
        return result;
    }

    public int deleteFeedbackByTimeOnlySolved(TimeSpanBO timeSpanBO,int status) throws Exception{
        int result = feedbackDAO.deleteFeedbackByTimeOnlySolved(timeSpanBO, status);
        return result;
    }
}
