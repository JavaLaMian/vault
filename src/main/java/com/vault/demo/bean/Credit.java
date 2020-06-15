package com.vault.demo.bean;

import com.gitee.sunchenbin.mybatis.actable.annotation.Column;
import com.gitee.sunchenbin.mybatis.actable.annotation.Table;
import com.gitee.sunchenbin.mybatis.actable.constants.MySqlTypeConstant;

import java.util.Date;

@Table(name = "credit")
public class Credit {//6月10日15点31

    @Column(name = "creId",type = MySqlTypeConstant.INT,length = 11,isKey = true,isAutoIncrement = true)
    private int creId;
    @Column(name = "uId",type = MySqlTypeConstant.INT)
    private int uId;
    @Column(name = "depart",type = MySqlTypeConstant.VARCHAR,length = 100)
    private String depart;

    private String wages;

    private String name;

    @Column(name = "identity",type = MySqlTypeConstant.VARCHAR,length = 100)
    private String identity;
    @Column(name = "hId",type = MySqlTypeConstant.INT)
    private int hId;

    private int cId;

    @Column(name = "funds",type = MySqlTypeConstant.VARCHAR,length = 100)
    private String funds;

    private String positiveIDPhoto;

    private String negativeIDPhoto;

    private String creditLV;

    private Date creditUpdateTime;

    @Override
    public String toString() {
        return "Credit{" +
                "creId=" + creId +
                ", uId=" + uId +
                ", depart='" + depart + '\'' +
                ", wages='" + wages + '\'' +
                ", name='" + name + '\'' +
                ", identity='" + identity + '\'' +
                ", hId=" + hId +
                ", cId=" + cId +
                ", funds='" + funds + '\'' +
                ", positiveIDPhoto='" + positiveIDPhoto + '\'' +
                ", negativeIDPhoto='" + negativeIDPhoto + '\'' +
                ", creditLV='" + creditLV + '\'' +
                ", creditUpdateTime=" + creditUpdateTime +
                '}';
    }

    public int getCreId() {
        return creId;
    }

    public void setCreId(int creId) {
        this.creId = creId;
    }

    public int getuId() {
        return uId;
    }

    public void setuId(int uId) {
        this.uId = uId;
    }

    public String getDepart() {
        return depart;
    }

    public void setDepart(String depart) {
        this.depart = depart;
    }

    public String getWages() {
        return wages;
    }

    public void setWages(String wages) {
        this.wages = wages;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public int gethId() {
        return hId;
    }

    public void sethId(int hId) {
        this.hId = hId;
    }

    public int getcId() {
        return cId;
    }

    public void setcId(int cId) {
        this.cId = cId;
    }

    public String getFunds() {
        return funds;
    }

    public void setFunds(String funds) {
        this.funds = funds;
    }

    public String getPositiveIDPhoto() {
        return positiveIDPhoto;
    }

    public void setPositiveIDPhoto(String positiveIDPhoto) {
        this.positiveIDPhoto = positiveIDPhoto;
    }

    public String getNegativeIDPhoto() {
        return negativeIDPhoto;
    }

    public void setNegativeIDPhoto(String negativeIDPhoto) {
        this.negativeIDPhoto = negativeIDPhoto;
    }

    public String getCreditLV() {
        return creditLV;
    }

    public void setCreditLV(String creditLV) {
        this.creditLV = creditLV;
    }

    public Date getCreditUpdateTime() {
        return creditUpdateTime;
    }

    public void setCreditUpdateTime(Date creditUpdateTime) {
        this.creditUpdateTime = creditUpdateTime;
    }
}
