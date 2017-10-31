package com.terabits.mapper;

import com.terabits.meta.po.Admin.AdminPO;
import com.terabits.meta.po.Admin.AdminRecordPO;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AdminMapper {
    //返回管理员密码
    public String selectAdminByAccount(@Param("account") String account) throws Exception;
    public int insertAdmin(@Param("name") String name,@Param("password") String password) throws Exception;
    //插入管理员充值记录
    public int insertAdminRecord(AdminRecordPO adminRecordPO) throws Exception;
    public List<AdminRecordPO> selectAllAdminRecord() throws Exception;
}
