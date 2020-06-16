package com.vault.demo.common;

/**
 * Created by Administrator on 2020/4/27.
 */
public class Pager {

    public int currPage=1;//当前页
    public int pageSize=8;//每页行数
    public int totalRow=0;//总行数
    public int totalPage=0;//总页数
    public Object data;//每页数据

    public void page(int row){
        totalRow = row;
        //计算总页数
        totalPage=(totalRow+pageSize-1)/pageSize;
        //判断当前页的上限和下限
        if(currPage<1){
            currPage=1;
        }
        if(currPage>totalPage&&totalPage!=0){
           currPage=totalPage;
        }
    }

    public int getCurrPage() {
        return currPage;
    }

    public void setCurrPage(int currPage) {
        this.currPage = currPage;
    }

}
