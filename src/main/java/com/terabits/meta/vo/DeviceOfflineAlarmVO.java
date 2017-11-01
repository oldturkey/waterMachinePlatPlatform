package com.terabits.meta.vo;

public class DeviceOfflineAlarmVO {
	
	private String displayid;
	private String location;
	private String simId;
	private String imei;
	private String lastConnectTime;
	private int offlineTime;
	
	public String getDisplayid() {
		return this.displayid;
	}
	
	public void setDisplayid(String displayid) {
		this.displayid = displayid;
	}
	
	public String getLocation() {
		return this.location;
	}
	
	public void setLocation(String location) {
		this.location = location;
	}
	
	public String getSimId() {
		return this.simId;
	}
	
	public void setSimId(String simId) {
		this.simId = simId;
	}
	
	public String getImei() {
		return this.imei;
	}
	
	public void setImei(String imei) {
		this.imei = imei;
	}
	
	public String getLastConnectTime() {
		return this.lastConnectTime;
	}
	
	public void setLastConnectTime(String lastConnectTime) {
		this.lastConnectTime = lastConnectTime;
	}
	
	public int getOfflineTime() {
		return this.offlineTime;
	}
	
	public void setOfflineTime(int offlineTime) {
		this.offlineTime = offlineTime;
	}
}
