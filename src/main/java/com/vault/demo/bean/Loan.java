package com.vault.demo.bean;

import com.gitee.sunchenbin.mybatis.actable.annotation.Column;
import com.gitee.sunchenbin.mybatis.actable.annotation.Table;
import com.gitee.sunchenbin.mybatis.actable.constants.MySqlTypeConstant;

import java.util.Date;

@Table(name = "loan")
public class Loan {//6月10日16点42
    private static final int IN = 1;
    private static final int CLOSE = 0;

    @Column(name = "lId",type = MySqlTypeConstant.INT,length = 11,isKey = true,isAutoIncrement = true)
    private int lId;

    @Column(name = "loanName",type = MySqlTypeConstant.VARCHAR,length = 100)
    private String loanName;

    @Column(name = "maxLimit",type = MySqlTypeConstant.DOUBLE)
    private float maxLimit;

    @Column(name = "minLimit",type = MySqlTypeConstant.DOUBLE)
    private float minLimit;

    @Column(name = "maxAge",type = MySqlTypeConstant.INT)
    private int maxAge;

    @Column(name = "minAge",type = MySqlTypeConstant.INT)
    private int minAge;

    @Column(name = "creditLv",type = MySqlTypeConstant.INT)
    private int creditLv;

    @Column(name = "guarantees",type = MySqlTypeConstant.INT)
    private int guarantees;

    @Column(name = "guaranter",type = MySqlTypeConstant.VARCHAR,length = 50)
    private String guaranter;

    @Column(name = "guaCardId",type = MySqlTypeConstant.VARCHAR,length = 200)
    private String guaCardId;

    @Column(name = "review",type = MySqlTypeConstant.INT)
    private int review;

    @Column(name = "interest",type = MySqlTypeConstant.DOUBLE)
    private float interest;

    @Column(name = "lowLimit",type = MySqlTypeConstant.INT)
    private int lowLimit;

    @Column(name = "topLimit",type = MySqlTypeConstant.INT)
    private int topLimit;

    @Column(name = "online",type = MySqlTypeConstant.DATETIME)
    private Date online;

    @Column(name = "offline",type = MySqlTypeConstant.DATETIME)
    private Date offline;

    @Column(name = "loanStatue",type = MySqlTypeConstant.INT)
    private int loanStatue;

    @Column(name = "remark",type = MySqlTypeConstant.VARCHAR,length = 2000)
    private String remark;

    @Override
    public String toString() {
        return "Loan{" +
                "lId=" + lId +
                ", loanName='" + loanName + '\'' +
                ", maxLimit=" + maxLimit +
                ", minLimit=" + minLimit +
                ", maxAge=" + maxAge +
                ", minAge=" + minAge +
                ", creditLv=" + creditLv +
                ", guarantees=" + guarantees +
                ", guaranter='" + guaranter + '\'' +
                ", guaCardId='" + guaCardId + '\'' +
                ", review=" + review +
                ", interest=" + interest +
                ", lowLimit=" + lowLimit +
                ", topLimit=" + topLimit +
                ", online=" + online +
                ", offline=" + offline +
                ", loanStatue=" + loanStatue +
                ", remark='" + remark + '\'' +
                '}';
    }

    public int getlId() {
        return lId;
    }

    public void setlId(int lId) {
        this.lId = lId;
    }

    public String getLoanName() {
        return loanName;
    }

    public void setLoanName(String loanName) {
        this.loanName = loanName;
    }

    public float getMaxLimit() {
        return maxLimit;
    }

    public void setMaxLimit(float maxLimit) {
        this.maxLimit = maxLimit;
    }

    public float getMinLimit() {
        return minLimit;
    }

    public void setMinLimit(float minLimit) {
        this.minLimit = minLimit;
    }

    public int getMaxAge() {
        return maxAge;
    }

    public void setMaxAge(int maxAge) {
        this.maxAge = maxAge;
    }

    public int getMinAge() {
        return minAge;
    }

    public void setMinAge(int minAge) {
        this.minAge = minAge;
    }

    public int getCreditLv() {
        return creditLv;
    }

    public void setCreditLv(int creditLv) {
        this.creditLv = creditLv;
    }

    public int getGuarantees() {
        return guarantees;
    }

    public void setGuarantees(int guarantees) {
        this.guarantees = guarantees;
    }

    public String getGuaranter() {
        return guaranter;
    }

    public void setGuaranter(String guaranter) {
        this.guaranter = guaranter;
    }

    public String getGuaCardId() {
        return guaCardId;
    }

    public void setGuaCardId(String guaCardId) {
        this.guaCardId = guaCardId;
    }

    public int getReview() {
        return review;
    }

    public void setReview(int review) {
        this.review = review;
    }

    public float getInterest() {
        return interest;
    }

    public void setInterest(float interest) {
        this.interest = interest;
    }

    public int getLowLimit() {
        return lowLimit;
    }

    public void setLowLimit(int lowLimit) {
        this.lowLimit = lowLimit;
    }

    public int getTopLimit() {
        return topLimit;
    }

    public void setTopLimit(int topLimit) {
        this.topLimit = topLimit;
    }

    public Date getOnline() {
        return online;
    }

    public void setOnline(Date online) {
        this.online = online;
    }

    public Date getOffline() {
        return offline;
    }

    public void setOffline(Date offline) {
        this.offline = offline;
    }

    public int getLoanStatue() {
        return loanStatue;
    }

    public void setLoanStatue(int loanStatue) {
        this.loanStatue = loanStatue;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
