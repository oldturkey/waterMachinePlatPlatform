package com.terabits.service;

import com.terabits.meta.po.Admin.AdminPO;
import com.terabits.meta.po.Admin.AdminRecordPO;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface AdminService {
    public String selectAdminByAccount(String account) ;
    public int insertAdmin(String name,String password);
    public int insertAdminRecord(AdminRecordPO adminRecordPO);
    public List<AdminRecordPO> selectAllAdminRecord();
}
