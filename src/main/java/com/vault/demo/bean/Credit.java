package com.vault.demo.bean;

import com.gitee.sunchenbin.mybatis.actable.annotation.Column;
import com.gitee.sunchenbin.mybatis.actable.annotation.Table;
import com.gitee.sunchenbin.mybatis.actable.constants.MySqlTypeConstant;

@Table(name = "credit")
public class Credit {//6月10日15点31

    @Column(name = "creId",type = MySqlTypeConstant.INT,length = 11,isKey = true,isAutoIncrement = true)
    private int creId;
    @Column(name = "uId",type = MySqlTypeConstant.INT)
    private int uId;
    @Column(name = "depart",type = MySqlTypeConstant.VARCHAR,length = 100)
    private String depart;
    @Column(name = "identity",type = MySqlTypeConstant.VARCHAR,length = 100)
    private String identity;
    @Column(name = "hId",type = MySqlTypeConstant.INT)
    private int hId;
    @Column(name = "funds",type = MySqlTypeConstant.VARCHAR,length = 100)
    private String funds;
    @Column(name = "photo",type = MySqlTypeConstant.VARCHAR,length = 200)
    private String photo;

    @Override
    public String toString() {
        return "Credit{" +
                "creId=" + creId +
                ", uId=" + uId +
                ", depart='" + depart + '\'' +
                ", identity='" + identity + '\'' +
                ", house='" + hId + '\'' +
                ", funds='" + funds + '\'' +
                ", photo='" + photo + '\'' +
                '}';
    }

    public int getCreId() {
        return creId;
    }

    public void setCreId(int creId) {
        this.creId = creId;
    }

    public int getuId() {
        return uId;
    }

    public void setuId(int uId) {
        this.uId = uId;
    }

    public String getDepart() {
        return depart;
    }

    public void setDepart(String depart) {
        this.depart = depart;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public int getHouse() {
        return hId;
    }

    public void setHouse(int hId) {
        this.hId = hId;
    }

    public String getFunds() {
        return funds;
    }

    public void setFunds(String funds) {
        this.funds = funds;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
