package com.vault.demo.bean;

import java.util.Date;

//我的积分表
public class MyIntegral {
    private int Id ;
    private int uId ;//关联用户表
    private  int iId ;//关联积分商品表
    private  int change ;//变动积分
    private String changeType ;
    private int  total ;
    private Date conversionTime ;//兑换时间

    @Override
    public String toString() {
        return "MyIntegral{" +
                "Id=" + Id +
                ", uId=" + uId +
                ", iId=" + iId +
                ", change=" + change +
                ", changeType='" + changeType + '\'' +
                ", total=" + total +
                ", conversion=" + conversionTime +
                '}';
    }

    public Date getConversionTime() {
        return conversionTime;
    }

    public void setConversionTime(Date conversionTime) {
        this.conversionTime = conversionTime;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getuId() {
        return uId;
    }

    public void setuId(int uId) {
        this.uId = uId;
    }

    public int getiId() {
        return iId;
    }

    public void setiId(int iId) {
        this.iId = iId;
    }

    public int getChange() {
        return change;
    }

    public void setChange(int change) {
        this.change = change;
    }

    public String getChangeType() {
        return changeType;
    }

    public void setChangeType(String changeType) {
        this.changeType = changeType;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
