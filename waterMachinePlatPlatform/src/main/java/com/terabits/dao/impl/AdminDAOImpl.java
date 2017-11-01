package com.terabits.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.terabits.dao.AdminDAO;
import com.terabits.mapper.AdminMapper;
import com.terabits.meta.po.AdminPO;
import com.terabits.meta.vo.AuthorityVO;
import com.terabits.utils.DBTools;

@Repository("adminDAO")
public class AdminDAOImpl implements AdminDAO {

	public AdminPO selectAdmin(String name) {
		SqlSession session = DBTools.getSession();
        AdminMapper mapper = session.getMapper(AdminMapper.class);
        AdminPO adminPO = null;
        try {
        	adminPO = mapper.selectAdmin(name);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        	session.close();
        }
        return adminPO;
	}

	public List<AuthorityVO> selectAdminAuthority(Integer type) {
		SqlSession session = DBTools.getSession();
		AdminMapper mapper = session.getMapper(AdminMapper.class);      
		List<String> urlS = null;
        try {
        	urlS = mapper.selectAllUrl();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
        	session.close();
        }
        
        session = DBTools.getSession();
        mapper = session.getMapper(AdminMapper.class);
        List<Integer> accessS = null;
        try {
        	accessS = mapper.selectAllAuthority("admin_type_" + type);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
        	session.close();
        }
        
        List<AuthorityVO> authorityVOS = new ArrayList<AuthorityVO>();
        try {
        	for(int i = 0; i < urlS.size(); i++) {
        		AuthorityVO authorityVO = new AuthorityVO();
        		authorityVO.setAccess(accessS.get(i));
        		authorityVO.setUrl(urlS.get(i));
        		authorityVOS.add(authorityVO);
        	}
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return authorityVOS;
	}

	public Integer selectAuthorityByTypeAndUrl(Integer type, String url) {
		SqlSession session = DBTools.getSession();
		AdminMapper mapper = session.getMapper(AdminMapper.class);
		try {
        	return mapper.selectAuthorityByTypeAndUrl("admin_type_" + type, "'" + url + "'");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
        	session.close();
        }
	}

}
