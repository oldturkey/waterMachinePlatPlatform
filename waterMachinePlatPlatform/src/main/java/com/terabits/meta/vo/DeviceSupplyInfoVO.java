package com.terabits.meta.vo;

public class DeviceSupplyInfoVO {
	private String displayid;
	private String location;
	private String time;
	private Double dailyFlow;
	private Double dailyIncome;
	private Double monthFlow;
	private Double monthIncome;
	
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
	
	public String getTime() {
		return this.time;
	}
	
	public void setTime(String time) {
		this.time = time;
	}
	
	public Double getDailyFlow() {
		return this.dailyFlow;
	}
	
	public void setDailyFlow(Double dailyFlow) {
		this.dailyFlow = dailyFlow;
	}
	
	public Double getDailyIncome() {
		return this.dailyIncome;
	}
	
	public void setDailyIncome(Double dailyIncome) {
		this.dailyIncome = dailyIncome;
	}
	
	public Double getMonthFlow() {
		return this.monthFlow;
	}
	
	public void setMonthFlow(Double monthFlow) {
		this.monthFlow = monthFlow;
	}
	
	public Double getMonthIncome() {
		return this.monthIncome;
	}
	
	public void setMonthIncome(Double monthIncome) {
		this.monthIncome = monthIncome;
	}
}
