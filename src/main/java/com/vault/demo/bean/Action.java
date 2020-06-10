package com.vault.demo.bean;

import com.gitee.sunchenbin.mybatis.actable.annotation.Column;
import com.gitee.sunchenbin.mybatis.actable.annotation.Table;
import com.gitee.sunchenbin.mybatis.actable.constants.MySqlTypeConstant;

import java.util.Date;

@Table(name = "action")
public class Action {//6月10日16点42

    private static final int BEFORE = 0;
    private static final int IN = 1;
    private static final int CAN = 2;
    private static final int END = 3;

    @Column(name = "aId",type = MySqlTypeConstant.INT,length = 11,isKey = true,isAutoIncrement = true)
    private int aId;

    @Column(name = "lId",type = MySqlTypeConstant.INT)
    private int lId;

    @Column(name = "uId",type = MySqlTypeConstant.INT)
    private int uId;

    @Column(name = "acMoney",type = MySqlTypeConstant.DOUBLE)
    private float acMoney;

    @Column(name = "minRepayTime",type = MySqlTypeConstant.DATETIME)
    private Date minRepayTime;

    @Column(name = "maxRepayTime",type = MySqlTypeConstant.DATETIME)
    private Date maxRepayTime;

    @Column(name = "acStatus",type = MySqlTypeConstant.INT)
    private int acStatus;

    @Column(name = "tobePay",type = MySqlTypeConstant.DOUBLE)
    private float tobePay;

    @Override
    public String toString() {
        return "Action{" +
                "aId=" + aId +
                ", lId=" + lId +
                ", uId=" + uId +
                ", acMoney=" + acMoney +
                ", minRepayTime=" + minRepayTime +
                ", maxRepayTime=" + maxRepayTime +
                ", acStatus=" + acStatus +
                ", tobePay=" + tobePay +
                '}';
    }

    public int getaId() {
        return aId;
    }

    public void setaId(int aId) {
        this.aId = aId;
    }

    public int getlId() {
        return lId;
    }

    public void setlId(int lId) {
        this.lId = lId;
    }

    public int getuId() {
        return uId;
    }

    public void setuId(int uId) {
        this.uId = uId;
    }

    public float getAcMoney() {
        return acMoney;
    }

    public void setAcMoney(float acMoney) {
        this.acMoney = acMoney;
    }

    public Date getMinRepayTime() {
        return minRepayTime;
    }

    public void setMinRepayTime(Date minRepayTime) {
        this.minRepayTime = minRepayTime;
    }

    public Date getMaxRepayTime() {
        return maxRepayTime;
    }

    public void setMaxRepayTime(Date maxRepayTime) {
        this.maxRepayTime = maxRepayTime;
    }

    public int getAcStatus() {
        return acStatus;
    }

    public void setAcStatus(int acStatus) {
        this.acStatus = acStatus;
    }

    public float getTobePay() {
        return tobePay;
    }

    public void setTobePay(float tobePay) {
        this.tobePay = tobePay;
    }
}
