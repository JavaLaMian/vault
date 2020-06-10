package com.vault.demo.bean;

import com.gitee.sunchenbin.mybatis.actable.annotation.Column;
import com.gitee.sunchenbin.mybatis.actable.annotation.Table;
import com.gitee.sunchenbin.mybatis.actable.constants.MySqlTypeConstant;

@Table(name = "userimf")
public class userimf {

    private static final long serialVersionUID = 5199200306752426433L;

    @Column(name = "uid",type = MySqlTypeConstant.INT,length = 11,isKey = true,isAutoIncrement = true)
    private Integer	uid;

    @Column(name = "uname",type = MySqlTypeConstant.VARCHAR,length = 200)
    private String	username;

    @Column(name = "phe",type = MySqlTypeConstant.VARCHAR,length = 200)
    private String	phe;

    @Column(name = "email",type = MySqlTypeConstant.VARCHAR,length = 200)
    private String	email;

    @Column(name = "loginPsw",type = MySqlTypeConstant.VARCHAR,length = 200)
    private String	loginPsw;

    @Column(name = "dealPsw",type = MySqlTypeConstant.VARCHAR,length = 200)
    private String	dealPsw;

    @Column(name = "regTime",type = MySqlTypeConstant.DATETIME)
    private String	regTime;

    @Column(name = "avaBalance",type = MySqlTypeConstant.DOUBLE)
    private String	avaBalance;

    @Column(name = "interest",type = MySqlTypeConstant.DOUBLE)
    private String	interest;

    @Column(name = "frozfund",type = MySqlTypeConstant.DOUBLE)
    private String	frozfund;

    @Column(name = "bounty",type = MySqlTypeConstant.DOUBLE)
    private String	bounty;

    @Column(name = "sex",type = MySqlTypeConstant.CHAR)
    private String	sex;

    @Column(name = "refereer",type = MySqlTypeConstant.VARCHAR,length = 20)
    private String	refereer;

    @Column(name = "refereerId",type = MySqlTypeConstant.INT)
    private String	refereerId;

    @Column(name = "creditLv",type = MySqlTypeConstant.INT)
    private String	creditLv;

    @Column(name = "age",type = MySqlTypeConstant.INT)
    private String	age;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhe() {
        return phe;
    }

    public void setPhe(String phe) {
        this.phe = phe;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLoginPsw() {
        return loginPsw;
    }

    public void setLoginPsw(String loginPsw) {
        this.loginPsw = loginPsw;
    }

    public String getDealPsw() {
        return dealPsw;
    }

    public void setDealPsw(String dealPsw) {
        this.dealPsw = dealPsw;
    }

    public String getRegTime() {
        return regTime;
    }

    public void setRegTime(String regTime) {
        this.regTime = regTime;
    }

    public String getAvaBalance() {
        return avaBalance;
    }

    public void setAvaBalance(String avaBalance) {
        this.avaBalance = avaBalance;
    }

    public String getInterest() {
        return interest;
    }

    public void setInterest(String interest) {
        this.interest = interest;
    }

    public String getFrozfund() {
        return frozfund;
    }

    public void setFrozfund(String frozfund) {
        this.frozfund = frozfund;
    }

    public String getBounty() {
        return bounty;
    }

    public void setBounty(String bounty) {
        this.bounty = bounty;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getRefereer() {
        return refereer;
    }

    public void setRefereer(String refereer) {
        this.refereer = refereer;
    }

    public String getRefereerId() {
        return refereerId;
    }

    public void setRefereerId(String refereerId) {
        this.refereerId = refereerId;
    }

    public String getCreditLv() {
        return creditLv;
    }

    public void setCreditLv(String creditLv) {
        this.creditLv = creditLv;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
