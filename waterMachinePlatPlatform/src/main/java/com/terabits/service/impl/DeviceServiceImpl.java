package com.terabits.service.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.terabits.dao.DeviceDAO;
import com.terabits.meta.po.TerminalPO;
import com.terabits.meta.vo.DeviceOfflineAlarmVO;
import com.terabits.meta.vo.DeviceSupplyInfoVO;
import com.terabits.meta.vo.DeviceSupplyRecordVO;
import com.terabits.service.DeviceService;
import com.terabits.utils.PlatformGlobal;
import com.terabits.utils.RequestWebId;

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
		
		//获取系统时间
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar calendar = Calendar.getInstance();
		String endTime = df.format(calendar.getTime());
		df = new SimpleDateFormat("yyyy-MM-dd");
		String dayBeginTime = df.format(calendar.getTime()) + " 00:00:00";
		df = new SimpleDateFormat("yyyy-MM");
		String monthBeginTime = df.format(calendar.getTime()) + "-01 00:00:00";
		
		JSONObject jsonObject = new JSONObject();
		int deviceNumber = deviceDAO.selectDeviceNumberByAdmin(name, type);
		double supplyDay = deviceDAO.selectTotalSupplyByAdmin(name, type, dayBeginTime, endTime);
		double supplyMonth = deviceDAO.selectTotalSupplyByAdmin(name, type, monthBeginTime, endTime);
		double incomeDay = deviceDAO.selectTotalIncomeByAdmin(name, type, dayBeginTime, endTime);
		double incomeMonth = deviceDAO.selectTotalIncomeByAdmin(name, type, monthBeginTime, endTime);
		jsonObject.put("deviceNumber", deviceNumber);
		jsonObject.put("flow-day", supplyDay);
		jsonObject.put("flow-month", supplyMonth);
		jsonObject.put("income-day", incomeDay);
		jsonObject.put("income-month", incomeMonth);
		return jsonObject;
	}

	public JSONArray getDeviceSupplyRecord(String name, int type, String displayid, String location, String phone, String beginTime, String endTime) {
		List<DeviceSupplyRecordVO> deviceSupplyRecordVOS = deviceDAO.selectDeviceSupplyRecordByAdmin(name, type, displayid, location, phone, beginTime, endTime);
		JSONArray record = JSONArray.fromObject(deviceSupplyRecordVOS);
		return record;
	}

	public JSONArray getDeviceOfflineAlarm(String name, int type, String displayid, String beginTime, String endTime) {
		List<DeviceOfflineAlarmVO> deviceOfflineAlarmVOS = deviceDAO.selectDeviceOfflineAlarmByAdmin(name, type, displayid, beginTime, endTime);
		JSONArray alarm = JSONArray.fromObject(deviceOfflineAlarmVOS);
		return alarm;
	}

	public JSONObject getDeviceStatictic(String name, int type) {
		//获取系统时间
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar calendar = Calendar.getInstance();
		String endTime = df.format(calendar.getTime());
		df = new SimpleDateFormat("yyyy-MM-dd");
		String dayBeginTime = df.format(calendar.getTime()) + " 00:00:00";
				
		JSONObject deviceStatistic = new JSONObject();
		int deviceNumber = deviceDAO.selectDeviceNumberByAdmin(name, type);
		double supplyDay = deviceDAO.selectTotalSupplyByAdmin(name, type, dayBeginTime, endTime);
		double supplyHistory = deviceDAO.selectTotalSupplyByAdmin(name, type, null, endTime);
		double incomeDay = deviceDAO.selectTotalIncomeByAdmin(name, type, dayBeginTime, endTime);
		double incomeHistory = deviceDAO.selectTotalIncomeByAdmin(name, type, null, endTime);
		JSONArray dailyData = new JSONArray();
		calendar.add(Calendar.DATE, -30);
		for (int i = 0; i < 30; i++) {
			JSONObject data = new JSONObject();
			calendar.add(Calendar.DATE, 1);
			String tempBeginTime = df.format(calendar.getTime()) + " 00:00:00";
			calendar.add(Calendar.DATE, 1);
			String tempEndTime = df.format(calendar.getTime()) + " 00:00:00";
			calendar.add(Calendar.DATE, -1);
			data.put("time", df.format(calendar.getTime()));
			data.put("flow", deviceDAO.selectTotalSupplyByAdmin(name, type, tempBeginTime, tempEndTime));
			data.put("income", deviceDAO.selectTotalIncomeByAdmin(name, type, tempBeginTime, tempEndTime));
			dailyData.add(data);
		}
		deviceStatistic.put("deviceNumber", deviceNumber);
		deviceStatistic.put("flow-day", supplyDay);
		deviceStatistic.put("flow-history", supplyHistory);
		deviceStatistic.put("income-day", incomeDay);
		deviceStatistic.put("income-history", incomeHistory);
		deviceStatistic.put("dailyData", dailyData);
		return deviceStatistic;
	}

	public JSONArray getDeviceSupplyInfo(String name, int type, String displayid, String location) {
		List<DeviceSupplyInfoVO> deviceSupplyInfoVOS = deviceDAO.selectDeviceSupplyInfo(name, type, displayid, location);
		return JSONArray.fromObject(deviceSupplyInfoVOS);
	}

	public int addTerminal(TerminalPO terminalPO, Map<String, Object> params) {
		try {
			JSONObject result = PlatformGlobal.add(terminalPO.getImei());
			if (result.getInt("error") == 100416) {
				return 4;
			}
			terminalPO.setWebid("wait");
			terminalPO.setDeviceid(result.getString("deviceId"));
		} catch (Exception e) {
			e.printStackTrace();
			return 2;
		}
		int insertResult = deviceDAO.insertTerminal(terminalPO);
		if (insertResult == 200) {
			//webId需要到微信后端请求
			terminalPO = deviceDAO.selectTerminalByImei(terminalPO.getImei());
	        RequestWebId requestWebId = new RequestWebId();
	        String webId = requestWebId.requestWebIdFromWeixinServer(terminalPO.getDisplayid());
	        deviceDAO.updateTerminalWebid(webId, terminalPO.getDisplayid());
			deviceDAO.addAdminToTerminal(params);
			return 1;
		}
		return 2;
	}

	public int deleteTerminal(String displayid) {
		TerminalPO terminalPO = deviceDAO.selectTerminalByDisplayid(displayid);
		if (terminalPO == null) {
			return 2;
		}
		try {
			PlatformGlobal.delete(terminalPO.getDeviceid());
		} catch (Exception e) {
			e.printStackTrace();
			return 3;
		}
		deviceDAO.deleteTerminal(displayid);
		return 1;
	}

	public JSONObject getDeviceConnectInfo(String name, int type, String displayid, String beginTime, String endTime) {
		int offlineDeviceNumber = deviceDAO.selectOfflineCounts(name, type, displayid, beginTime, endTime);
		List<DeviceOfflineAlarmVO> deviceOfflineAlarmVOs = deviceDAO.selectDeviceOfflineAlarmByAdmin(name, type, displayid, beginTime, endTime);
		JSONArray alarmInfo = JSONArray.fromObject(deviceOfflineAlarmVOs);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("offlineDeviceNumber", offlineDeviceNumber);
		jsonObject.put("alarmInfo", alarmInfo);
		return jsonObject;
	}

	public JSONArray getDeviceManageInfo(String name, int type, String displayid) {
		List<TerminalPO> terminalPOs = deviceDAO.selectTerminalByAdmin(name, type, displayid);
		JSONArray jsonArray = JSONArray.fromObject(terminalPOs);
		return jsonArray;
	}

}
