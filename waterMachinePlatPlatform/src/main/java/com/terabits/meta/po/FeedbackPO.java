package com.terabits.meta.po;


public class FeedbackPO {
    private int id;
    private String nickname;
    private String phone;
    private String email;
    private String feedback;
    private int status;
    private String gmtSolve;
    private String gmtCreate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getGmtSolve() {
        return gmtSolve;
    }

    public void setGmtSolve(String gmtSolve) {
        this.gmtSolve = gmtSolve;
    }

    public String getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(String gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    @Override
    public String toString() {
        return "FeedbackPO{" +
                "id=" + id +
                ", nickname='" + nickname + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", feedback='" + feedback + '\'' +
                ", status=" + status +
                ", gmtSolve='" + gmtSolve + '\'' +
                ", gmtCreate='" + gmtCreate + '\'' +
                '}';
    }
}
