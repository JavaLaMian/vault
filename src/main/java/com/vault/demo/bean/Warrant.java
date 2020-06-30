package com.vault.demo.bean;

import java.util.Date;

public class Warrant {
    private int wId;
    private int uId;
    private String wName;
    private String wCardId;
    private String wPositiveIDPhoto;
    private String wNegativeIDPhoto;
    private String wContractPhoto;
    private String remark;
    private Date registerTime;
    private int status;

    @Override
    public String toString() {
        return "Warrant{" +
                "wId=" + wId +
                ", uId=" + uId +
                ", wName='" + wName + '\'' +
                ", wCardId='" + wCardId + '\'' +
                ", wPositiveIDPhoto='" + wPositiveIDPhoto + '\'' +
                ", wNegativeIDPhoto='" + wNegativeIDPhoto + '\'' +
                ", wContractPhoto='" + wContractPhoto + '\'' +
                ", remark='" + remark + '\'' +
                ", registerTime=" + registerTime +
                ", status=" + status +
                '}';
    }

    public int getwId() {
        return wId;
    }

    public void setwId(int wId) {
        this.wId = wId;
    }

    public int getuId() {
        return uId;
    }

    public void setuId(int uId) {
        this.uId = uId;
    }

    public String getwName() {
        return wName;
    }

    public void setwName(String wName) {
        this.wName = wName;
    }

    public String getwCardId() {
        return wCardId;
    }

    public void setwCardId(String wCardId) {
        this.wCardId = wCardId;
    }

    public String getwPositiveIDPhoto() {
        return wPositiveIDPhoto;
    }

    public void setwPositiveIDPhoto(String wPositiveIDPhoto) {
        this.wPositiveIDPhoto = wPositiveIDPhoto;
    }

    public String getwNegativeIDPhoto() {
        return wNegativeIDPhoto;
    }

    public void setwNegativeIDPhoto(String wNegativeIDPhoto) {
        this.wNegativeIDPhoto = wNegativeIDPhoto;
    }

    public String getwContractPhoto() {
        return wContractPhoto;
    }

    public void setwContractPhoto(String wContractPhoto) {
        this.wContractPhoto = wContractPhoto;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
