package com.vault.demo.bean;

import java.util.Date;

public class Sign {
    private int id ;
    private int uId; //用户表
    private  int running ;//连续天数
    private int signIntegral ;//签到积分
    private Date signTime ; //签到时间

    @Override
    public String toString() {
        return "Sign{" +
                "id=" + id +
                ", uId=" + uId +
                ", running=" + running +
                ", signIntegral=" + signIntegral +
                ", signTime=" + signTime +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getuId() {
        return uId;
    }

    public void setuId(int uId) {
        this.uId = uId;
    }

    public int getRunning() {
        return running;
    }

    public void setRunning(int running) {
        this.running = running;
    }

    public int getSignIntegral() {
        return signIntegral;
    }

    public void setSignIntegral(int signIntegral) {
        this.signIntegral = signIntegral;
    }

    public Date getSignTime() {
        return signTime;
    }

    public void setSignTime(Date signTime) {
        this.signTime = signTime;
    }
}
