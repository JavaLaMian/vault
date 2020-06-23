package com.vault.demo.quartz;

import com.vault.demo.bean.Bid;
import com.vault.demo.bean.Recharge;
import com.vault.demo.bean.Tender;
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
        List<String> tenderid = is.selectgetByid();
        int time = 0;
        int time1 = 0;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
        for(int i=0;i<list.size();i++){
            bid = list.get(i);//将参数遍历到bean// 新手标 优享标的时间
            String bidTime = simpleDateFormat.format(bid.getBidTime());//开始时间
            String exprie = simpleDateFormat.format(bid.getExprie());//封标期时间
            try {
                Date date1 =simpleDateFormat.parse(bidTime);//上线时间
                Date date = simpleDateFormat.parse(exprie);//封标期时间
                time = date1.compareTo(new Date());//上线时间小于当前时间 -1
                time1 = date.compareTo(new Date());//封标时间小于当前时间 -1
                if(bid.getBidType() == Bid.getNEWHAND() || bid.getBidType() == Bid.getNORM()){//新手标和优享标
                   if(bid.getBidStatus() == Bid.getREADY()){//预售标
                        if(time == -1 && time1 != -1){//在售（ON=0）
                            is.updategetbiBid(Bid.getNO(),bid.getbId());//设置标开启售
                        }
                    }else if(bid.getBidStatus() == Bid.getNO()){//在售标
                       if((time == -1 && time1 == -1)){//售罄标
                           is.updategetbiBid(Bid.getEMPTY(),bid.getbId());
                       }
                   }
                   else if(bid.getDeposit() == 1 && bid.getBidStatus() == Bid.getEMPTY()){//定期售罄标
                       Calendar cal = Calendar.getInstance();//创建时间相加
                       cal.setTime(date);
                       cal.add(Calendar.HOUR,1);//需要加上的时间
                       Date date2 = cal.getTime();
                       int ti = date2.compareTo(new Date());
                       if(ti == -1){//将标改为锁定期
                           is.updategetbiBid(Bid.getLockup(),bid.getbId());
                       }
                   }
                   else if(bid.getDeposit() == 1 && bid.getBidStatus() == Bid.getLockup()){//定期锁定期标
                       if(bid.getClockLine().equals("1")) {//定期为3个月的
                           Calendar cal = Calendar.getInstance();//创建时间相加
                           cal.setTime(date);
                           cal.add(Calendar.HOUR,1*24*30*3);//需要加上的时间
                           Date date3 = cal.getTime();//转让期时间
                           int ti = date3.compareTo(new Date());
                           if(ti == -1){//标为转让期
                               is.updategetbiBid(Bid.getTransferss(),bid.getbId());
                           }
                       }
                       else if(bid.getClockLine().equals("2")) {//定期为6个月的
                           Calendar cal = Calendar.getInstance();//创建时间相加
                           cal.setTime(date);
                           cal.add(Calendar.HOUR,1*24*30*6);//需要加上的时间
                           Date date3 = cal.getTime();//转让期时间
                           int ti = date3.compareTo(new Date());
                           if(ti == -1){//标为转让期
                               is.updategetbiBid(Bid.getTransferss(),bid.getbId());
                           }
                       }
                       else if(bid.getClockLine().equals("3")) {//定期为12个月的
                           Calendar cal = Calendar.getInstance();//创建时间相加
                           cal.setTime(date);
                           cal.add(Calendar.HOUR,1*24*30*12);//需要加上的时间
                           Date date3 = cal.getTime();//转让期时间
                           int ti = date3.compareTo(new Date());
                           if(ti == -1){//标为转让期
                               is.updategetbiBid(Bid.getTransferss(),bid.getbId());

                           }
                       }
                   }
                   else if(bid.getDeposit() == 1 && bid.getBidStatus() == Bid.getTransferss()){//定期转让期
                       if(bid.getClockLine().equals("1")) {//定期为3个月的
                           Calendar cal = Calendar.getInstance();//创建时间相加
                           cal.setTime(date);
                           cal.add(Calendar.HOUR,1*24*30*4);//需要加上的时间
                           Date date3 = cal.getTime();
                           int ti = date3.compareTo(new Date());
                           if(ti == -1){//标为关闭
                               is.updategetbiBid(Bid.getCLOSE(),bid.getbId());

                           }
                       }  else if(bid.getClockLine().equals("2")) {//定期为6个月的
                           Calendar cal = Calendar.getInstance();//创建时间相加
                           cal.setTime(date);
                           cal.add(Calendar.HOUR,1*24*30*7);//需要加上的时间
                           Date date3 = cal.getTime();//转让期时间
                           int ti = date3.compareTo(new Date());
                           if(ti == -1){//标为转让期
                               is.updategetbiBid(Bid.getCLOSE(),bid.getbId());
                           }
                       }  else if(bid.getClockLine().equals("3")) {//定期为12个月的
                           Calendar cal = Calendar.getInstance();//创建时间相加
                           cal.setTime(date);
                           cal.add(Calendar.HOUR,1*24*30*13);//需要加上的时间
                           Date date3 = cal.getTime();//转让期时间
                           int ti = date3.compareTo(new Date());
                           if(ti == -1){//标为转让期
                               is.updategetbiBid(Bid.getCLOSE(),bid.getbId());

                           }
                       }
                   }
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }

        }
        for(int j=0;j<tenderid.size();j++){
            Float summoeny = bid.getSumLimit();//总体累计限额
            Float moeny = is.selectBidmoney(Integer.valueOf(tenderid.get(j))); //查询出有哪些标投过，再根据id去查投了多少钱
            if(bid.getBidType() == Bid.getNEWHAND() || bid.getBidType() == Bid.getNORM()) {//新手标和优享标
                if(bid.getBidStatus() == Bid.getNO()) {//在售标
                    if ((time == -1 && time1 == -1) || (moeny >= summoeny)) {//售罄标
                        is.updategetbiBid(Bid.getEMPTY(), bid.getbId());
                    }
                }
            }
            if(bid.getDeposit() == 1 && bid.getBidStatus() == Bid.getTransferss()){//定期转让期
                if(bid.getClockLine().equals("1")) {//定期为3个月的
                    //获取转让期时间 加上一个小时 小于当前就
                    String exprie = simpleDateFormat.format(bid.getExprie());//封标期时间
                    try {
                        Date date = simpleDateFormat.parse(exprie);//封标期时间
                        Calendar cal = Calendar.getInstance();//创建时间相加
                        cal.setTime(bid.getExprie());
                        cal.add(Calendar.HOUR,(1*24*30*3)+1);//需要加上的时间
                        Date date3 = cal.getTime();//转让期时间
                        int ti = date3.compareTo(new Date());
                        if(ti != -1){
                            List<String> lis=is.selectgetBytenid(Integer.valueOf(bid.getbId()));//查询出这个标有多少个人投了
                            for(int p =0;p<lis.size();p++){//人数
                                List<Tender> moneylist = is.slecttendermoney(bid.getbId(),Integer.valueOf(lis.get(p)));
                                for(int q=0;q<moneylist.size();q++){//投了多少笔
                                    Tender qq = moneylist.get(q);
                                    try {
                                        Date jieshu = simpleDateFormat.parse( simpleDateFormat.format(qq.getTenTime()));//获取到他的投标时间
                                        Date kais = simpleDateFormat.parse(simpleDateFormat.format(bid.getBidTime()));//标开始时间
                                        int days = (int)((jieshu.getTime()-kais.getTime()) / (1000*3600*24));//求出时间
                                        Float mymoney = ((bid.getRate()+bid.getRewardRate())/100/12)*qq.getTenMoney()*(3+days/30);
                                        //查询出用户的余额然后相加
                                        Float usermoney = is.seleUsermoney(Integer.valueOf(lis.get(p)));
                                        is.updateuserMoney((usermoney+mymoney),Integer.valueOf(lis.get(p)));
                                        System.out.println("金钱"+(usermoney+mymoney)+"id"+Integer.valueOf(lis.get(p)));
                                        Recharge recharge = new Recharge();
                                        recharge.setuId(Integer.valueOf(lis.get(p)));//用户id
                                        recharge.setReMoney(usermoney);
                                        recharge.setReTime(new Date());
                                        recharge.setBankId(0);
                                        recharge.setBankName("利息金额");
                                        is.addusermoney(recharge);
                                        System.out.println(usermoney);
                                    } catch (ParseException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                        }
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                }
            }
        }
    }
}
