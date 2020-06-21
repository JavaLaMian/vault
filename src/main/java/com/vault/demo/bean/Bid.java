package com.vault.demo.bean;

import com.gitee.sunchenbin.mybatis.actable.annotation.Column;
import com.gitee.sunchenbin.mybatis.actable.annotation.Table;
import com.gitee.sunchenbin.mybatis.actable.constants.MySqlTypeConstant;

import java.util.Date;

@Table(name = "bid")
public class Bid {//6月10日15：29

    private static final int NEWHAND = 1; //新手标
    private static final int NORM = 2;  //优享标
    private static final int YES = 1;//是否自动转让
    private static final int NO = 0;  //在售（ON=0）
    private static final int READY = 1; //预售（READY=1）
    private static final int EMPTY = 3; //售罄
    private static final int CLOSE = 2; //关闭
    private static final int lockup = 4;//锁定期
    private static final int transferss = 5;//转让期

    public static int getLockup() {
        return lockup;
    }

    public static int getTransferss() {
        return transferss;
    }

    @Column(name = "bId", type = MySqlTypeConstant.INT, length = 11, isKey = true, isAutoIncrement = true)
    private int bId;
    @Column(name = "bidName", type = MySqlTypeConstant.VARCHAR, length = 100) //标名       1
    private String bidName;
    @Column(name = "bidType", type = MySqlTypeConstant.INT)  //标种 标类
    private int bidType;
    @Column(name = "rewardRate", type = MySqlTypeConstant.DOUBLE)
    private float rewardRate; //奖励利率
    @Column(name = "rate", type = MySqlTypeConstant.DOUBLE)
    private float rate; //基本利率
    @Column(name = "addLimit", type = MySqlTypeConstant.DOUBLE)
    private float addLimit; //每次可以追加金额
    @Column(name = "singelLimit", type = MySqlTypeConstant.DOUBLE)
    private float singelLimit; //单笔限额
    @Column(name = "sumLimit", type = MySqlTypeConstant.DOUBLE)
    private float sumLimit;//总体累计金额
    @Column(name = "personLimit",type = MySqlTypeConstant.DOUBLE)
    private float personLimit;//个人累计金额
    @Column(name = "startLimit", type = MySqlTypeConstant.DOUBLE)
    private float startLimit;//起标额
    @Column(name = "transLine", type = MySqlTypeConstant.VARCHAR, length = 100)
    private String transLine;//转让期
    @Column(name = "clockLine", type = MySqlTypeConstant.VARCHAR, length = 100)
    private String clockLine;//锁定期
    @Column(name = "deposit", type = MySqlTypeConstant.INT)
    private int deposit;//标期
    @Column(name = "dealCount", type = MySqlTypeConstant.INT)
    private int dealCount;//用户交易次数限制
    @Column(name = "question", type = MySqlTypeConstant.VARCHAR, length = 2000)
    private String question;//问
    @Column(name = "answer", type = MySqlTypeConstant.VARCHAR, length = 2000)
    private String answer;//答
    @Column(name = "transfer", type = MySqlTypeConstant.INT)
    private int transfer;//是否可自动转让
    @Column(name = "aotuInverst", type = MySqlTypeConstant.INT)
    private int aotuInverst;//是否可自动复投
    @Column(name = "exprie", type = MySqlTypeConstant.DATETIME)
    private Date exprie;//标封期时间
    @Column(name = "bidStatus", type = MySqlTypeConstant.INT)
    private int bidStatus;//状态     预售（READY=1），在售（ON=0），售罄（EMPTY=3），关闭交易（CLOSE=2）
    @Column(name = "bidTime", type = MySqlTypeConstant.DATETIME)
    private Date bidTime;//上线时间
    @Column(name = "maxTime", type = MySqlTypeConstant.VARCHAR, length = 50)
    private int maxTime;//最长投标时间  默认12个月，从买的时间开始算

    public Bid() {
    }


    @Override
    public String toString() {
        return "Bid{" +
                "bId=" + bId +
                ", bidName='" + bidName + '\'' +
                ", bidType=" + bidType +
                ", rewardRate=" + rewardRate +
                ", rate=" + rate +
                ", addLimit=" + addLimit +
                ", singelLimit=" + singelLimit +
                ", sumLimit=" + sumLimit +
                ", personLimit=" + personLimit +
                ", startLimit=" + startLimit +
                ", transLine='" + transLine + '\'' +
                ", clockLine='" + clockLine + '\'' +
                ", deposit=" + deposit +
                ", dealCount=" + dealCount +
                ", question='" + question + '\'' +
                ", answer='" + answer + '\'' +
                ", transfer=" + transfer +
                ", aotuInverst=" + aotuInverst +
                ", exprie=" + exprie +
                ", bidStatus=" + bidStatus +
                ", bidTime=" + bidTime +
                ", maxTime=" + maxTime +
                '}';
    }

    public float getPersonLimit() {
        return personLimit;
    }

    public void setPersonLimit(float personLimit) {
        this.personLimit = personLimit;
    }

    public static int getNEWHAND() {
        return NEWHAND;
    }

    public static int getNORM() {
        return NORM;
    }

    public static int getYES() {
        return YES;
    }

    public static int getNO() {
        return NO;
    }

    public static int getREADY() {
        return READY;
    }

    public static int getEMPTY() {
        return EMPTY;
    }

    public static int getCLOSE() {
        return CLOSE;
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