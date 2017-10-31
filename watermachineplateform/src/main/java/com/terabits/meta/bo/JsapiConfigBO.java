package com.terabits.meta.bo;

/**
 * Created by Administrator on 2017/6/12.
 */
public class JsapiConfigBO {
    private String appId;
    private String timestamp;
    private String nonce;
    private String signature;
    private String title;
    private String link;
    private String signType;
    private String packageName;

    public JsapiConfigBO(){
        signType = "MD5";
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getNonce() {
        return nonce;
    }

    public void setNonce(String nonce) {
        this.nonce = nonce;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getSignType() {
        return signType;
    }

    public void setSignType(String signType) {
        this.signType = signType;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    @Override
    public String toString() {
        return "JsapiConfigBO{" +
                "appId='" + appId + '\'' +
                ", timestamp='" + timestamp + '\'' +
                ", nonce='" + nonce + '\'' +
                ", signature='" + signature + '\'' +
                ", title='" + title + '\'' +
                ", link='" + link + '\'' +
                ", signType='" + signType + '\'' +
                ", packageName='" + packageName + '\'' +
                '}';
    }
}
