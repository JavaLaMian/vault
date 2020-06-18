package com.vault.demo.bean;

import com.gitee.sunchenbin.mybatis.actable.annotation.Column;
import com.gitee.sunchenbin.mybatis.actable.annotation.Table;
import com.gitee.sunchenbin.mybatis.actable.constants.MySqlTypeConstant;

import java.util.Date;
//
@Table(name = "perBid")
public class PerBid {
    @Column(name = "perBid",type = MySqlTypeConstant.INT,length = 11,isKey = true,isAutoIncrement = true)
   private int  perBid;
    @Column(name = "bidStatus",type = MySqlTypeConstant.INT)
   private int bidStatus;
    @Column(name = "borrower",type = MySqlTypeConstant.INT)
   private int borrower;

    @Column(name = "perBidName",type = MySqlTypeConstant.VARCHAR)
   private String  perBidName;
    @Column(name = "inRate",type = MySqlTypeConstant.VARCHAR)
   private  String  inRate ;

    @Column(name = "rate",type = MySqlTypeConstant.DOUBLE)
   private float rate;
    @Column(name = "addRate",type = MySqlTypeConstant.DOUBLE)
   private float addRate;
    @Column(name = "combination",type = MySqlTypeConstant.DOUBLE)
   private float  combination;

    @Column(name = "transLine",type = MySqlTypeConstant.VARCHAR)
   private String  transLine;
    @Column(name = "clockLine",type = MySqlTypeConstant.VARCHAR)
   private String clockLine;

    @Column(name = "deposit",type = MySqlTypeConstant.INT)
   private int  deposit;
    @Column(name = "question",type = MySqlTypeConstant.VARCHAR)
   private String question;
    @Column(name = "answer",type = MySqlTypeConstant.VARCHAR)
   private String answer;
    @Column(name = "transfer",type = MySqlTypeConstant.INT)
   private int  transfer;
    @Column(name = "startTime",type = MySqlTypeConstant.DATETIME)
   private Date startTime;
    @Column(name = "investor",type = MySqlTypeConstant.INT)
   private  int investor;

    @Override
    public String toString() {
        return "PerBid{" +
                "perBid=" + perBid +
                ", bidStatus=" + bidStatus +
                ", borrower=" + borrower +
                ", perBidName='" + perBidName + '\'' +
                ", inRate='" + inRate + '\'' +
                ", rate=" + rate +
                ", addRate=" + addRate +
                ", combination=" + combination +
                ", transLine='" + transLine + '\'' +
                ", clockLine='" + clockLine + '\'' +
                ", deposit=" + deposit +
                ", question='" + question + '\'' +
                ", answer='" + answer + '\'' +
                ", transfer=" + transfer +
                ", startTime=" + startTime +
                ", investor=" + investor +
                '}';
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

    public String getInRate() {
        return inRate;
    }

    public void setInRate(String inRate) {
        this.inRate = inRate;
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

    public int getDeposit() {
        return deposit;
    }

    public void setDeposit(int deposit) {
        this.deposit = deposit;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
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

    public int getInvestor() {
        return investor;
    }

    public void setInvestor(int investor) {
        this.investor = investor;
    }
}
