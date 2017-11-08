package com.terabits.mapper;

import java.util.List;

import com.terabits.meta.po.AdminRecordPO;
import org.apache.ibatis.annotations.Param;

import com.terabits.meta.po.AdminPO;

public interface AdminMapper {
    public AdminPO selectAdmin(@Param("name") String name);

    public List<String> selectAllUrl();

    public List<Integer> selectAllAuthority(@Param("column") String colume);

    public int selectAuthorityByTypeAndUrl(@Param("column") String colume, @Param("url") String url);

    //插入管理员充值记录
    public int insertAdminRecord(AdminRecordPO adminRecordPO) throws Exception;
    public List<AdminRecordPO> selectAllAdminRecord(@Param("phone") String phone) throws Exception;
}
