package com.terabits.service.impl;
//
import com.terabits.mapper.AdminMapper;
import com.terabits.meta.po.Admin.AdminPO;
import com.terabits.meta.po.Admin.AdminRecordPO;
import com.terabits.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import com.terabits.utils.DBTools;
import org.apache.ibatis.session.SqlSession;

 //增删改 session.commit(),session.rollback(),session.close()
//查询操作，session.close()

@Service("adminService")
public class AdminServiceImpl implements AdminService{

    //查询操作，session.close()
    public String selectAdminByAccount(String account) {
        SqlSession session = DBTools.getSession();
        AdminMapper adminMapper = session.getMapper(AdminMapper.class);
        String password="";
        try {
            password=adminMapper.selectAdminByAccount(account);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            session.close();
        }
        return password;
    }

    //增删改 session.commit(),session.rollback(),session.close()
    public int insertAdmin(String name,String password){
        SqlSession session = DBTools.getSession();
        AdminMapper adminMapper = session.getMapper(AdminMapper.class);
        int result=0;
        try {
            result=adminMapper.insertAdmin(name,password);
            session.commit();
        }catch (Exception e){
            e.printStackTrace();
            session.rollback();
        }finally {
            session.close();
        }
        return result;
    }

    public int insertAdminRecord(AdminRecordPO adminRecordPO){
        SqlSession session = DBTools.getSession();
        AdminMapper adminMapper = session.getMapper(AdminMapper.class);
        int result=0;
        try {
            result=adminMapper.insertAdminRecord(adminRecordPO);
            session.commit();
        }catch (Exception e){
            e.printStackTrace();
            session.rollback();
        }finally {
            session.close();
        }
        return result;
    }

    public List<AdminRecordPO> selectAllAdminRecord(){
        SqlSession session = DBTools.getSession();
        AdminMapper adminMapper = session.getMapper(AdminMapper.class);
        List<AdminRecordPO> adminRecordPOList=new ArrayList<AdminRecordPO>();
        try {
            adminRecordPOList=adminMapper.selectAllAdminRecord();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            session.close();
        }
        return adminRecordPOList;
    }

}
