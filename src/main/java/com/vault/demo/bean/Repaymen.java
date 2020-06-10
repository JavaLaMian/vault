package com.vault.demo.bean;

import com.gitee.sunchenbin.mybatis.actable.annotation.Column;
import com.gitee.sunchenbin.mybatis.actable.annotation.Table;
import com.gitee.sunchenbin.mybatis.actable.constants.MySqlTypeConstant;

import java.util.Date;

@Table(name = "repaymen")
public class Repaymen {//6月10日16点42
    @Column(name = "reId",type = MySqlTypeConstant.INT,length = 11,isKey = true,isAutoIncrement = true)
    private int reId;

    @Column(name = "lId",type = MySqlTypeConstant.INT)
    private int lId;

    @Column(name = "repayTime",type = MySqlTypeConstant.DATETIME)
    private Date repayTime;

    @Column(name = "repayMoney",type = MySqlTypeConstant.DOUBLE)
    private float repayMoney;

    @Override
    public String toString() {
        return "Repaymen{" +
                "reId=" + reId +
                ", lId=" + lId +
                ", repayTime=" + repayTime +
                ", repayMoney=" + repayMoney +
                '}';
    }

    public int getReId() {
        return reId;
    }

    public void setReId(int reId) {
        this.reId = reId;
    }

    public int getlId() {
        return lId;
    }

    public void setlId(int lId) {
        this.lId = lId;
    }

    public Date getRepayTime() {
        return repayTime;
    }

    public void setRepayTime(Date repayTime) {
        this.repayTime = repayTime;
    }

    public float getRepayMoney() {
        return repayMoney;
    }

    public void setRepayMoney(float repayMoney) {
        this.repayMoney = repayMoney;
    }
}
