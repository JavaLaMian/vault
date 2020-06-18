package com.vault.demo.bean;

import com.gitee.sunchenbin.mybatis.actable.annotation.Column;
import com.gitee.sunchenbin.mybatis.actable.annotation.Table;
import com.gitee.sunchenbin.mybatis.actable.constants.MySqlTypeConstant;

import java.util.Date;
//
@Table(name = "perBid")
public class PerBid {
    @Column(name = "lId",type = MySqlTypeConstant.INT,length = 11,isKey = true,isAutoIncrement = true)
    private int  perBid;

    @Column(name = "borrowTime",type = MySqlTypeConstant.INT)
    private int borrowTime;

    @Column(name = "borrower",type = MySqlTypeConstant.INT)
    private int borrower;

    @Column(name = "perBidName",type = MySqlTypeConstant.VARCHAR,length = 100)
    private String  perBidName;

    @Column(name = "rate",type = MySqlTypeConstant.DOUBLE)
    private float rate;

    @Column(name = "addRate",type = MySqlTypeConstant.DOUBLE)
    private float addRate;

    @Column(name = "inRate",type = MySqlTypeConstant.DOUBLE)
    private float inRate;

    @Column(name = "combination",type = MySqlTypeConstant.DOUBLE)
    private float  combination;

    @Column(name = "transLine",type = MySqlTypeConstant.INT)
    private int  transLine;

    @Column(name = "clockLine",type = MySqlTypeConstant.INT)
    private int clockLine;

    @Column(name = "transfer",type = MySqlTypeConstant.INT)
    private int  transfer;

    @Column(name = "bidStatus",type = MySqlTypeConstant.INT)
    private int bidStatus;

    @Column(name = "startTime",type = MySqlTypeConstant.DATETIME)
    private Date startTime;


    @Override
    public String toString() {
        return "PerBid{" +
                "perBid=" + perBid +
                ", borrowTime=" + borrowTime +
                ", borrower=" + borrower +
                ", perBidName='" + perBidName + '\'' +
                ", rate=" + rate +
                ", addRate=" + addRate +
                ", inRate=" + inRate +
                ", combination=" + combination +
                ", transLine=" + transLine +
                ", clockLine=" + clockLine +
                ", transfer=" + transfer +
                ", bidStatus=" + bidStatus +
                ", startTime=" + startTime +
                '}';
    }

    public int getPerBid() {
        return perBid;
    }

    public void setPerBid(int perBid) {
        this.perBid = perBid;
    }

    public int getBorrowTime() {
        return borrowTime;
    }

    public void setBorrowTime(int borrowTime) {
        this.borrowTime = borrowTime;
    }

    public int getBorrower() {
        return borrower;
    }

    public void setBorrower(int borrower) {
        this.borrower = borrower;
    }

    public String getPerBidName() {
        return perBidName;
    }

    public void setPerBidName(String perBidName) {
        this.perBidName = perBidName;
    }

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    public float getAddRate() {
        return addRate;
    }

    public void setAddRate(float addRate) {
        this.addRate = addRate;
    }

    public float getInRate() {
        return inRate;
    }

    public void setInRate(float inRate) {
        this.inRate = inRate;
    }

    public float getCombination() {
        return combination;
    }

    public void setCombination(float combination) {
        this.combination = combination;
    }

    public int getTransLine() {
        return transLine;
    }

    public void setTransLine(int transLine) {
        this.transLine = transLine;
    }

    public int getClockLine() {
        return clockLine;
    }

    public void setClockLine(int clockLine) {
        this.clockLine = clockLine;
    }

    public int getTransfer() {
        return transfer;
    }

    public void setTransfer(int transfer) {
        this.transfer = transfer;
    }

    public int getBidStatus() {
        return bidStatus;
    }

    public void setBidStatus(int bidStatus) {
        this.bidStatus = bidStatus;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }
}
