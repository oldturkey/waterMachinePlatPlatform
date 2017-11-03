package com.terabits.dao.impl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.terabits.dao.DeviceDAO;
import com.terabits.mapper.DeviceMapper;
import com.terabits.meta.po.TerminalPO;
import com.terabits.meta.vo.DeviceOfflineAlarmVO;
import com.terabits.meta.vo.DeviceSupplyInfoVO;
import com.terabits.meta.vo.DeviceSupplyRecordVO;
import com.terabits.utils.DBTools;

@Repository("deviceDAO")
public class DeviceDAOImpl implements DeviceDAO {

	public List<String> selectDisplayIdByAdmin(String name, int type) {
		SqlSession session = DBTools.getSession();
        DeviceMapper mapper = session.getMapper(DeviceMapper.class);
        List<String> displayIdS = null;
        try {
        	displayIdS = mapper.selectDisplayIdByAdmin(name, type);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        	session.close();
        }
        return displayIdS;
	}

	public int selectDeviceNumberByAdmin(String name, int type) {
		SqlSession session = DBTools.getSession();
        DeviceMapper mapper = session.getMapper(DeviceMapper.class);
        int number = 0;
        try {
        	number = mapper.selectDeviceNumberByAdmin(name, type);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        	session.close();
        }
        return number;
	}

	public double selectTotalSupplyByAdmin(String name, int type, String beginTime, String endTime) {
		SqlSession session = DBTools.getSession();
        DeviceMapper mapper = session.getMapper(DeviceMapper.class);
        double supply = 0;
        try {
        	supply = mapper.selectTotalSupplyByAdmin(name, type, beginTime, endTime);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        	session.close();
        }
        return supply;
	}

	public double selectTotalIncomeByAdmin(String name, int type, String beginTime, String endTime) {
		SqlSession session = DBTools.getSession();
        DeviceMapper mapper = session.getMapper(DeviceMapper.class);
        double income = 0;
        try {
        	income = mapper.selectTotalIncomeByAdmin(name, type, beginTime, endTime);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        	session.close();
        }
        return income;
	}

	public List<DeviceSupplyRecordVO> selectDeviceSupplyRecordByAdmin(String name, int type, String beginTime,
			String endTime) {
		SqlSession session = DBTools.getSession();
        DeviceMapper mapper = session.getMapper(DeviceMapper.class);
        List<DeviceSupplyRecordVO> deviceSupplyRecordVOS = null;
        try {
        	deviceSupplyRecordVOS = mapper.selectDeviceSupplyRecordByAdmin(name, type, beginTime, endTime);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        	session.close();
        }
        return deviceSupplyRecordVOS;
	}

	public List<DeviceOfflineAlarmVO> selectDeviceOfflineAlarmByAdmin(String name, int type, String displayid, 
			String beginTime, String endTime) {
		SqlSession session = DBTools.getSession();
        DeviceMapper mapper = session.getMapper(DeviceMapper.class);
        List<DeviceOfflineAlarmVO> deviceOfflineAlarmVOS = null;
        try {
        	deviceOfflineAlarmVOS = mapper.selectDeviceOfflineAlarmByAdmin(name, type, displayid, beginTime, endTime);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        	session.close();
        }
        return deviceOfflineAlarmVOS;
	}

	public List<DeviceSupplyInfoVO> selectDeviceSupplyInfo(String name, int type, String displayid, String location) {
		SqlSession session = DBTools.getSession();
        DeviceMapper mapper = session.getMapper(DeviceMapper.class);
        List<DeviceSupplyInfoVO> deviceSupplyInfoVOS = null;
        try {
        	deviceSupplyInfoVOS = mapper.selectDeviceSupplyInfo(name, type, displayid, location);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        	session.close();
        }
        return deviceSupplyInfoVOS;
	}

	public int insertTerminal(TerminalPO terminalPO) {
		SqlSession session = DBTools.getSession();
        DeviceMapper mapper = session.getMapper(DeviceMapper.class);
        try {
        	mapper.insertTerminal(terminalPO);
        	session.commit();
        	return 200;
        } catch (Exception e) {
            e.printStackTrace();
            session.rollback();
            return 400;
        } finally {
        	session.close();
        }
	}

	public int addAdminToTerminal(Map<String, Object> params) {
		SqlSession session = DBTools.getSession();
        DeviceMapper mapper = session.getMapper(DeviceMapper.class);
        try {
        	mapper.addAdminToTerminal(params);
        	session.commit();
        	return 200;
        } catch (Exception e) {
            e.printStackTrace();
            session.rollback();
            return 400;
        } finally {
        	session.close();
        }
	}

	public int deleteTerminal(String displayid) {
		SqlSession session = DBTools.getSession();
        DeviceMapper mapper = session.getMapper(DeviceMapper.class);
        try {
        	mapper.deleteTerminal(displayid);
        	session.commit();
        	return 200;
        } catch (Exception e) {
            e.printStackTrace();
            session.rollback();
            return 400;
        } finally {
        	session.close();
        }
	}

	public TerminalPO selectTerminalByDisplayid(String displayid) {
		SqlSession session = DBTools.getSession();
        DeviceMapper mapper = session.getMapper(DeviceMapper.class);
        TerminalPO terminalPO = null;
        try {
        	terminalPO = mapper.selectTerminalByDisplayid(displayid);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        	session.close();
        }
        return terminalPO;
	}

	public TerminalPO selectTerminalByImei(String imei) {
		SqlSession session = DBTools.getSession();
        DeviceMapper mapper = session.getMapper(DeviceMapper.class);
        TerminalPO terminalPO = null;
        try {
        	terminalPO = mapper.selectTerminalByImei(imei);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        	session.close();
        }
        return terminalPO;
	}

	public int updateTerminalWebid(String webid, String displayid) {
		SqlSession session = DBTools.getSession();
        DeviceMapper mapper = session.getMapper(DeviceMapper.class);
        try {
        	mapper.updateTerminalWebid(webid, displayid);
        	session.commit();
        	return 200;
        } catch (Exception e) {
            e.printStackTrace();
            session.rollback();
            return 400;
        } finally {
        	session.close();
        }
	}

	public int selectOfflineCounts(String name, int type, String displayid, String beginTime, String endTime) {
		SqlSession session = DBTools.getSession();
        DeviceMapper mapper = session.getMapper(DeviceMapper.class);
        int count = 0;
        try {
        	count = mapper.selectOfflineCounts(name, type, displayid, beginTime, endTime);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        	session.close();
        }
        return count;
	}

}
