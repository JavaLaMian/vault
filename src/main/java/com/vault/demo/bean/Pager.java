package com.vault.demo.bean;

public class Pager {
    public int thisPage=1;//当前页
    public int titleSize=2;//每行页数
    public int  totalTitle;//总条数
    public int  totalPage;//总页数


    public void page(int title){
        totalTitle = title;
        //计算总页数
        totalPage = (totalTitle + titleSize-1)/titleSize;
        //判断页数限制
        if(thisPage<1){
            thisPage=1;
        }
        if(thisPage>totalPage){
            thisPage =totalPage;
        }
    }

    public int getThisPage() {
        return thisPage;
    }

    public void setThisPage(int thisPage) {
        this.thisPage = thisPage;
    }
}
