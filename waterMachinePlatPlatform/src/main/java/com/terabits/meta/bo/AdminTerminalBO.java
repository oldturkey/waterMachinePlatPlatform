package com.terabits.meta.bo;

public class AdminTerminalBO {
	private String adminName;
	private String terminalImei;
	
	public AdminTerminalBO() {
		
	}
	
	public AdminTerminalBO(String adminName, String terminalImei) {
		this.adminName = adminName;
		this.terminalImei = terminalImei;
	}
	
	public String getAdminName() {
		return this.adminName;
	}
	
	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}
	
	public String getTerminalImei() {
		return this.terminalImei;
	}
	
	public void setTerminalImei(String terminalImei) {
		this.terminalImei = terminalImei;
	}
}
