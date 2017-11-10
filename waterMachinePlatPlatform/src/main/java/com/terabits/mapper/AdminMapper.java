package com.terabits.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.terabits.meta.po.AdminPO;
import com.terabits.meta.po.TerminalAdminPO;
/***************************
 * ��������
 * 		��ͨ������Ҫʹ��@Param
 * 		PO��BO��VOֱ��ʹ��
 * ��������
 * 		select���ַ�������
 * 		insert��update��delete����int
 * @author MasterXu
 * 
 *
 */
public interface AdminMapper {
	//��¼���һ�����
	public AdminPO selectAdmin(@Param("name") String name);
	public List<String> selectAllUrl();
	public List<Integer> selectAllAuthority(@Param("column") String colume);
	public int selectAuthorityByTypeAndUrl(@Param("column") String colume, @Param("url") String url);
	public int updateByAdmin(AdminPO adminPO);
	
	//�˻�����
	public List<AdminPO> selectAllAdminPO();
	public List<String> selectDisplayidByName(@Param("name") String name);
	public int insertAdminPO(AdminPO adminPO);
	public int insertTerminalAdmin(TerminalAdminPO terminalAdminPO);
		
}
