package com.terabits.utils;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.terabits.constant.Constant;
import com.terabits.constant.HuaweiPlatformGlobal;
//import jdk.nashorn.api.scripting.ScriptObjectMirror;
import net.sf.json.JSONObject;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpResponse;
import org.apache.http.entity.StringEntity;
import org.springframework.beans.factory.annotation.Autowired;

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

    public static String manufacturerId = "terabits";
    public static String manufacturerName = "terabits";
    public static String deviceType = "ElectricityDevice";
    public static String protocolType = "CoAP";
    public static String model = "001";
    public static String serviceId = "ElectricityService";
    public static String callbackUrl = "http://112.124.6.31/watermachine/receive/data";
    public static int expireTime = 0;


    @SuppressWarnings("unchecked")
    //获取accesstoken
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

    public static JSONObject add(String imei) throws Exception {

        // Two-Way Authentication
        HttpsUtil httpsUtil = new HttpsUtil();
        httpsUtil.initSSLConfigForTwoWay();

        // Authentication，get token
        String accessToken = login(httpsUtil);

        //Please make sure that the following parameter values have been modified in the Constant file.
        String appId = Constant.APP_ID;
        String urlReg = Constant.REGISTER_DEVICE;

        //please replace the verifyCode and nodeId and timeout, when you use the demo.
        System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
        System.out.println(imei);
        System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
        String verifyCode = imei;
        String nodeId = imei;
        String EndUserId = "currentuser"; // please replace the currentuser, when you use the demo.

        Map<String, Object> paramReg = new HashMap<String, Object>();
        paramReg.put("verifyCode", verifyCode.toUpperCase());
        paramReg.put("nodeId", nodeId.toUpperCase());
        paramReg.put("endUserId", EndUserId);
        paramReg.put("timeout", 0);

        String jsonRequest = JsonUtil.jsonObj2Sting(paramReg);

        Map<String, String> header = new HashMap<String, String>();
        header.put("app_key", appId);
        header.put("Authorization", "Bearer " + accessToken);

        String responseReg = httpsUtil.doPostJsonForString(urlReg, header, jsonRequest);
        Map<String, String> data = new HashMap<String, String>();
        data = JsonUtil.jsonString2SimpleObj(responseReg, data.getClass());
        
        if (data.get("error_code").equals("100416")) {
        	//已注册该设备
        	JSONObject jsonObject = new JSONObject();
            jsonObject.put("error", 100416);
            return jsonObject;
        }
        String deviceId = data.get("deviceId");

        System.out.println("RegisterDirectlyConnectedDevice, response content:");

        System.out.println(deviceId);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("error", 0);
        jsonObject.put("token", accessToken);
        jsonObject.put("deviceId", deviceId);

        //添加设备信息
        setInfoByDeviceId(deviceId, accessToken);

        return jsonObject;
    }

    public static void setInfoByDeviceId(String externDeviceId, String accessToken) throws Exception {

        HttpsUtil httpsUtil = new HttpsUtil();
        httpsUtil.initSSLConfigForTwoWay(); //Two-Way Authentication
//        String accessToken = login(httpsUtil); //Authentication，get token
        String appId = HuaweiPlatformGlobal.APP_ID;    //please replace the appId, when you use the demo.
        String deviceId = externDeviceId;  //please replace the deviceId, when you use the demo.
        String urlSetDeviceInfo = HuaweiPlatformGlobal.APP_URL + "/iocm/app/dm/v1.2.0/devices/" + deviceId;  //please replace the IP and Port, when you use the demo.
        String manufacturerId = "terabits";  //  please replace the manufacturerId, when you use the demo.
        String manufacturerName = "terabits";  //  please replace the manufacturerName, when you use the demo.
        String deviceType = "ElectricityMeter";  //please replace the deviceType, when you use the demo.
        String protocolType = "CoAP";
        String model = "001";  // please replace the model, when you use the demo.

        Map<String, Object> paramSetDeviceInfo = new HashMap<String, Object>();
        paramSetDeviceInfo.put("manufacturerId", manufacturerId);
        paramSetDeviceInfo.put("manufacturerName", manufacturerName);
        paramSetDeviceInfo.put("deviceType", deviceType);
        paramSetDeviceInfo.put("protocolType", protocolType);
        paramSetDeviceInfo.put("model", model);


        String jsonRequest = JsonUtil.jsonObj2Sting(paramSetDeviceInfo);

        Map<String, String> header = new HashMap<String, String>();
        header.put("app_key", appId);
        header.put("Authorization", "Bearer " + accessToken);

        String bodyModifyDeviceInfo = httpsUtil.doPutJsonForString(urlSetDeviceInfo, header, jsonRequest);
        System.out.println("bodyModifyDeviceInfo:  ");
        System.out.println(bodyModifyDeviceInfo);
    }

    public static String delete(String deviceId) throws Exception {

        // Two-Way Authentication
        HttpsUtil httpsUtil = new HttpsUtil();
        httpsUtil.initSSLConfigForTwoWay();

        // Authentication，get token
        String accessToken = login(httpsUtil);


        //Please make sure that the following parameter values have been modified in the Constant file.
        String appId = HuaweiPlatformGlobal.APP_ID;

        //please replace the deviceId, when you use the demo.
        String urlDelete = Constant.DELETE_DEVICE + "/" + deviceId;

        Map<String, String> header = new HashMap<String, String>();
        header.put("app_key", appId);
        header.put("Authorization", "Bearer " + accessToken);

        //V1.4更新
        StreamClosedHttpResponse responseDelete = httpsUtil.doDeleteGetStatusLine(urlDelete, header);

        System.out.println("DeleteDirectlyConnectedDevice, response content:");
        System.out.print(responseDelete.getStatusLine());
        System.out.println(responseDelete.getContent());
        System.out.println();
        return accessToken;

    }

    //模拟透传的模式，下发命令用这个方法
    public static String command(byte[] data, String terminalId) throws Exception {
        String command = Base64.encodeBase64String(data);
        //terminalId = "fea083bc-33af-4c3c-b382-d76e18363292";
        HttpsUtil httpsUtil = new HttpsUtil();
        httpsUtil.initSSLConfigForTwoWay();
        //****************************tempUrl******************************************
        String tempUrl = String.format(urlPostAsynCmd, terminalId);
        //****************************jsonRequest***************************************
        String method = "START";
        ObjectNode paras = JsonUtil.convertObject2ObjectNode("{\"rawData\":\"" + command + "\"}");
        Map<String, Object> paramCommand = new HashMap<String, Object>();
        paramCommand.put("serviceId", "RawData");
        paramCommand.put("method", method);
        paramCommand.put("paras", paras);


        Map<String, Object> paramPostAsynCmd = new HashMap<String, Object>();
        paramPostAsynCmd.put("command", paramCommand);
        paramPostAsynCmd.put("callbackUrl", callbackUrl);
        paramPostAsynCmd.put("expireTime", expireTime);
        String jsonRequest = JsonUtil.jsonObj2Sting(paramPostAsynCmd);
        //*******************************header*******************************************
        String accessToken = login(httpsUtil);
        Map<String, String> header = new HashMap<String, String>();
        header.put("app_key", appId);
        header.put("Authorization", "Bearer " + accessToken);

        HttpResponse httpResponse = httpsUtil.doPostJson(tempUrl, header, jsonRequest);

        String responseBody = httpsUtil.getHttpResponseBody(httpResponse);

        return responseBody;
    }

    public static String command(String terminalId, String method, ObjectNode paras) throws Exception {

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
        paramPostAsynCmd.put("expireTime", expireTime);

        String jsonRequest = JsonUtil.jsonObj2Sting(paramPostAsynCmd);

        Map<String, String> header = new HashMap<String, String>();
        header.put("app_key", appId);
        header.put("Authorization", "Bearer " + accessToken);

        HttpResponse httpResponse = httpsUtil.doPostJson(tempUrl, header, jsonRequest);

        String responseBody = httpsUtil.getHttpResponseBody(httpResponse);

        return responseBody;
    }
}

