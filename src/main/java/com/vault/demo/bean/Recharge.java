package com.vault.demo.bean;

import com.gitee.sunchenbin.mybatis.actable.annotation.Column;
import com.gitee.sunchenbin.mybatis.actable.annotation.Table;
import com.gitee.sunchenbin.mybatis.actable.constants.MySqlTypeConstant;

import java.util.Date;

@Table(name = "recharge")
public class Recharge {//6月10日16点21
    @Column(name = "rId",type = MySqlTypeConstant.INT,length = 11,isKey = true,isAutoIncrement = true)
    private int rId;

    @Column(name = "uId",type = MySqlTypeConstant.INT)
    private int uId;

    @Column(name = "reMoney",type = MySqlTypeConstant.DOUBLE)
    private float reMoney;

    @Column(name = "reTime",type = MySqlTypeConstant.DATETIME)
    private Date reTime;

    @Column(name = "bankId",type = MySqlTypeConstant.INT)
    private int bankId;

    @Column(name = "bankName",type = MySqlTypeConstant.VARCHAR,length = 100)
    private String bankName;

    @Override
    public String toString() {
        return "Recharge{" +
                "rId=" + rId +
                ", uId=" + uId +
                ", reMoney=" + reMoney +
                ", reTime=" + reTime +
                ", cardId=" + bankId +
                ", bankName='" + bankName + '\'' +
                '}';
    }

    public int getrId() {
        return rId;
    }

    public void setrId(int rId) {
        this.rId = rId;
    }

    public int getuId() {
        return uId;
    }

    public void setuId(int uId) {
        this.uId = uId;
    }

    public float getReMoney() {
        return reMoney;
    }

    public void setReMoney(float reMoney) {
        this.reMoney = reMoney;
    }

    public Date getReTime() {
        return reTime;
    }

    public void setReTime(Date reTime) {
        this.reTime = reTime;
    }

    public int getBankId() {
        return bankId;
    }

    public void setBankId(int bankId) {
        this.bankId = bankId;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }
}
