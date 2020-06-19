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
            String exprie = simpleDateFormat.format(bid.getExprie());//封标期时间
            String bidTime = simpleDateFormat.format(bid.getBidTime());//开始时间
            try {
                Date date1 =simpleDateFormat.parse(bidTime);//上线时间
                Date date = simpleDateFormat.parse(exprie);//封标期时间
                int time = date1.compareTo(new Date());//上线时间小于当前时间 -1
                int time1 = date.compareTo(new Date());//封标时间小于当前时间 -1
                if(time == -1 && time1 == -1){//预售（READY=1）
                    System.out.println("进来了"+bid.getbId());
                    is.updategetbiBid(1,bid.getbId());
                }
                else if(time != -1 && time1 == -1){//在售（ON=0）
                    is.updategetbiBid(Bid.getNO(),bid.getbId());
                }
                else if(time != -1 && time1 != -1){ //售罄
                    is.updategetbiBid(Bid.getEMPTY(),bid.getbId());
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }

        }
    }
}
