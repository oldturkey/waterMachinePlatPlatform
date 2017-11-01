package com.terabits.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.terabits.meta.vo.DeviceOfflineAlarmVO;
import com.terabits.meta.vo.DeviceSupplyRecordVO;

public interface DeviceDAO {
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
	 * 当日供水总量
	 * @param name
	 * @param type
	 * @return
	 */
	public double selectTotalSupplyDayByAdmin(@Param("name") String name, @Param("type") int type);
	
	/**
	 * 当月供水总量
	 * @param name
	 * @param type
	 * @return
	 */
	public double selectTotalSupplyMonthByAdmin(@Param("name") String name, @Param("type") int type);
	
	/**
	 * 当日收入总量
	 * @param name
	 * @param type
	 * @return
	 */
	public double selectTotalIncomeDayByAdmin(@Param("name") String name, @Param("type") int type);
	
	/**
	 * 当月收入总量
	 * @param name
	 * @param type
	 * @return
	 */
	public double selectTotalIncomeMonthByAdmin(@Param("name") String name, @Param("type") int type);
	
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
	 * @param beginTime
	 * @param endTime
	 * @return
	 */
	public List<DeviceOfflineAlarmVO> 
	selectDeviceOfflineAlarmByAdmin(@Param("name") String name, @Param("type") int type, @Param("beginTime") String beginTime, @Param("endTime") String endTime);

}
