package com.vault.demo.bean;

import com.gitee.sunchenbin.mybatis.actable.annotation.Column;
import com.gitee.sunchenbin.mybatis.actable.annotation.Table;
import com.gitee.sunchenbin.mybatis.actable.constants.MySqlTypeConstant;

import java.util.Date;

@Table(name = "bounty")
public class Bounty {//6月10日16点03
    private static final int BAIPIAO = 1;
    private static final int SHOPPING = 3;
    private static final int FRIEND = 2;


    @Column(name = "boId",type = MySqlTypeConstant.INT,length = 11,isKey = true,isAutoIncrement = true)
    private int boId;

    @Column(name = "uId",type = MySqlTypeConstant.INT)
    private int uId;

    @Column(name = "boType",type = MySqlTypeConstant.INT)
    private int boType;

    @Column(name = "boMoney",type = MySqlTypeConstant.DOUBLE)
    private float boMoney;

    @Column(name = "boTime",type = MySqlTypeConstant.DATETIME)
    private Date boTime;

    @Override
    public String toString() {
        return "Bounty{" +
                "boId=" + boId +
                ", uId=" + uId +
                ", boType=" + boType +
                ", boMoney=" + boMoney +
                ", boTime=" + boTime +
                '}';
    }

    public int getBoId() {
        return boId;
    }

    public void setBoId(int boId) {
        this.boId = boId;
    }

    public int getuId() {
        return uId;
    }

    public void setuId(int uId) {
        this.uId = uId;
    }

    public int getBoType() {
        return boType;
    }

    public void setBoType(int boType) {
        this.boType = boType;
    }

    public float getBoMoney() {
        return boMoney;
    }

    public void setBoMoney(float boMoney) {
        this.boMoney = boMoney;
    }

    public Date getBoTime() {
        return boTime;
    }

    public void setBoTime(Date boTime) {
        this.boTime = boTime;
    }
}
