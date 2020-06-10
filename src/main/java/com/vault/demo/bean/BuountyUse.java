package com.vault.demo.bean;

import com.gitee.sunchenbin.mybatis.actable.annotation.Column;
import com.gitee.sunchenbin.mybatis.actable.annotation.Table;
import com.gitee.sunchenbin.mybatis.actable.constants.MySqlTypeConstant;

import java.util.Date;

@Table(name = "buountyUse")
public class BuountyUse {//6月10日16点07
    @Column(name = "bouID",type = MySqlTypeConstant.INT,length = 11,isKey = true,isAutoIncrement = true)
    private int bouID;

    @Column(name = "uId",type = MySqlTypeConstant.INT)
    private int uId;

    @Column(name = "bouTime",type = MySqlTypeConstant.DATETIME)
    private Date bouTime;

    @Column(name = "boId",type = MySqlTypeConstant.INT)
    private int boId;

    @Override
    public String toString() {
        return "BuountyUse{" +
                "bouID=" + bouID +
                ", uId=" + uId +
                ", bouTime=" + bouTime +
                ", boId=" + boId +
                '}';
    }

    public int getBouID() {
        return bouID;
    }

    public void setBouID(int bouID) {
        this.bouID = bouID;
    }

    public int getuId() {
        return uId;
    }

    public void setuId(int uId) {
        this.uId = uId;
    }

    public Date getBouTime() {
        return bouTime;
    }

    public void setBouTime(Date bouTime) {
        this.bouTime = bouTime;
    }

    public int getBoId() {
        return boId;
    }

    public void setBoId(int boId) {
        this.boId = boId;
    }
}
