package com.vault.demo.bean;

import com.gitee.sunchenbin.mybatis.actable.annotation.Table;

@Table(name = "worryCall")
public class WorryCall {
    private int Id;
    private int uId;
    private String relationship;
    private String phe;
    private String name;

    @Override
    public String toString() {
        return "WorryCall{" +
                "Id=" + Id +
                ", uId=" + uId +
                ", relationship='" + relationship + '\'' +
                ", phe='" + phe + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getuId() {
        return uId;
    }

    public void setuId(int uId) {
        this.uId = uId;
    }

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }

    public String getPhe() {
        return phe;
    }

    public void setPhe(String phe) {
        this.phe = phe;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
