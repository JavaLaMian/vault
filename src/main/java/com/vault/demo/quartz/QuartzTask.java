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
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
            String bidTime = simpleDateFormat.format(bid.getBidTime());//开始时间
            String exprie = simpleDateFormat.format(bid.getExprie());//封标期时间
            try {
                Date date1 =simpleDateFormat.parse(bidTime);//上线时间
                Date date = simpleDateFormat.parse(exprie);//封标期时间
                int time = date1.compareTo(new Date());//上线时间小于当前时间 -1
                int time1 = date.compareTo(new Date());//封标时间小于当前时间 -1
                Date date2 = null;//锁定期时间
                Float summoeny = bid.getSumLimit();//总体累计限额
                int id = is.selectgetByid();
                Float moeny = is.selectBidmoney(id); //查询出有哪些标投过，再根据id去查投了多少钱
                if(bid.getBidType() == Bid.getNEWHAND() || bid.getBidType() == Bid.getNORM()){//新手标和优享标
                   if(bid.getBidStatus() == Bid.getREADY()){//预售标
                        System.out.println(time+"时间"+time1+"id"+bid.getbId());
                        if(time != -1 && time1 != -1){//在售（ON=0）
                            is.updategetbiBid(Bid.getNO(),bid.getbId());
                        }
                    }else if(bid.getBidStatus() == Bid.getNO()){//在售标
                       if((time == -1 && time1 != -1) || ( moeny >= summoeny)){
                           is.updategetbiBid(Bid.getEMPTY(),bid.getbId());
                       }
                   }else if(bid.getDeposit() == 1 && bid.getBidStatus() == Bid.getEMPTY()){//查询所有定期的标并且售罄的标 加上封标期时间 然后对比当前时间 小于就是锁定期 大于就是转让期
                       if(bid.getClockLine().equals("1")){//定期为3个月的
                           Calendar cal = Calendar.getInstance();//创建时间相加
                           cal.setTime(date);
                           cal.add(Calendar.HOUR,1*24*30*3);//需要加上的时间
                           date2 = cal.getTime();
                           int ti = date2.compareTo(new Date());
                           if(ti == -1){//标到期 将标改为转让期
                               is.updategetbiBid(Bid.getLockup(),bid.getbId());
                           }
                       }
                       else if(bid.getClockLine().equals("2")){//定期为应该1个月的
                           Calendar cal = Calendar.getInstance();//创建时间相加
                           cal.setTime(date);
                           cal.add(Calendar.HOUR,1*24*30*6);//需要加上的时间
                           date2 = cal.getTime();
                           int ti = date2.compareTo(new Date());
                           if(ti == -1){//标到期 将标改为转让期
                               is.updategetbiBid(Bid.getLockup(),bid.getbId());
                           }
                       }
                       else if(bid.getClockLine().equals("3")){//定期为应该1个月的
                           Calendar cal = Calendar.getInstance();//创建时间相加
                           cal.setTime(date);
                           cal.add(Calendar.HOUR,1*24*30*12);//需要加上的时间
                           date2 = cal.getTime();
                           int ti = date2.compareTo(new Date());
                           if(ti == -1){//标到期 将标改为转让期
                               is.updategetbiBid(Bid.getLockup(),bid.getbId());
                           }
                       }
                   }else if(bid.getDeposit() == 1 && bid.getBidStatus() == Bid.getLockup()){//查询所有定期的标并且是锁定期  加上转让期然后再对比 小于就是转让期 大于就是关闭计算利率金钱
                       Calendar cal = Calendar.getInstance();//创建时间相加
                       cal.setTime(date2);
                       cal.add(Calendar.HOUR,1*24*30);//需要加上的时间
                       Date date3 = cal.getTime();
                       int ti = date2.compareTo(new Date());
                       if(ti == 1){//标到期 将标改为关闭
                           is.updategetbiBid(Bid.getCLOSE(),bid.getbId());
                       }
                   }

                }


            } catch (ParseException e) {
                e.printStackTrace();
            }

        }
    }
}
