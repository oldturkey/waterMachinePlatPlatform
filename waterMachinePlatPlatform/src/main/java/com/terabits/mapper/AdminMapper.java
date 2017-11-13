package com.terabits.mapper;

import java.util.List;

import com.terabits.meta.po.AdminRecordPO;
import com.terabits.meta.po.TerminalAdminPO;
import org.apache.ibatis.annotations.Param;

import com.terabits.meta.po.AdminPO;

public interface AdminMapper {
    public AdminPO selectAdmin(@Param("name") String name);

    public List<String> selectAllUrl();

    public List<Integer> selectAllAuthority(@Param("column") String colume);

    public int selectAuthorityByTypeAndUrl(@Param("column") String colume, @Param("url") String url);

    public int updateByAdmin(AdminPO adminPO);

    //插入管理员充值记录
    public int insertAdminRecord(AdminRecordPO adminRecordPO) throws Exception;

    public List<AdminRecordPO> selectAllAdminRecord(@Param("phone") String phone) throws Exception;

    public List<AdminPO> selectAllAdminPO();

    public List<String> selectDisplayidByName(@Param("name") String name);

    public int insertAdminPO(AdminPO adminPO);

    public int insertTerminalAdmin(TerminalAdminPO terminalAdminPO);
}
