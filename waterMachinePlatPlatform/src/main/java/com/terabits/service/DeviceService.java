package com.terabits.service;

import java.util.List;
import java.util.Map;

import com.terabits.meta.po.TerminalPO;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public interface DeviceService {
	
	/**
	 * 获取用户可管理的设备id
	 * @param name
	 * @param type
	 * @return
	 */
	public List<String> getDisplayIdByAdmin(String name, int type);
	
	/**
	 * 获取设备信息，包括设备数，当日（月）供水总量，当日（月）收入
	 * @param name 用户账号名
	 * @param type 用户类型
	 * @return
	 */
	public JSONObject getDeviceInfo(String name, int type);
	
	/**
	 * 获取设备供水信息，包括设备编号，设备位置，取水金额，取水量，取水时间
	 * @param name
	 * @param type
	 * @param beginTime
	 * @param endTime
	 * @return
	 */
	public JSONArray getDeviceSupplyRecord(String name, int type, String beginTime, String endTime);
	
	/**
	 * 获取设备断线报警信息，包括设备编号，设备位置，sim卡号，imei卡号，最后连接时刻，断线时长
	 * @param name
	 * @param type
	 * @param displayid
	 * @param beginTime
	 * @param endTime
	 * @return
	 */
	public JSONArray getDeviceOfflineAlarm(String name, int type, String displayid, String beginTime, String endTime);
	
	/**
	 * 获取设备统计信息，包括设备数量，当日供水总量，历史供水总量，当日收入额，历史收入额，30日折线数据（日供水量和日收入额）
	 * @param name
	 * @param type
	 * @return
	 */
	public JSONObject getDeviceStatictic(String name, int type);
	
	/**
	 * 设备供水信息，包括设备编号，地址，注册时间，日供水量，日收入额，月供水量，月收入额
	 * @param name
	 * @param type
	 * @return
	 */
	public JSONArray getDeviceSupplyInfo(String name, int type, String displayid, String location);
	
	/**
	 * 添加设备
	 * @param terminalPO
	 * @param params 存储设备-用户表
	 * @return
	 */
	public int addTerminal(TerminalPO terminalPO, Map<String, Object> params);
	
	/**
	 * 删除设备
	 * @param displayid
	 * @return
	 */
	public int deleteTerminal(String displayid);
	
	/**
	 * 设备连接信息，包括设备断线次数，设备连接告警信息表
	 * @param name
	 * @param type
	 * @param displayid
	 * @param beginTime
	 * @param endTime
	 * @return
	 */
	public JSONObject getDeviceConnectInfo(String name, int type, String displayid, String beginTime, String endTime);
}
