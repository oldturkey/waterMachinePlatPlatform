package com.terabits.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.terabits.meta.po.AdminPO;

public interface AdminMapper {
	public AdminPO selectAdmin(@Param("name") String name);
	public List<String> selectAllUrl();
	public List<Integer> selectAllAuthority(@Param("column") String colume);
	public int selectAuthorityByTypeAndUrl(@Param("column") String colume, @Param("url") String url);
}
