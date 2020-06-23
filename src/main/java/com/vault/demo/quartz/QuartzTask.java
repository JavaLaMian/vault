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
//        List<Bid> list = is.Bidlist();
//        Bid bid = new Bid();
//        for(int i=0;i<list.size();i++){
//            bid = list.get(i);//将参数遍历到bean// 新手标 优享标的时间
//            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
//            String bidTime = simpleDateFormat.format(bid.getBidTime());//开始时间
//            String exprie = simpleDateFormat.format(bid.getExprie());//封标期时间
//            try {
//                Date date1 =simpleDateFormat.parse(bidTime);//上线时间
//                Date date = simpleDateFormat.parse(exprie);//封标期时间
//                int time = date1.compareTo(new Date());//上线时间小于当前时间 -1
//                int time1 = date.compareTo(new Date());//封标时间小于当前时间 -1
//                Float summoeny = bid.getSumLimit();//总体累计限额
//                int id = is.selectgetByid();
//                Float moeny = is.selectBidmoney(id); //查询出有哪些标投过，再根据id去查投了多少钱
//                if(bid.getBidType() == Bid.getNEWHAND() || bid.getBidType() == Bid.getNORM()){//新手标和优享标
//                   if(bid.getBidStatus() == Bid.getREADY()){//预售标
////                        System.out.println(time+"时间"+time1+"id"+bid.getbId());
//                        if(time == -1 && time1 != -1){//在售（ON=0）
//                            is.updategetbiBid(Bid.getNO(),bid.getbId());
//                        }
//                    }else if(bid.getBidStatus() == Bid.getNO()){//在售标
//                       if((time == -1 && time1 == -1) || ( moeny >= summoeny)){//售罄标
//                           is.updategetbiBid(Bid.getEMPTY(),bid.getbId());
//                       }
//                   }else if(bid.getDeposit() == 1 && bid.getBidStatus() == Bid.getEMPTY()){//定期售罄标
//                       if(bid.getClockLine().equals("1")){//定期为3个月的
//                           Calendar cal = Calendar.getInstance();//创建时间相加
//                           cal.setTime(date);
//                           cal.add(Calendar.HOUR,1*24*30*3);//需要加上的时间
//                           Date date2 = cal.getTime();
//                           int ti = date2.compareTo(new Date());
//                           if(ti == -1){//标到期 将标改为锁定期
//                               is.updategetbiBid(Bid.getLockup(),bid.getbId());
//                           }
//                       }
//                   }else if(bid.getDeposit() == 1 && bid.getBidStatus() == Bid.getLockup()){//定期锁定期标
//                       if(bid.getClockLine().equals("1")) {//定期为3个月的
//                           Calendar cal = Calendar.getInstance();//创建时间相加
//                           cal.setTime(date);
//                           cal.add(Calendar.HOUR,1*24*30*4);//需要加上的时间
//                           Date date3 = cal.getTime();//转让期时间
//                           int ti = date3.compareTo(new Date());
//                           if(ti != -1){//标为转让期
//                               is.updategetbiBid(Bid.getTransferss(),bid.getbId());
//                           }
//                       }
//                   }else if(bid.getDeposit() == 1 && bid.getBidStatus() == Bid.getTransferss()){//定期转让期
//                       if(bid.getClockLine().equals("1")) {//定期为3个月的
//                           Calendar cal = Calendar.getInstance();//创建时间相加
//                           cal.setTime(date);
//                           cal.add(Calendar.HOUR,1*24*30*4);//需要加上的时间
//                           Date date3 = cal.getTime();
//                           int ti = date3.compareTo(new Date());
//                           if(ti == -1){//标为关闭
//                               is.updategetbiBid(Bid.getCLOSE(),bid.getbId());
//                           }
//                       }
//                   }
//
//                }
//
//
//            } catch (ParseException e) {
//                e.printStackTrace();
//            }
//
//        }
    }
}
