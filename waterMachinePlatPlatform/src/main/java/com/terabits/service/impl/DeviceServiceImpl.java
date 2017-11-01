package com.terabits.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.terabits.dao.DeviceDAO;
import com.terabits.meta.vo.DeviceOfflineAlarmVO;
import com.terabits.meta.vo.DeviceSupplyRecordVO;
import com.terabits.service.DeviceService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Service
public class DeviceServiceImpl implements DeviceService {
	
	@Autowired
	private DeviceDAO deviceDAO;

	public List<String> getDisplayIdByAdmin(String name, int type) {
		return deviceDAO.selectDisplayIdByAdmin(name, type);
	}

	public JSONObject getDeviceInfo(String name, int type) {
		JSONObject jsonObject = new JSONObject();
		int deviceNumber = deviceDAO.selectDeviceNumberByAdmin(name, type);
		double supplyDay = deviceDAO.selectTotalSupplyDayByAdmin(name, type);
		double supplyMonth = deviceDAO.selectTotalSupplyMonthByAdmin(name, type);
		double incomeDay = deviceDAO.selectTotalIncomeDayByAdmin(name, type);
		double incomeMonth = deviceDAO.selectTotalIncomeMonthByAdmin(name, type);
		jsonObject.put("deviceNumber", deviceNumber);
		jsonObject.put("flow-day", supplyDay);
		jsonObject.put("flow-month", supplyMonth);
		jsonObject.put("income-day", incomeDay);
		jsonObject.put("income-month", incomeMonth);
		return jsonObject;
	}

	public JSONArray getDeviceSupplyRecord(String name, int type, String beginTime, String endTime) {
		List<DeviceSupplyRecordVO> deviceSupplyRecordVOS = deviceDAO.selectDeviceSupplyRecordByAdmin(name, type, beginTime, endTime);
		JSONArray record = JSONArray.fromObject(deviceSupplyRecordVOS);
		return record;
	}

	public JSONArray getDeviceOfflineAlarm(String name, int type, String beginTime, String endTime) {
		List<DeviceOfflineAlarmVO> deviceOfflineAlarmVOS = deviceDAO.selectDeviceOfflineAlarmByAdmin(name, type, beginTime, endTime);
		JSONArray alarm = JSONArray.fromObject(deviceOfflineAlarmVOS);
		return alarm;
	}

}
