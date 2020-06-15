package com.vault.demo.bean;

import com.gitee.sunchenbin.mybatis.actable.annotation.Column;
import com.gitee.sunchenbin.mybatis.actable.annotation.Table;
import com.gitee.sunchenbin.mybatis.actable.constants.MySqlTypeConstant;

import java.util.Date;

@Table(name = "Tender")
public class Tender {//6月10日15点59

//    private static final int ZHANGRANGQI = 1;
//    private static final int SUODINGQI = 2 ;

    @Column(name = "tId",type = MySqlTypeConstant.INT,length = 11,isKey = true,isAutoIncrement = true)
    private int	tId;

    @Column(name = "bId",type = MySqlTypeConstant.INT)
    private int	bId;

    @Column(name = "uId",type = MySqlTypeConstant.INT)
    private int	uId;

    @Column(name = "tenMoney",type = MySqlTypeConstant.DOUBLE)
    private float	tenMoney;

    @Column(name = "tenTime",type = MySqlTypeConstant.DATETIME)
    private Date tenTime;

    @Column(name = "tenType",type = MySqlTypeConstant.INT)
    private int	tenType;

    @Column(name = "tenCicle",type = MySqlTypeConstant.DATETIME)
    private Date	tenCicle;

    private  int bType;
    public Tender(){}


    public Tender(int tId, int bId, int uId, float tenMoney, Date tenTime, int tenType, Date tenCicle, int bType) {
        this.tId = tId;
        this.bId = bId;
        this.uId = uId;
        this.tenMoney = tenMoney;
        this.tenTime = tenTime;
        this.tenType = tenType;
        this.tenCicle = tenCicle;
        this.bType = bType;
    }

    @Override
    public String toString() {
        return "Tender{" +
                "tId=" + tId +
                ", bId=" + bId +
                ", uId=" + uId +
                ", tenMoney=" + tenMoney +
                ", tenTime=" + tenTime +
                ", tenType=" + tenType +
                ", tenCicle=" + tenCicle +
                ", bType=" + bType +
                '}';
    }

    public int gettId() {
        return tId;
    }

    public void settId(int tId) {
        this.tId = tId;
    }

    public int getbId() {
        return bId;
    }

    public void setbId(int bId) {
        this.bId = bId;
    }

    public int getuId() {
        return uId;
    }

    public void setuId(int uId) {
        this.uId = uId;
    }

    public float getTenMoney() {
        return tenMoney;
    }

    public void setTenMoney(float tenMoney) {
        this.tenMoney = tenMoney;
    }

    public Date getTenTime() {
        return tenTime;
    }

    public void setTenTime(Date tenTime) {
        this.tenTime = tenTime;
    }

    public int getTenType() {
        return tenType;
    }

    public void setTenType(int tenType) {
        this.tenType = tenType;
    }

    public Date getTenCicle() {
        return tenCicle;
    }

    public void setTenCicle(Date tenCicle) {
        this.tenCicle = tenCicle;
    }

    public int getbType() {
        return bType;
    }

    public void setbType(int bType) {
        this.bType = bType;
    }
}
