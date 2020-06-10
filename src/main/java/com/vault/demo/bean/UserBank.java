package com.vault.demo.bean;

import com.gitee.sunchenbin.mybatis.actable.annotation.Column;
import com.gitee.sunchenbin.mybatis.actable.annotation.Table;
import com.gitee.sunchenbin.mybatis.actable.constants.MySqlTypeConstant;

@Table(name = "userBank")
public class UserBank {//6月10日16点11

    @Column(name = "bankId",type = MySqlTypeConstant.INT,length = 11,isKey = true,isAutoIncrement = true)
    private int bankId;

    @Column(name = "uId",type = MySqlTypeConstant.INT)
    private int uId;

    @Column(name = "bankName",type = MySqlTypeConstant.VARCHAR,length = 100)
    private String bankName;

    @Column(name = "cardId",type = MySqlTypeConstant.VARCHAR,length = 200)
    private String cardId;

    @Override
    public String toString() {
        return "UserBank{" +
                "bankId=" + bankId +
                ", uId=" + uId +
                ", bankName='" + bankName + '\'' +
                ", cardId='" + cardId + '\'' +
                '}';
    }

    public int getBankId() {
        return bankId;
    }

    public void setBankId(int bankId) {
        this.bankId = bankId;
    }

    public int getuId() {
        return uId;
    }

    public void setuId(int uId) {
        this.uId = uId;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }
}
