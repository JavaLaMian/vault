package com.vault.demo.bean;

import com.gitee.sunchenbin.mybatis.actable.annotation.Column;
import com.gitee.sunchenbin.mybatis.actable.annotation.Table;
import com.gitee.sunchenbin.mybatis.actable.constants.MySqlTypeConstant;

import java.util.Date;

@Table(name = "bid")
public class Bid {//6月10日15：29

    private static final int NEWHAND = 1;
    private static final int NORM = 2;
    private static final int YES = 1;
    private static final int NO = 0;
    private static final int READY = 1;
    private static final int EMPTY = 3;
    private static final int CLOSE = 2;

    @Column(name = "bId",type = MySqlTypeConstant.INT,length = 11,isKey = true,isAutoIncrement = true)
    private int	bId;
    @Column(name = "bidName",type = MySqlTypeConstant.VARCHAR,length = 100)
    private String bidName;
    @Column(name = "bidType",type = MySqlTypeConstant.INT)
    private int bidType;
    @Column(name = "rewardRate",type = MySqlTypeConstant.DOUBLE)
    private float rewardRate;
    @Column(name = "rate",type = MySqlTypeConstant.DOUBLE)
    private float rate;
    @Column(name = "addLimit",type = MySqlTypeConstant.DOUBLE)
    private float addLimit;
    @Column(name = "singelLimit",type = MySqlTypeConstant.DOUBLE)
    private float singelLimit;
    @Column(name = "sumLimit",type = MySqlTypeConstant.DOUBLE)
    private float sumLimit;
    @Column(name = "startLimit",type = MySqlTypeConstant.DOUBLE)
    private float startLimit;
    @Column(name = "transLine",type = MySqlTypeConstant.INT)
    private int transLine;
    @Column(name = "clockLine",type = MySqlTypeConstant.INT)
    private int clockLine;
    @Column(name = "deposit",type = MySqlTypeConstant.INT)
    private int deposit;
    @Column(name = "dealCount",type = MySqlTypeConstant.INT)
    private int dealCount;
    @Column(name = "question",type = MySqlTypeConstant.VARCHAR,length = 2000)
    private String question;
    @Column(name = "answer",type = MySqlTypeConstant.VARCHAR,length = 2000)
    private String answer;
    @Column(name = "transfer",type = MySqlTypeConstant.INT)
    private int transfer;
    @Column(name = "aotuInverst",type = MySqlTypeConstant.INT)
    private int aotuInverst;
    @Column(name = "exprie",type = MySqlTypeConstant.DATETIME)
    private Date exprie;
    @Column(name = "bidStatus",type = MySqlTypeConstant.INT)
    private int bidStatus;
    @Column(name = "bidTime",type = MySqlTypeConstant.DATETIME)
    private Date bidTime;
    @Column(name = "maxTime",type = MySqlTypeConstant.INT)
    private int maxTime;


    @Override
    public String toString() {
        return "Bid{" +
                "bId=" + bId +
                ", bidName='" + bidName + '\'' +
                ", bidType='" + bidType + '\'' +
                ", rewardRate='" + rewardRate + '\'' +
                ", rate='" + rate + '\'' +
                ", addLimit='" + addLimit + '\'' +
                ", singelLimit='" + singelLimit + '\'' +
                ", sumLimit='" + sumLimit + '\'' +
                ", startLimit='" + startLimit + '\'' +
                ", transLine='" + transLine + '\'' +
                ", clockLine='" + clockLine + '\'' +
                ", deposit='" + deposit + '\'' +
                ", dealCount='" + dealCount + '\'' +
                ", question='" + question + '\'' +
                ", answer='" + answer + '\'' +
                ", transfer='" + transfer + '\'' +
                ", aotuInverst='" + aotuInverst + '\'' +
                ", exprie='" + exprie + '\'' +
                ", bidStatus='" + bidStatus + '\'' +
                ", bidTime='" + bidTime + '\'' +
                ", maxTime='" + maxTime + '\'' +
                '}';
    }

    public int getbId() {
        return bId;
    }

    public void setbId(int bId) {
        this.bId = bId;
    }

    public String getBidName() {
        return bidName;
    }

    public void setBidName(String bidName) {
        this.bidName = bidName;
    }

    public int getBidType() {
        return bidType;
    }

    public void setBidType(int bidType) {
        this.bidType = bidType;
    }

    public float getRewardRate() {
        return rewardRate;
    }

    public void setRewardRate(float rewardRate) {
        this.rewardRate = rewardRate;
    }

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    public float getAddLimit() {
        return addLimit;
    }

    public void setAddLimit(float addLimit) {
        this.addLimit = addLimit;
    }

    public float getSingelLimit() {
        return singelLimit;
    }

    public void setSingelLimit(float singelLimit) {
        this.singelLimit = singelLimit;
    }

    public float getSumLimit() {
        return sumLimit;
    }

    public void setSumLimit(float sumLimit) {
        this.sumLimit = sumLimit;
    }

    public float getStartLimit() {
        return startLimit;
    }

    public void setStartLimit(float startLimit) {
        this.startLimit = startLimit;
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

    public int getDeposit() {
        return deposit;
    }

    public void setDeposit(int deposit) {
        this.deposit = deposit;
    }

    public int getDealCount() {
        return dealCount;
    }

    public void setDealCount(int dealCount) {
        this.dealCount = dealCount;
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

    public int getAotuInverst() {
        return aotuInverst;
    }

    public void setAotuInverst(int aotuInverst) {
        this.aotuInverst = aotuInverst;
    }

    public Date getExprie() {
        return exprie;
    }

    public void setExprie(Date exprie) {
        this.exprie = exprie;
    }

    public int getBidStatus() {
        return bidStatus;
    }

    public void setBidStatus(int bidStatus) {
        this.bidStatus = bidStatus;
    }

    public Date getBidTime() {
        return bidTime;
    }

    public void setBidTime(Date bidTime) {
        this.bidTime = bidTime;
    }

    public int getMaxTime() {
        return maxTime;
    }

    public void setMaxTime(int maxTime) {
        this.maxTime = maxTime;
    }
}
