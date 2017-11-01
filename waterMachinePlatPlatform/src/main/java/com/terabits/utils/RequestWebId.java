package com.terabits.utils;

import net.sf.json.JSONObject;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.http.HttpEntity;
import org.omg.CORBA.Request;

import java.nio.charset.Charset;


public class RequestWebId {
    private static final String POST_ADDRESS = "http://119.23.210.52/watermachine/getwebid";

    public String requestWebIdFromWeixinServer(String displayId) {

        String webId = "initFail";
        HttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(POST_ADDRESS);
        JSONObject displayIdData = new JSONObject();
        displayIdData.put("displayId",displayId);
        StringEntity entity = new StringEntity(displayIdData.toString(), Charset.forName("UTF-8"));
        entity.setContentEncoding("UTF-8");
        entity.setContentType("application/json");
        httpPost.setEntity(entity);
        try {
            HttpResponse httpResponse = httpClient.execute(httpPost);
            if (httpResponse.getStatusLine().getStatusCode() == 200) {
                System.out.println("请求webId成功");
                HttpEntity httpEntity = httpResponse.getEntity();
                String response = EntityUtils.toString(httpEntity);
                System.out.println(response);
                JSONObject json = JSONObject.fromObject(response);
                webId = json.getString("webId");
            }
            return webId;
        } catch (Exception e) {
            e.printStackTrace();
            return webId;
        }
    }
//
//    public static void main(String[] args) {
//        RequestWebId r = new RequestWebId();
//        r.requestWebIdFromWeixinServer("1013");
//    }

}
