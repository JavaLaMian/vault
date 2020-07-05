package com.vault.demo.bean;


import com.gitee.sunchenbin.mybatis.actable.annotation.Column;
import com.gitee.sunchenbin.mybatis.actable.annotation.Table;
import com.gitee.sunchenbin.mybatis.actable.constants.MySqlTypeConstant;

//积分商品表
//@Table(name = "integral")
public class Integral {

    private int Id;
    private String integralName;
    private String integralImg;
    private int integral;
    private String integralType;
    private int inventory ;//库存
    private String tag; //标签  热门，人气 ，优质

    @Override
    public String toString() {
        return "integral{" +
                "Id=" + Id +
                ", integralName='" + integralName + '\'' +
                ", integralImg='" + integralImg + '\'' +
                ", integral=" + integral +
                ", integralType='" + integralType + '\'' +
                ", inventory=" + inventory +
                ", tag='" + tag + '\'' +
                '}';
    }

    public String getIntegralImg() {
        return integralImg;
    }

    public void setIntegralImg(String integralImg) {
        this.integralImg = integralImg;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getIntegralName() {
        return integralName;
    }

    public void setIntegralName(String integralName) {
        this.integralName = integralName;
    }

    public int getIntegral() {
        return integral;
    }

    public void setIntegral(int integral) {
        this.integral = integral;
    }

    public String getIntegralType() {
        return integralType;
    }

    public void setIntegralType(String integralType) {
        this.integralType = integralType;
    }

    public int getInventory() {
        return inventory;
    }

    public void setInventory(int inventory) {
        this.inventory = inventory;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

}
