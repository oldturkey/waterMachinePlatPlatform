package com.terabits.dao.impl;

import com.terabits.dao.FeedbackDAO;
import com.terabits.mapper.FeedbackMapper;
import com.terabits.meta.bo.TimeSpanBO;
import com.terabits.meta.po.FeedbackPO;
import com.terabits.utils.DBTools;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository("feedbackDAO")
public class FeedbackDAOImpl implements FeedbackDAO  {

    public int insertFeedback(FeedbackPO feedbackPO) throws Exception{
        SqlSession session = DBTools.getSession();
        FeedbackMapper feedbackMapper = session.getMapper(FeedbackMapper.class);
        int result = 0;
        try {
            result = feedbackMapper.insertFeedback(feedbackPO);
            session.commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.rollback();
        } finally {
            session.close();
        }
        return result;
    }

    public int updateFeedbackStatusById(int status, int id) throws Exception {
        SqlSession session = DBTools.getSession();
        FeedbackMapper feedbackMapper = session.getMapper(FeedbackMapper.class);
        int affectedLines = 0;
        try {
            affectedLines = feedbackMapper.updateFeedbackStatusById(status, id);
            session.commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.rollback();
        } finally {
            session.close();
        }
        return affectedLines;
    }

    public List<FeedbackPO> selectFeedbackByPhoneAndTime(String phone, TimeSpanBO timeSpanBO) throws Exception {
        SqlSession session = DBTools.getSession();
        FeedbackMapper feedbackMapper = session.getMapper(FeedbackMapper.class);
        List<FeedbackPO> feedbackPOS = new ArrayList<FeedbackPO>();
        try {
            feedbackPOS = feedbackMapper.selectFeedbackByPhoneAndTime(phone, timeSpanBO);
        } catch (Exception e) {
            e.printStackTrace();
            session.rollback();
        } finally {
            session.close();
        }
        return feedbackPOS;
    }

    public int deleteFeedbackById(int id) throws Exception {
        SqlSession session = DBTools.getSession();
        FeedbackMapper feedbackMapper = session.getMapper(FeedbackMapper.class);
        int affectedLines = 0;
        try {
            affectedLines = feedbackMapper.deleteFeedbackById(id);
            session.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return affectedLines;
    }

    public int deleteFeedbackByTimeOnlySolved(TimeSpanBO timeSpanBO, int status) throws Exception {
        SqlSession session = DBTools.getSession();
        FeedbackMapper feedbackMapper = session.getMapper(FeedbackMapper.class);
        int affectedLines = 0;
        try {
            affectedLines = feedbackMapper.deleteFeedbackByTimeOnlySolved(timeSpanBO, status);
            session.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return affectedLines;
    }
}