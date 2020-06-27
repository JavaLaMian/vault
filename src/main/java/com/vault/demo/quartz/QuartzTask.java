package com.vault.demo.quartz;

import com.vault.demo.bean.Bid;
import com.vault.demo.bean.Recharge;
import com.vault.demo.bean.Tender;
import com.vault.demo.service.backstage.adxmn.selevicexmn;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import javax.annotation.Resource;
import java.math.BigDecimal;
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
                   }else if (bid.getBidStatus() == Bid.getEMPTY()){//售罄标
                       if(bid.getClockLine().equals("3")) {//定期为3个月的
                           Calendar cal = Calendar.getInstance();//创建时间相加
                           cal.setTime(date);
                           cal.add(Calendar.HOUR,1*24*30*4);//需要加上的时间
                           Date date3 = cal.getTime();//转让期时间
                           int ti = date3.compareTo(new Date());
                           if(ti == -1){//将标关闭
                               is.updategetbiBid(Bid.getCLOSE(),bid.getbId());
                           }
                       }else if(bid.getClockLine().equals("6")) {//定期为6个月的
                           Calendar cal = Calendar.getInstance();//创建时间相加
                           cal.setTime(date);
                           cal.add(Calendar.HOUR,1*24*30*7);//需要加上的时间
                           Date date3 = cal.getTime();//转让期时间
                           int ti = date3.compareTo(new Date());
                           if(ti == -1){//将标关闭
                               is.updategetbiBid(Bid.getCLOSE(),bid.getbId());
                           }
                       }else if(bid.getClockLine().equals("12")) {//定期为12个月的
                           Calendar cal = Calendar.getInstance();//创建时间相加
                           cal.setTime(date);
                           cal.add(Calendar.HOUR,1*24*30*13);//需要加上的时间
                           Date date3 = cal.getTime();//转让期时间
                           int ti = date3.compareTo(new Date());
                           if(ti == -1){//将标关闭
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
            String exprie = simpleDateFormat.format(bid.getExprie());//封标期时间
            Date date = null;//封标期时间
            try {
                date = simpleDateFormat.parse(exprie);
                if(bid.getBidType() == Bid.getNEWHAND() || bid.getBidType() == Bid.getNORM()) {//新手标和优享标
                    if(bid.getBidStatus() == Bid.getNO()) {//在售标
                        if ((time == -1 && time1 == -1) || (moeny >= summoeny)) {//售罄标
                            is.updategetbiBid(Bid.getEMPTY(), bid.getbId());
                        }
                    }
                }
                //当标的时间到了转让期时
                if(bid.getClockLine().equals("3")){
                    Calendar cal = Calendar.getInstance();//创建时间相加
                    cal.setTime(date);
                    cal.add(Calendar.HOUR,(1*24*30*3)+24);//需要加上的时间
                    Date date3 = cal.getTime();//转让期时间
                    int ti = date3.compareTo(new Date());
                    if(ti != -1){
                        List<String> lis=is.selectgetBytenid(Integer.valueOf(bid.getbId()));//查询出这个标有多少个人投了
                        for(int p =0;p<lis.size();p++){//人数
                            List<Tender> moneylist = is.slecttendermoney(bid.getbId(),Integer.valueOf(lis.get(p)));
                            for(int q=0;q<moneylist.size();q++){//投了多少笔
                                Tender qq = moneylist.get(q);
                                try {
                                    System.out.println("进来了");
                                    Date jieshu = simpleDateFormat.parse( simpleDateFormat.format(qq.getTenTime()));//获取到他的投标时间
                                    Date kais = simpleDateFormat.parse(simpleDateFormat.format(bid.getBidTime()));//标开始时间
                                    int days = (int)((jieshu.getTime()-kais.getTime()) / (1000*3600*24));//求出时间
                                    if(days<=29){//判断这个人投了多久 小于29天按照天来算
                                        //利率
                                        BigDecimal rate = new BigDecimal(String.valueOf((bid.getRate()+bid.getRewardRate())/100/12/30));
                                        //用户金额
                                        BigDecimal usermoney = new BigDecimal(qq.getTenMoney());
                                        //时间
                                        BigDecimal daytime = new BigDecimal(days);
                                        //三个月
                                        BigDecimal threedaytime = new BigDecimal("90");
                                        //一天产生的金额
                                        BigDecimal onedaymoney = rate.multiply(usermoney);
                                        //一个月内产生的金额
                                        BigDecimal monthmoney = onedaymoney.multiply(daytime);
                                        //三个月来产生的金额
                                        BigDecimal threemonehmoney = onedaymoney.multiply(threedaytime);
                                        //全部时间的总金额
                                        BigDecimal summoney = monthmoney.add(threemonehmoney);
                                        //个人总金额
                                        BigDecimal usermoney1 = new BigDecimal(is.seleUsermoney(Integer.valueOf(lis.get(p))));
                                        //总金额
                                        BigDecimal sum = usermoney1.add(summoney);
//                                        is.updateuserMoney(sum,Integer.valueOf(lis.get(p)));//将收益加上去
                                        System.out.println(sum);
                                    }
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
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
