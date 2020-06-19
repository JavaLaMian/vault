package com.vault.demo.quartz;

import com.vault.demo.bean.Bid;
import com.vault.demo.service.backstage.adxmn.selevicexmn;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class QuartzTask implements Job {
    @Resource
    selevicexmn is;
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        //查询出所有标 新手标和优享标
        List<Bid> list = is.Bidlist();
        Bid bid = new Bid();
        for(int i=0;i<list.size();i++){
            bid = list.get(i);//将参数遍历到bean// 新手标 优享标的时间
            //封标期到了就关闭这个标，将标的状态设置为售罄 3
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
            String exprie = simpleDateFormat.format(bid.getExprie());
            String bidTime = simpleDateFormat.format(bid.getBidTime());
            try {
                Date date = simpleDateFormat.parse(exprie);//封标期时间
                Date date1 =simpleDateFormat.parse(bidTime);//上线时间
                //将当前时间与封标期时间比较
                int time = date.compareTo(new Date());
                //将当前时间与开始时间比较
                int time1 = date.compareTo(new Date());
                if(time == -1){//当前时间大于锋标期时间 就是 -1
                    //将标给关闭，设置为售罄
                    is.updategetbiBid(bid);
                }
                if(bid.getBidStatus() == Bid.getREADY()){//判断是否是为上线的
                    if(time1 == -1){//当前时间大于上线时间 就是 -1
                        //将标给开启，设置为在售
                        is.updategetbiBid1(bid);
                    }
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }

        }
    }
}
