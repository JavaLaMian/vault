package com.vault.demo.bean;

import java.util.Date;

public class LoanBankHistory {
    private int Id;
    private int lId;
    private float acMoney;
    private int bankId;
    private Date inTime;
    private int status;

    @Override
    public String toString() {
        return "LoanBankHistory{" +
                "Id=" + Id +
                ", lId=" + lId +
                ", acMoney=" + acMoney +
                ", bankId=" + bankId +
                ", inTime=" + inTime +
                ", status=" + status +
                '}';
    }

    public Date getInTime() {
        return inTime;
    }

    public void setInTime(Date inTime) {
        this.inTime = inTime;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getlId() {
        return lId;
    }

    public void setlId(int lId) {
        this.lId = lId;
    }

    public float getAcMoney() {
        return acMoney;
    }

    public void setAcMoney(float acMoney) {
        this.acMoney = acMoney;
    }

    public int getBankId() {
        return bankId;
    }

    public void setBankId(int bankId) {
        this.bankId = bankId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
