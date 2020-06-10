package com.vault.demo.bean;

import com.gitee.sunchenbin.mybatis.actable.annotation.Column;
import com.gitee.sunchenbin.mybatis.actable.annotation.Table;
import com.gitee.sunchenbin.mybatis.actable.constants.MySqlTypeConstant;

import java.util.Date;

@Table(name = "withdraw")
public class Withdraw {//6月10日16点22
    @Column(name = "wId",type = MySqlTypeConstant.INT,length = 11,isKey = true,isAutoIncrement = true)
    private int wId;

    @Column(name = "uId",type = MySqlTypeConstant.INT)
    private int uId;

    @Column(name = "withMoney",type = MySqlTypeConstant.DOUBLE)
    private float withMoney;

    @Column(name = "withTime",type = MySqlTypeConstant.DATETIME)
    private Date withTime;

    @Column(name = "bankId",type = MySqlTypeConstant.INT)
    private int bankId;

    @Column(name = "bankName",type = MySqlTypeConstant.VARCHAR,length = 100)
    private String bankName;

    @Override
    public String toString() {
        return "Withdraw{" +
                "wId=" + wId +
                ", uId=" + uId +
                ", withMoney=" + withMoney +
                ", withTime=" + withTime +
                ", bankId=" + bankId +
                ", bankName='" + bankName + '\'' +
                '}';
    }

    public int getwId() {
        return wId;
    }

    public void setwId(int wId) {
        this.wId = wId;
    }

    public int getuId() {
        return uId;
    }

    public void setuId(int uId) {
        this.uId = uId;
    }

    public float getWithMoney() {
        return withMoney;
    }

    public void setWithMoney(float withMoney) {
        this.withMoney = withMoney;
    }

    public Date getWithTime() {
        return withTime;
    }

    public void setWithTime(Date withTime) {
        this.withTime = withTime;
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
