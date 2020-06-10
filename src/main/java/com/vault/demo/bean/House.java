package com.vault.demo.bean;

import com.gitee.sunchenbin.mybatis.actable.annotation.Column;
import com.gitee.sunchenbin.mybatis.actable.annotation.Table;
import com.gitee.sunchenbin.mybatis.actable.constants.MySqlTypeConstant;

import java.util.Date;

@Table(name = "house")
public class House {//6月10日15点59

    private static final int CAN=1;
    private static final int USED=2;
    private static final int NO=0;
    private static final int CHECK=4;

    @Column(name = "hId",type = MySqlTypeConstant.INT,length = 11,isKey = true,isAutoIncrement = true)
    private int hId;
    @Column(name = "uId",type = MySqlTypeConstant.INT)
    private int uId;
    @Column(name = "photo",type = MySqlTypeConstant.VARCHAR,length = 300)
    private String photo;
    @Column(name = "remark",type = MySqlTypeConstant.VARCHAR,length = 300)
    private String remark;
    @Column(name = "money",type = MySqlTypeConstant.DOUBLE)
    private float money;
    @Column(name = "status",type = MySqlTypeConstant.INT)
    private int status;

    @Override
    public String toString() {
        return "House{" +
                "hId=" + hId +
                ", uId=" + uId +
                ", photo='" + photo + '\'' +
                ", remark='" + remark + '\'' +
                ", money=" + money +
                ", status=" + status +
                '}';
    }

    public int gethId() {
        return hId;
    }

    public void sethId(int hId) {
        this.hId = hId;
    }

    public int getuId() {
        return uId;
    }

    public void setuId(int uId) {
        this.uId = uId;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public float getMoney() {
        return money;
    }

    public void setMoney(float money) {
        this.money = money;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
