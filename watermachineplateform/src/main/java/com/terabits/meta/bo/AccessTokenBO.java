package com.terabits.meta.bo;

/**
 * Created by Administrator on 2017/6/5.
 */
public class AccessTokenBO {
    // 获取到的凭证
    private String token;
    // 凭证有效时间，单位：秒
    private int expiresIn;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(int expiresIn) {
        this.expiresIn = expiresIn;
    }

    @Override
    public String toString() {
        return "AccessTokenBO{" +
                "token='" + token + '\'' +
                ", expiresIn=" + expiresIn +
                '}';
    }
}
