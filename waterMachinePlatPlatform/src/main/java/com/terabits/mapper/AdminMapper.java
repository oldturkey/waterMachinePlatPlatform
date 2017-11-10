package com.terabits.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.terabits.meta.po.AdminPO;
import com.terabits.meta.po.TerminalAdminPO;
/***************************
 * 参数类型
 * 		普通类型需要使用@Param
 * 		PO，BO，VO直接使用
 * 返回类型
 * 		select各种返回类型
 * 		insert、update、delete返回int
 * @author MasterXu
 * 
 *
 */
public interface AdminMapper {
	//登录，找回密码
	public AdminPO selectAdmin(@Param("name") String name);
	public List<String> selectAllUrl();
	public List<Integer> selectAllAuthority(@Param("column") String colume);
	public int selectAuthorityByTypeAndUrl(@Param("column") String colume, @Param("url") String url);
	public int updateByAdmin(AdminPO adminPO);
	
	//账户管理
	public List<AdminPO> selectAllAdminPO();
	public List<String> selectDisplayidByName(@Param("name") String name);
	public int insertAdminPO(AdminPO adminPO);
	public int insertTerminalAdmin(TerminalAdminPO terminalAdminPO);
		
}
