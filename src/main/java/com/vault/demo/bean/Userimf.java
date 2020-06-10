package com.vault.demo.bean;

import com.gitee.sunchenbin.mybatis.actable.annotation.Column;
import com.gitee.sunchenbin.mybatis.actable.annotation.Table;
import com.gitee.sunchenbin.mybatis.actable.constants.MySqlTypeConstant;

import java.util.Date;

@Table(name = "userimf")
public class Userimf { //6月10日15点23
    private static final int NO = 1;
    private static final int BAD = 2;
    private static final int GOOD = 3;
    private static final int GREAT = 4;


    @Column(name = "uId",type = MySqlTypeConstant.INT,length = 11,isKey = true,isAutoIncrement = true)
    private int	uId;

    @Column(name = "uName",type = MySqlTypeConstant.VARCHAR,length = 100)
    private String	userName;

    @Column(name = "account",type = MySqlTypeConstant.VARCHAR,length = 100)
    private String account;

    @Column(name = "phe",type = MySqlTypeConstant.VARCHAR,length = 200)
    private String	phe;

    @Column(name = "email",type = MySqlTypeConstant.VARCHAR,length = 200)
    private String	email;

    @Column(name = "loginPsw",type = MySqlTypeConstant.VARCHAR,length = 200)
    private String	loginPsw;

    @Column(name = "dealPsw",type = MySqlTypeConstant.VARCHAR,length = 200)
    private String	dealPsw;

    @Column(name = "regTime",type = MySqlTypeConstant.DATETIME)
    private Date regTime;

    @Column(name = "avaBalance",type = MySqlTypeConstant.DOUBLE)
    private float	avaBalance;

    @Column(name = "interest",type = MySqlTypeConstant.DOUBLE)
    private float	interest;

    @Column(name = "frozfund",type = MySqlTypeConstant.DOUBLE)
    private float	frozfund;

    @Column(name = "bounty",type = MySqlTypeConstant.DOUBLE)
    private float	bounty;

    @Column(name = "sex",type = MySqlTypeConstant.VARCHAR,length = 5)
    private String	sex;

    @Column(name = "refereer",type = MySqlTypeConstant.VARCHAR,length = 100)
    private String	refereer;

    @Column(name = "creditLv",type = MySqlTypeConstant.INT)
    private int	creditLv;

    @Column(name = "age",type = MySqlTypeConstant.INT)
    private int	age;

    @Column(name = "lastLoginTime",type = MySqlTypeConstant.DATETIME)
    private Date lastLoginTime;


    @Override
    public String toString() {
        return "Userimf{" +
                "uId=" + uId +
                ", userName='" + userName + '\'' +
                ", account='" + account + '\'' +
                ", phe='" + phe + '\'' +
                ", email='" + email + '\'' +
                ", loginPsw='" + loginPsw + '\'' +
                ", dealPsw='" + dealPsw + '\'' +
                ", regTime=" + regTime +
                ", avaBalance=" + avaBalance +
                ", interest=" + interest +
                ", frozfund=" + frozfund +
                ", bounty=" + bounty +
                ", sex='" + sex + '\'' +
                ", refereer='" + refereer + '\'' +
                ", creditLv=" + creditLv +
                ", age=" + age +
                ", lastLoginTime=" + lastLoginTime +
                '}';
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public int getuId() {
        return uId;
    }

    public void setuId(int uId) {
        this.uId = uId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
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

    public Date getRegTime() {
        return regTime;
    }

    public void setRegTime(Date regTime) {
        this.regTime = regTime;
    }

    public float getAvaBalance() {
        return avaBalance;
    }

    public void setAvaBalance(float avaBalance) {
        this.avaBalance = avaBalance;
    }

    public float getInterest() {
        return interest;
    }

    public void setInterest(float interest) {
        this.interest = interest;
    }

    public float getFrozfund() {
        return frozfund;
    }

    public void setFrozfund(float frozfund) {
        this.frozfund = frozfund;
    }

    public float getBounty() {
        return bounty;
    }

    public void setBounty(float bounty) {
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

    public int getCreditLv() {
        return creditLv;
    }

    public void setCreditLv(int creditLv) {
        this.creditLv = creditLv;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
