package com.terabits.dao.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.terabits.dao.DeviceDAO;
import com.terabits.mapper.DeviceMapper;
import com.terabits.meta.vo.DeviceOfflineAlarmVO;
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

	public double selectTotalSupplyDayByAdmin(String name, int type) {
		SqlSession session = DBTools.getSession();
        DeviceMapper mapper = session.getMapper(DeviceMapper.class);
        double supply = 0;
        try {
        	supply = mapper.selectTotalSupplyDayByAdmin(name, type);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        	session.close();
        }
        return supply;
	}

	public double selectTotalSupplyMonthByAdmin(String name, int type) {
		SqlSession session = DBTools.getSession();
        DeviceMapper mapper = session.getMapper(DeviceMapper.class);
        double supply = 0;
        try {
        	supply = mapper.selectTotalSupplyMonthByAdmin(name, type);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        	session.close();
        }
        return supply;
	}

	public double selectTotalIncomeDayByAdmin(String name, int type) {
		SqlSession session = DBTools.getSession();
        DeviceMapper mapper = session.getMapper(DeviceMapper.class);
        double income = 0;
        try {
        	income = mapper.selectTotalIncomeDayByAdmin(name, type);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        	session.close();
        }
        return income;
	}

	public double selectTotalIncomeMonthByAdmin(String name, int type) {
		SqlSession session = DBTools.getSession();
        DeviceMapper mapper = session.getMapper(DeviceMapper.class);
        double income = 0;
        try {
        	income = mapper.selectTotalIncomeMonthByAdmin(name, type);
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

	public List<DeviceOfflineAlarmVO> selectDeviceOfflineAlarmByAdmin(String name, int type, String beginTime,
			String endTime) {
		SqlSession session = DBTools.getSession();
        DeviceMapper mapper = session.getMapper(DeviceMapper.class);
        List<DeviceOfflineAlarmVO> deviceOfflineAlarmVOS = null;
        try {
        	deviceOfflineAlarmVOS = mapper.selectDeviceOfflineAlarmByAdmin(name, type, beginTime, endTime);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        	session.close();
        }
        return deviceOfflineAlarmVOS;
	}

}
