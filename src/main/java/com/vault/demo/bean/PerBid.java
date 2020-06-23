package com.vault.demo.bean;

import com.gitee.sunchenbin.mybatis.actable.annotation.Table;

import java.util.Date;
//
@Table(name = "perBid")
public class PerBid {
       private int  perBid;
        private int bidStatus;
       private int borrower;
       private String  perBidName;
       private  float  inRate ;//投资利率
       private float rate;
       private float addRate;
       private float  combination;
       private String  transLine;
       private String clockLine;
       private int  transfer;
       private Date startTime;
       private float enquiry;//可以投资余额
       private float schedul;//进度
       private Date expire;//到期时间
       private int borrowTime;

    @Override
    public String toString() {
        return "PerBid{" +
                "perBid=" + perBid +
                ", bidStatus=" + bidStatus +
                ", borrower=" + borrower +
                ", perBidName='" + perBidName + '\'' +
                ", inRate=" + inRate +
                ", rate=" + rate +
                ", addRate=" + addRate +
                ", combination=" + combination +
                ", transLine='" + transLine + '\'' +
                ", clockLine='" + clockLine + '\'' +
                ", transfer=" + transfer +
                ", startTime=" + startTime +
                ", enquiry=" + enquiry +
                ", schedul=" + schedul +
                ", expire=" + expire +
                ", borrowTime=" + borrowTime +
                '}';
    }

    public float getInRate() {
        return inRate;
    }

    public void setInRate(float inRate) {
        this.inRate = inRate;
    }

    public int getPerBid() {
        return perBid;
    }

    public void setPerBid(int perBid) {
        this.perBid = perBid;
    }

    public int getBidStatus() {
        return bidStatus;
    }

    public void setBidStatus(int bidStatus) {
        this.bidStatus = bidStatus;
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

    public float getCombination() {
        return combination;
    }

    public void setCombination(float combination) {
        this.combination = combination;
    }

    public String getTransLine() {
        return transLine;
    }

    public void setTransLine(String transLine) {
        this.transLine = transLine;
    }

    public String getClockLine() {
        return clockLine;
    }

    public void setClockLine(String clockLine) {
        this.clockLine = clockLine;
    }

    public int getTransfer() {
        return transfer;
    }

    public void setTransfer(int transfer) {
        this.transfer = transfer;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public float getEnquiry() {
        return enquiry;
    }

    public void setEnquiry(float enquiry) {
        this.enquiry = enquiry;
    }

    public float getSchedul() {
        return schedul;
    }

    public void setSchedul(float schedul) {
        this.schedul = schedul;
    }

    public Date getExpire() {
        return expire;
    }

    public void setExpire(Date expire) {
        this.expire = expire;
    }

    public int getBorrowTime() {
        return borrowTime;
    }

    public void setBorrowTime(int borrowTime) {
        this.borrowTime = borrowTime;
    }
}
