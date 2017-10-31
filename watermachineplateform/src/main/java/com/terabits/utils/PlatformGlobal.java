package com.terabits.utils;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.terabits.constant.HuaweiPlatformGlobal;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpResponse;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/5/27.
 */
public class PlatformGlobal {
    public static int limitDevicesByOneTerminal = 32;


    public static String appId = HuaweiPlatformGlobal.APP_ID;
    public static String secret = HuaweiPlatformGlobal.APP_PASSWORD;

    public static String urlLogin = HuaweiPlatformGlobal.APP_URL + "/iocm/app/sec/v1.1.0/login";
    public static String urlReg = HuaweiPlatformGlobal.APP_URL + "/iocm/app/reg/v1.2.0/devices";
    public static String urlSetDeviceInfo = HuaweiPlatformGlobal.APP_URL + "/iocm/app/dm/v1.2.0/devices/";
    public static String urlDelete = HuaweiPlatformGlobal.APP_URL + "/iocm/app/dm/v1.1.0/devices/";
    public static String urlPostAsynCmd = HuaweiPlatformGlobal.APP_URL + "/iocm/app/cmd/v1.2.0/devices/%s/commands";

    public static String manufacturerId= "terabits";
    public static String manufacturerName = "terabits";
    public static String deviceType = "ElectricityDevice";
    public static String protocolType = "CoAP";
    public static String model = "001";
    public static String serviceId = "ElectricityService";
    public static String callbackUrl = "http://112.124.6.31/watermachine/receive/data";
    public static int expireTime = 0;



    @SuppressWarnings("unchecked")
    public static String login(HttpsUtil httpsUtil) throws Exception {

        Map<String, String> paramLogin = new HashMap<String, String>();
        paramLogin.put("appId", appId);
        paramLogin.put("secret", secret);

        String bodyLogin = httpsUtil.doPostFormUrlEncodedForString(urlLogin,
                paramLogin);
        System.out.println(bodyLogin);

        Map<String, String> data = new HashMap<String, String>();
        data = JsonUtil.jsonString2SimpleObj(bodyLogin, data.getClass());
        String accessToken = data.get("accessToken");
        return accessToken;
    }

    //模拟透传的模式，下发命令用这个方法
    public static String command(byte[] data, String terminalId) throws Exception{
        String command = Base64.encodeBase64String(data);
        //terminalId = "fea083bc-33af-4c3c-b382-d76e18363292";
        HttpsUtil httpsUtil = new HttpsUtil();
        httpsUtil.initSSLConfigForTwoWay();

        String accessToken = login(httpsUtil);
        String tempUrl = String.format(urlPostAsynCmd, terminalId);

        String method = "START";
        ObjectNode paras = JsonUtil.convertObject2ObjectNode("{\"rawData\":\"" + command +"\"}");

        Map<String, Object> paramCommand = new HashMap<String, Object>();
        paramCommand.put("serviceId", "RawData");
        paramCommand.put("method", method);
        paramCommand.put("paras", paras);

        Map<String, Object> paramPostAsynCmd = new HashMap<String, Object>();
        paramPostAsynCmd.put("command", paramCommand);
        paramPostAsynCmd.put("callbackUrl", callbackUrl);
        paramPostAsynCmd.put("expireTime",expireTime);

        String jsonRequest = JsonUtil.jsonObj2Sting(paramPostAsynCmd);

        Map<String, String> header = new HashMap<String, String>();
        header.put("app_key", appId);
        header.put("Authorization", "Bearer " + accessToken);

        HttpResponse httpResponse = httpsUtil.doPostJson(tempUrl, header, jsonRequest);

        String responseBody = httpsUtil.getHttpResponseBody(httpResponse);

        return responseBody;
    }

    public static String command(String terminalId, String method, ObjectNode paras) throws Exception{

        HttpsUtil httpsUtil = new HttpsUtil();
        httpsUtil.initSSLConfigForTwoWay();

        String accessToken = login(httpsUtil);
        String tempUrl = String.format(urlPostAsynCmd, terminalId);

        Map<String, Object> paramCommand = new HashMap<String, Object>();
        paramCommand.put("serviceId", serviceId);
        paramCommand.put("method", method);
        paramCommand.put("paras", paras);

        Map<String, Object> paramPostAsynCmd = new HashMap<String, Object>();
        paramPostAsynCmd.put("command", paramCommand);
        paramPostAsynCmd.put("callbackUrl", callbackUrl);
        paramPostAsynCmd.put("expireTime",expireTime);

        String jsonRequest = JsonUtil.jsonObj2Sting(paramPostAsynCmd);

        Map<String, String> header = new HashMap<String, String>();
        header.put("app_key", appId);
        header.put("Authorization", "Bearer " + accessToken);

        HttpResponse httpResponse = httpsUtil.doPostJson(tempUrl, header, jsonRequest);

        String responseBody = httpsUtil.getHttpResponseBody(httpResponse);

        return responseBody;
    }
}

