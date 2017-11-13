package com.terabits.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.terabits.meta.po.AdminRecordPO;
import com.terabits.meta.po.TerminalAdminPO;
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
            for (int i = 0; i < urlS.size(); i++) {
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

    public int insertAdminRecord(AdminRecordPO adminRecordPO) {
        SqlSession session = DBTools.getSession();
        AdminMapper adminMapper = session.getMapper(AdminMapper.class);
        int result = 0;
        try {
            result = adminMapper.insertAdminRecord(adminRecordPO);
            session.commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.rollback();
        } finally {
            session.close();
        }
        return result;
    }

    public List<AdminRecordPO> selectAllAdminRecord(String phone) {
        SqlSession session = DBTools.getSession();
        AdminMapper adminMapper = session.getMapper(AdminMapper.class);
        List<AdminRecordPO> adminRecordPOList = new ArrayList<AdminRecordPO>();
        try {
            adminRecordPOList = adminMapper.selectAllAdminRecord(phone);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return adminRecordPOList;
    }

    public int updateByAdmin(AdminPO adminPO) {
        SqlSession session = DBTools.getSession();
        AdminMapper mapper = session.getMapper(AdminMapper.class);
        int result = 0;
        try {
            result = mapper.updateByAdmin(adminPO);
            session.commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.rollback();
            // TODO: handle exception
        } finally {
            session.close();
        }
        return result;
    }

    public List<AdminPO> selectAllAdminPO() {
        SqlSession session = DBTools.getSession();
        AdminMapper mapper = session.getMapper(AdminMapper.class);
        List<AdminPO> adminPOs = null;
        try {
            adminPOs = mapper.selectAllAdminPO();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return adminPOs;
    }

    public List<String> selectDisplayidByName(String name) {
        SqlSession session = DBTools.getSession();
        AdminMapper mapper = session.getMapper(AdminMapper.class);
        List<String> strings = null;
        try {
            strings = mapper.selectDisplayidByName(name);
        } catch (Exception e) {
            e.printStackTrace();
            // TODO: handle exception
        } finally {
            session.close();
        }
        return strings;
    }

    public int insertAdminPO(AdminPO adminPO) {
        SqlSession session = DBTools.getSession();
        AdminMapper mapper = session.getMapper(AdminMapper.class);
        int result = 0;
        try {
            result = mapper.insertAdminPO(adminPO);
            session.commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.rollback();
            // TODO: handle exception
        } finally {
            session.close();
        }
        return result;
    }

    public int insertTerminalAdmin(TerminalAdminPO terminalAdminPO) {
        SqlSession session = DBTools.getSession();
        AdminMapper mapper = session.getMapper(AdminMapper.class);
        int result = 0;
        try {
            result = mapper.insertTerminalAdmin(terminalAdminPO);
            session.commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.rollback();
            // TODO: handle exception
        } finally {
            session.close();
        }
        return result;
    }
}
