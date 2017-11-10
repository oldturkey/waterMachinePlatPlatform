package com.terabits.service;

import com.terabits.meta.po.AdminPO;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public interface AdminService {
	public JSONObject login(String name, String password);
	public boolean authConfirm(int type, String url);
	
	public int checkEmailAndName(String name,String email);
	public JSONArray getAllAccount();
	public JSONArray getSingleAcount(AdminPO adminPO);
}
