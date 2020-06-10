package com.vault.demo.bean;

import com.gitee.sunchenbin.mybatis.actable.annotation.Column;
import com.gitee.sunchenbin.mybatis.actable.annotation.Table;
import com.gitee.sunchenbin.mybatis.actable.constants.MySqlTypeConstant;

@Table(name = "admin")
public class Admin {//6月10日16点42
    @Column(name = "aid",type = MySqlTypeConstant.INT,length = 11,isKey = true,isAutoIncrement = true)
    private int aid;

    @Column(name = "account",type = MySqlTypeConstant.VARCHAR,length = 100)
    private String account;

    @Column(name = "pwd",type = MySqlTypeConstant.VARCHAR,length = 100)
    private String pwd;

    @Column(name = "jurisdiction",type = MySqlTypeConstant.INT)
    private int jurisdiction;

    @Column(name = "a_name",type = MySqlTypeConstant.VARCHAR,length = 100)
    private String a_name;

    @Column(name = "a_img",type = MySqlTypeConstant.VARCHAR,length = 200)
    private String a_img;

    @Column(name = "a_phone",type = MySqlTypeConstant.VARCHAR,length = 100)
    private String a_phone;

    @Override
    public String toString() {
        return "Admin{" +
                "aid=" + aid +
                ", account='" + account + '\'' +
                ", pwd='" + pwd + '\'' +
                ", jurisdiction=" + jurisdiction +
                ", a_name='" + a_name + '\'' +
                ", a_img='" + a_img + '\'' +
                ", a_phone='" + a_phone + '\'' +
                '}';
    }

    public int getAid() {
        return aid;
    }

    public void setAid(int aid) {
        this.aid = aid;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public int getJurisdiction() {
        return jurisdiction;
    }

    public void setJurisdiction(int jurisdiction) {
        this.jurisdiction = jurisdiction;
    }

    public String getA_name() {
        return a_name;
    }

    public void setA_name(String a_name) {
        this.a_name = a_name;
    }

    public String getA_img() {
        return a_img;
    }

    public void setA_img(String a_img) {
        this.a_img = a_img;
    }

    public String getA_phone() {
        return a_phone;
    }

    public void setA_phone(String a_phone) {
        this.a_phone = a_phone;
    }
}
