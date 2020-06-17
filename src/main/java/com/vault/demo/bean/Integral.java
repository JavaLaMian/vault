package com.vault.demo.bean;


import com.gitee.sunchenbin.mybatis.actable.annotation.Column;
import com.gitee.sunchenbin.mybatis.actable.annotation.Table;
import com.gitee.sunchenbin.mybatis.actable.constants.MySqlTypeConstant;

//积分商品表
@Table(name = "integral")
public class Integral {

    @Column(name = "Id",type = MySqlTypeConstant.INT,length = 11,isKey = true,isAutoIncrement = true)
    private int Id;

      @Column(name = "integralName",type = MySqlTypeConstant.VARCHAR,length = 100)
    private String integralName;

    @Column(name = "integralImg",type = MySqlTypeConstant.VARCHAR,length = 100)
    private String integralImg;


    @Column(name = "integral",type = MySqlTypeConstant.INT)
    private int integral;

    @Column(name = "integralType",type = MySqlTypeConstant.VARCHAR,length = 100)
    private String integralType;

    @Column(name = "inventory ",type = MySqlTypeConstant.INT)
    private int inventory ; //库存

    @Column(name = "tag",type = MySqlTypeConstant.VARCHAR,length = 100)
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
