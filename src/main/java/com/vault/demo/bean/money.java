package com.vault.demo.bean;


public class money {
    private int mid;
    private int bid;//标id
    private float sumLimit;//总体金额
    private float rate; //利率
    private float usermoney;//用户所得所有总金额

    @Override
    public String toString() {
        return "money{" +
                "mid=" + mid +
                ", bid=" + bid +
                ", sumLimit=" + sumLimit +
                ", rate=" + rate +
                ", usermoney=" + usermoney +
                '}';
    }

    public int getMid() {
        return mid;
    }

    public void setMid(int mid) {
        this.mid = mid;
    }

    public int getBid() {
        return bid;
    }

    public void setBid(int bid) {
        this.bid = bid;
    }

    public float getSumLimit() {
        return sumLimit;
    }

    public void setSumLimit(float sumLimit) {
        this.sumLimit = sumLimit;
    }

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    public float getUsermoney() {
        return usermoney;
    }

    public void setUsermoney(float usermoney) {
        this.usermoney = usermoney;
    }
}
