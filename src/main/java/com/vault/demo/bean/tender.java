package com.vault.demo.bean;

import com.gitee.sunchenbin.mybatis.actable.annotation.Column;
import com.gitee.sunchenbin.mybatis.actable.annotation.Table;
import com.gitee.sunchenbin.mybatis.actable.constants.MySqlTypeConstant;

@Table(name = "tender")
public class tender {

    @Column(name = "tId",type = MySqlTypeConstant.INT,length = 11,isKey = true,isAutoIncrement = true)
    private Integer	tId;

    @Column(name = "bId",type = MySqlTypeConstant.INT)
    private String	bId;

    @Column(name = "uId",type = MySqlTypeConstant.INT)
    private String	uId;

    @Column(name = "tenMoney",type = MySqlTypeConstant.DOUBLE)
    private String	tenMoney;

    @Column(name = "tenTime",type = MySqlTypeConstant.DATETIME)
    private String	tenTime;

    @Column(name = "tenType",type = MySqlTypeConstant.INT)
    private String	tenType;

    @Column(name = "tenCicle",type = MySqlTypeConstant.DATETIME)
    private String	tenCicle;
}
