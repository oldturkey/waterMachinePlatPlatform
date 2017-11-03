package com.terabits.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.terabits.meta.po.TerminalPO;
import com.terabits.meta.vo.DeviceOfflineAlarmVO;
import com.terabits.meta.vo.DeviceSupplyInfoVO;
import com.terabits.meta.vo.DeviceSupplyRecordVO;

public interface DeviceMapper {
	/**
	 * 获取用户管理的设备编号
	 * @param name
	 * @param type
	 * @return
	 */
	public List<String> selectDisplayIdByAdmin(@Param("name") String name, @Param("type") int type);
	
	/**
	 * 获取用户管理的设备数量
	 * @param name
	 * @param type
	 * @return
	 */
	public int selectDeviceNumberByAdmin(@Param("name") String name, @Param("type") int type);	
	
	/**
	 * 供水总量
	 * @param name
	 * @param type
	 * @param beginTime
	 * @param endTime
	 * @return
	 */
	public double selectTotalSupplyByAdmin(@Param("name") String name, @Param("type") int type, @Param("beginTime") String beginTime, @Param("endTime") String endTime);
	
	/**
	 * 收入总量
	 * @param name
	 * @param type
	 * @param beginTime
	 * @param endTime
	 * @return
	 */
	public double selectTotalIncomeByAdmin(@Param("name") String name, @Param("type") int type, @Param("beginTime") String beginTime, @Param("endTime") String endTime);
	
	/**
	 * 设备供水记录查询
	 * @param name
	 * @param type
	 * @return
	 */
	public List<DeviceSupplyRecordVO> 
	selectDeviceSupplyRecordByAdmin(@Param("name") String name, @Param("type") int type, @Param("beginTime") String beginTime, @Param("endTime") String endTime);
	
	/**
	 * 设备报警记录查询
	 * @param name
	 * @param type
	 * @param displayid
	 * @param beginTime
	 * @param endTime
	 * @return
	 */
	public List<DeviceOfflineAlarmVO> 
	selectDeviceOfflineAlarmByAdmin(@Param("name") String name, @Param("type") int type, @Param("displayid") String displayid, @Param("beginTime") String beginTime, @Param("endTime") String endTime);
	
	/**
	 * 设备供水信息，包括设备编号，地址，注册时间，日供水量，日收入额，月供水量，月收入额
	 * @param name
	 * @param type
	 * @return
	 */
	public List<DeviceSupplyInfoVO> selectDeviceSupplyInfo(@Param("name") String name, @Param("type") int type, @Param("displayid") String displayid, @Param("location") String location);

	/**
	 * 添加设备
	 * @param terminalPO
	 * @return
	 */
	public int insertTerminal(TerminalPO terminalPO);
	
	/**
	 * 添加设备相关用户
	 * @param params
	 * @return
	 */
	public int addAdminToTerminal(Map<String,Object> params);
	
	/**
	 * 删除设备
	 * @param displayid
	 * @return
	 */
	public int deleteTerminal(@Param("displayid") String displayid);
	
	/**
	 * 查询设备
	 * @param displayid
	 * @return
	 */
	public TerminalPO selectTerminalByDisplayid(@Param("displayid") String displayid);
	
	/**
	 * 查询设备
	 * @param displayid
	 * @return
	 */
	public TerminalPO selectTerminalByImei(@Param("imei") String imei);
	
	/**
	 * 更新webid
	 * @param webid
	 * @param displayid
	 * @return
	 */
	public int updateTerminalWebid(@Param("webid") String webid, @Param("displayid") String displayid);
	
	/**
	 * 查询断线次数
	 * @param name
	 * @param type
	 * @param displayid
	 * @param beginTime
	 * @param endTime
	 * @return
	 */
	public int selectOfflineCounts(@Param("name") String name, @Param("type") int type, @Param("displayid") String displayid, @Param("beginTime") String beginTime, @Param("endTime") String endTime);
}
