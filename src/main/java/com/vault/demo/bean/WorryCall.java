package com.vault.demo.bean;

import com.gitee.sunchenbin.mybatis.actable.annotation.Column;
import com.gitee.sunchenbin.mybatis.actable.annotation.Table;
import com.gitee.sunchenbin.mybatis.actable.constants.MySqlTypeConstant;

@Table(name = "worryCall")
public class WorryCall {
    @Column(name = "Id",type = MySqlTypeConstant.INT,length = 11,isKey = true,isAutoIncrement = true)
    private int Id;

    @Column(name = "uId",type = MySqlTypeConstant.INT)
    private int uId;

    @Column(name = "relationship",type = MySqlTypeConstant.VARCHAR,length = 100)
    private String relationship;

    @Column(name = "phe",type = MySqlTypeConstant.VARCHAR,length = 100)
    private String phe;

    @Column(name = "name",type = MySqlTypeConstant.VARCHAR,length = 100)
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
