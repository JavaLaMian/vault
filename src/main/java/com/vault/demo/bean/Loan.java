package com.vault.demo.bean;

import com.gitee.sunchenbin.mybatis.actable.annotation.Column;
import com.gitee.sunchenbin.mybatis.actable.annotation.Table;
import com.gitee.sunchenbin.mybatis.actable.constants.MySqlTypeConstant;

import java.util.Date;

@Table(name = "loan")
public class Loan {//6月10日16点42

    @Column(name = "lId",type = MySqlTypeConstant.INT,length = 11,isKey = true,isAutoIncrement = true)
    private int lId;

    @Column(name = "loanName",type = MySqlTypeConstant.VARCHAR,length = 100)
    private String loanName;

    @Column(name = "bidType",type = MySqlTypeConstant.INT)
    private int bidType;

    @Column(name = "bidId",type = MySqlTypeConstant.INT)
    private int bidId;

    @Column(name = "uId",type = MySqlTypeConstant.INT)
    private int uId;

    @Column(name = "maxLimit",type = MySqlTypeConstant.DOUBLE)
    private float maxLimit;

    @Column(name = "minLimit",type = MySqlTypeConstant.DOUBLE)
    private float minLimit;

    @Column(name = "loanType",type = MySqlTypeConstant.INT)
    private int loanType;

    @Column(name = "loanWantMoney",type = MySqlTypeConstant.DOUBLE)
    private float loanWantMoney;

    @Column(name = "loanPurpose",type = MySqlTypeConstant.VARCHAR,length = 1000)
    private String loanPurpose;

    @Column(name = "applicationTime",type = MySqlTypeConstant.DATETIME)
    private Date applicationTime;

    @Column(name = "applicationEnd",type = MySqlTypeConstant.DATETIME)
    private Date applicationEnd;

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

    @Column(name = "loanStatue",type = MySqlTypeConstant.INT)
    private int loanStatue;

    @Column(name = "remark",type = MySqlTypeConstant.VARCHAR,length = 2000)
    private String remark;

    @Override
    public String toString() {
        return "Loan{" +
                "lId=" + lId +
                ", loanName='" + loanName + '\'' +
                ", bidType=" + bidType +
                ", bidId=" + bidId +
                ", uId=" + uId +
                ", maxLimit=" + maxLimit +
                ", minLimit=" + minLimit +
                ", loanType=" + loanType +
                ", loanWantMoney=" + loanWantMoney +
                ", loanPurpose='" + loanPurpose + '\'' +
                ", applicationTime=" + applicationTime +
                ", applicationEnd=" + applicationEnd +
                ", guarantees=" + guarantees +
                ", guaranter='" + guaranter + '\'' +
                ", guaCardId='" + guaCardId + '\'' +
                ", review=" + review +
                ", interest=" + interest +
                ", lowLimit=" + lowLimit +
                ", topLimit=" + topLimit +
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

    public int getBidType() {
        return bidType;
    }

    public void setBidType(int bidType) {
        this.bidType = bidType;
    }

    public int getBidId() {
        return bidId;
    }

    public void setBidId(int bidId) {
        this.bidId = bidId;
    }

    public int getuId() {
        return uId;
    }

    public void setuId(int uId) {
        this.uId = uId;
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

    public int getLoanType() {
        return loanType;
    }

    public void setLoanType(int loanType) {
        this.loanType = loanType;
    }

    public float getLoanWantMoney() {
        return loanWantMoney;
    }

    public void setLoanWantMoney(float loanWantMoney) {
        this.loanWantMoney = loanWantMoney;
    }

    public Date getApplicationTime() {
        return applicationTime;
    }

    public String getLoanPurpose() {
        return loanPurpose;
    }

    public void setLoanPurpose(String loanPurpose) {
        this.loanPurpose = loanPurpose;
    }

    public void setApplicationTime(Date applicationTime) {
        this.applicationTime = applicationTime;
    }

    public Date getApplicationEnd() {
        return applicationEnd;
    }

    public void setApplicationEnd(Date applicationEnd) {
        this.applicationEnd = applicationEnd;
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
