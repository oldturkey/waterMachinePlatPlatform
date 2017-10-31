package com.terabits.service;

import net.sf.json.JSONObject;

public interface AdminService {
	public JSONObject login(String name, String password);
	public boolean authConfirm(int type, String url);
}
