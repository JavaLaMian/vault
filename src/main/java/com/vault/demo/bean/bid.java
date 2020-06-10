package com.vault.demo.bean;

import com.gitee.sunchenbin.mybatis.actable.annotation.Column;
import com.gitee.sunchenbin.mybatis.actable.annotation.Table;
import com.gitee.sunchenbin.mybatis.actable.constants.MySqlTypeConstant;

@Table(name = "bid")
public class bid {

    @Column(name = "id",type = MySqlTypeConstant.INT,length = 11,isKey = true,isAutoIncrement = true)
    private Integer	id;
    @Column(name = "bidName",type = MySqlTypeConstant.VARCHAR,length = 100)
    private String bidName;
    @Column(name = "bidType",type = MySqlTypeConstant.INT)
    private String bidType;
    @Column(name = "rewardRate",type = MySqlTypeConstant.DOUBLE)
    private String rewardRate;
    @Column(name = "rate",type = MySqlTypeConstant.DOUBLE)
    private String rate;
    @Column(name = "addLimit",type = MySqlTypeConstant.DOUBLE)
    private String addLimit;
    @Column(name = "singelLimit",type = MySqlTypeConstant.DOUBLE)
    private String singelLimit;
    @Column(name = "sumLimit",type = MySqlTypeConstant.DOUBLE)
    private String sumLimit;
    @Column(name = "startLimit",type = MySqlTypeConstant.DOUBLE)
    private String startLimit;
    @Column(name = "transLine",type = MySqlTypeConstant.INT)
    private String transLine;
    @Column(name = "clockLine",type = MySqlTypeConstant.INT)
    private String clockLine;
    @Column(name = "deposit",type = MySqlTypeConstant.INT)
    private String deposit;
    @Column(name = "question",type = MySqlTypeConstant.VARCHAR)
    private String question;
    @Column(name = "answer",type = MySqlTypeConstant.VARCHAR)
    private String answer;
    @Column(name = "transfer",type = MySqlTypeConstant.INT)
    private String transfer;
    @Column(name = "aotuInverst",type = MySqlTypeConstant.INT)
    private String aotuInverst;
    @Column(name = "exprie",type = MySqlTypeConstant.DATETIME)
    private String exprie;
    @Column(name = "bidStatus",type = MySqlTypeConstant.INT)
    private String bidStatus;
    @Column(name = "bidTime",type = MySqlTypeConstant.DATETIME)
    private String bidTime;
    @Column(name = "maxTime",type = MySqlTypeConstant.INT)
    private String maxTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBidName() {
        return bidName;
    }

    public void setBidName(String bidName) {
        this.bidName = bidName;
    }

    public String getBidType() {
        return bidType;
    }

    public void setBidType(String bidType) {
        this.bidType = bidType;
    }

    public String getRewardRate() {
        return rewardRate;
    }

    public void setRewardRate(String rewardRate) {
        this.rewardRate = rewardRate;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getAddLimit() {
        return addLimit;
    }

    public void setAddLimit(String addLimit) {
        this.addLimit = addLimit;
    }

    public String getSingelLimit() {
        return singelLimit;
    }

    public void setSingelLimit(String singelLimit) {
        this.singelLimit = singelLimit;
    }

    public String getSumLimit() {
        return sumLimit;
    }

    public void setSumLimit(String sumLimit) {
        this.sumLimit = sumLimit;
    }

    public String getStartLimit() {
        return startLimit;
    }

    public void setStartLimit(String startLimit) {
        this.startLimit = startLimit;
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

    public String getDeposit() {
        return deposit;
    }

    public void setDeposit(String deposit) {
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

    public String getTransfer() {
        return transfer;
    }

    public void setTransfer(String transfer) {
        this.transfer = transfer;
    }

    public String getAotuInverst() {
        return aotuInverst;
    }

    public void setAotuInverst(String aotuInverst) {
        this.aotuInverst = aotuInverst;
    }

    public String getExprie() {
        return exprie;
    }

    public void setExprie(String exprie) {
        this.exprie = exprie;
    }

    public String getBidStatus() {
        return bidStatus;
    }

    public void setBidStatus(String bidStatus) {
        this.bidStatus = bidStatus;
    }

    public String getBidTime() {
        return bidTime;
    }

    public void setBidTime(String bidTime) {
        this.bidTime = bidTime;
    }

    public String getMaxTime() {
        return maxTime;
    }

    public void setMaxTime(String maxTime) {
        this.maxTime = maxTime;
    }
}
