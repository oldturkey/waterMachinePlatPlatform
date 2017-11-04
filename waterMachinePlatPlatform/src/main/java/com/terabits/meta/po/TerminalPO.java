package com.terabits.meta.po;

public class TerminalPO {
	private String imei;
	private String deviceid;
	private String displayid;
	private String webid;
	private Integer state;
	private Double strength;
	private String location;
	private String simid;
	private Double simremain;
	private String registerTime;
	
	public String getImei() {
		return this.imei;
	}
	
	public void setImei(String imei) {
		this.imei = imei;
	}
	
	public String getDeviceid() {
		return this.deviceid;
	}
	
	public void setDeviceid(String deviceid) {
		this.deviceid = deviceid;
	}
	
	public String getDisplayid() {
		return this.displayid;
	}
	
	public void setDisplayid(String displayid) {
		this.displayid = displayid;
	}
	
	public String getWebid() {
		return this.webid;
	}
	
	public void setWebid(String webid) {
		this.webid = webid;
	}
	
	public Integer getState() {
		return this.state;
	}
	
	public void setState(Integer state) {
		this.state = state;
	}
	
	public Double getStrength() {
		return this.strength;
	}
	
	public void setStrength(Double strength) {
		this.strength = strength;
	}
	
	public String getLocation() {
		return this.location;
	}
	
	public void setLocation(String location) {
		this.location = location;
	}
	
	public String getSimid() {
		return this.simid;
	}
	
	public void setSimid(String simid) {
		this.simid = simid;
	}
	
	public Double getSimremain() {
		return this.simremain;
	}
	
	public void setSimremain(Double simremain) {
		this.simremain =simremain;
	}
	
	public String getRegisterTime() {
		return this.registerTime;
	}
	
	public void setRegisterTime(String registerTime) {
		this.registerTime = registerTime;
	}
}
