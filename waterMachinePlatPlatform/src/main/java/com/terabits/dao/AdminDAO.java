package com.terabits.dao;

import java.util.List;

import com.terabits.meta.po.AdminPO;
import com.terabits.meta.po.TerminalAdminPO;
import com.terabits.meta.vo.AuthorityVO;

public interface AdminDAO {
	public AdminPO selectAdmin(String name);
	public List<AuthorityVO> selectAdminAuthority(Integer type);
	public Integer selectAuthorityByTypeAndUrl(Integer type, String url);
	
	public int updateByAdmin(AdminPO adminPO);
	public List<AdminPO> selectAllAdminPO();
	public List<String> selectDisplayidByName(String name);
	public int insertAdminPO(AdminPO adminPO);
	public int insertTerminalAdmin(TerminalAdminPO terminalAdminPO);
}
