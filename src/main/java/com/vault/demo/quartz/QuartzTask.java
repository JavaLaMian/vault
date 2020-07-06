package com.vault.demo.quartz;

import com.vault.demo.bean.*;
import com.vault.demo.dao.PerBidDao;
import com.vault.demo.dao.loan.ActionDao;
import com.vault.demo.dao.loan.LoanDao;
import com.vault.demo.service.backstage.adxmn.selevicexmn;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import javax.annotation.Resource;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class QuartzTask implements Job {
    public double  summoney = 0;
    @Resource
    selevicexmn is;

    @Resource
    LoanDao loanDao;

    @Resource
    ActionDao actionDao;

    @Resource
    PerBidDao perBidDao;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        //查询出所有标 新手标和优享标
        List<Bid> list = is.Bidlist();
        Bid bid = new Bid();
        Tender tender = new Tender();
        List<String> tenderid = is.selectgetByid();
        //获取到每个用户投标的结束时间
        List<Tender> tenderlist = is.tenderlist();
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
                           cal.add(Calendar.HOUR,1*24*30*3);//需要加上的时间
                           Date date3 = cal.getTime();//转让期时间
                           int ti = date3.compareTo(new Date());
                           if(ti == -1){//将标关闭
                               is.updategetbiBid(Bid.getCLOSE(),bid.getbId());
                           }
                       }else if(bid.getClockLine().equals("6")) {//定期为6个月的
                           Calendar cal = Calendar.getInstance();//创建时间相加
                           cal.setTime(date);
                           cal.add(Calendar.HOUR,1*24*30*6);//需要加上的时间
                           Date date3 = cal.getTime();//转让期时间
                           int ti = date3.compareTo(new Date());
                           if(ti == -1){//将标关闭
                               is.updategetbiBid(Bid.getCLOSE(),bid.getbId());
                           }
                       }else if(bid.getClockLine().equals("12")) {//定期为12个月的
                           Calendar cal = Calendar.getInstance();//创建时间相加
                           cal.setTime(date);
                           cal.add(Calendar.HOUR,1*24*30*12);//需要加上的时间
                           Date date3 = cal.getTime();//转让期时间
                           int ti = date3.compareTo(new Date());
                           if(ti == -1){//将标关闭
                               is.updategetbiBid(Bid.getCLOSE(),bid.getbId());
                           }
                       }
                   }

                }
                for(int p=0;p<tenderlist.size();p++){
                    tender = tenderlist.get(p);
                    String userexprie = simpleDateFormat.format(tender.getTenCicle());//获取到用户投标的最后时间
                    Date dateexprie=simpleDateFormat.parse(userexprie);
                    int timeee = dateexprie.compareTo(new Date());
                    if(timeee == -1){//到期时间小于当前时间
                        if(tender.getTenType() != 5){//判断是否进行过操作
                            //当用户投的标的时间到了就开始卖
                            if(bid.getClockLine().equals("3")  && tender.getbId() == bid.getbId()){
                                double n = 3.0;//三个月
                                Date jieshu = simpleDateFormat.parse( simpleDateFormat.format(tender.getTenTime()));//获取到他的投标时间
                                Date kais = simpleDateFormat.parse(simpleDateFormat.format(bid.getExprie()));//标结束时间
                                //86400000
                                double days = (int)((kais.getTime()-jieshu.getTime()) / (1000*3600*24));//求出时间
                                if(days>=0 && days<=29){//判断这个人投了多久 小于29天按照天来算
                                    double SumMOney =usersummoney(bid.getRate(),bid.getRewardRate(),tender.getTenMoney(),days,String.valueOf(tender.getuId()),n);
                                    //修改用户代收利息剪掉当前
                                    Float tenMoney = is.selecttendertenMoney(Integer.valueOf(tender.getuId()),bid.getbId());
                                    is.updatetenderMoney((tenMoney-tender.getTenMoney()),Integer.valueOf(tender.getuId()),bid.getbId());
                                    is.updateuserMoney(SumMOney,Integer.valueOf(tender.getuId()));//将收益加上去
                                    Recharge recharge = new Recharge();
                                    recharge.setuId(Integer.valueOf(tender.getuId()));//用户id
                                    recharge.setReMoney((float)summoney);//金额
                                    recharge.setReTime(new Date());//时间
                                    recharge.setBankId(0);
                                    recharge.setBankName("利息金额");
                                    is.addusermoney(recharge);
                                    //将这个订单状态改为5 说明已经操作
                                    is.updateTender(tender.gettId(),5);
                                    System.out.println("操作成功");
                                }else if(days>=30 && (days/30)>=1){//大于30天按照月来算
                                    double SumMOney =userSumMoney(bid.getRate(),bid.getRewardRate(),tender.getTenMoney(),String.valueOf(tender.getuId()),n);
                                    //修改用户代收利息剪掉当前
                                    Float tenMoney = is.selecttendertenMoney(Integer.valueOf(tender.getuId()),bid.getbId());
                                    is.updatetenderMoney((tenMoney-tender.getTenMoney()),Integer.valueOf(tender.getuId()),bid.getbId());
                                    is.updateuserMoney(SumMOney,Integer.valueOf(tender.getuId()));//将收益加上去
                                    Recharge recharge = new Recharge();
                                    recharge.setuId(Integer.valueOf(tender.getuId()));//用户id
                                    recharge.setReMoney((float)summoney);//金额
                                    recharge.setReTime(new Date());//时间
                                    recharge.setBankId(0);
                                    recharge.setBankName("利息金额");
                                    is.addusermoney(recharge);
                                    //将这个订单状态改为5 说明已经操作
                                    is.updateTender(tender.gettId(),5);
                                    System.out.println("总金额"+SumMOney);
                                    System.out.println("操作成功");
                                }
                            }
                            else if(bid.getClockLine().equals("6")  && tender.getbId() == bid.getbId()){
                                double n = 6.0;//三个月
                                Date jieshu = simpleDateFormat.parse( simpleDateFormat.format(tender.getTenTime()));//获取到他的投标时间
                                Date kais = simpleDateFormat.parse(simpleDateFormat.format(bid.getExprie()));//标结束时间
                                //86400000
                                double days = (int)((kais.getTime()-jieshu.getTime()) / (1000*3600*24));//求出时间
                                if(days>0 && days<=29){//判断这个人投了多久 小于29天按照天来算
                                    double SumMOney =usersummoney(bid.getRate(),bid.getRewardRate(),tender.getTenMoney(),days,String.valueOf(tender.getuId()),n);
                                    //修改用户代收利息剪掉当前
                                    Float tenMoney = is.selecttendertenMoney(Integer.valueOf(tender.getuId()),bid.getbId());
                                    is.updatetenderMoney((tenMoney-tender.getTenMoney()),Integer.valueOf(tender.getuId()),bid.getbId());
                                    is.updateuserMoney(SumMOney,Integer.valueOf(tender.getuId()));//将收益加上去
                                    Recharge recharge = new Recharge();
                                    recharge.setuId(Integer.valueOf(tender.getuId()));//用户id
                                    recharge.setReMoney((float)summoney);//金额
                                    recharge.setReTime(new Date());//时间
                                    recharge.setBankId(0);
                                    recharge.setBankName("利息金额");
                                    is.addusermoney(recharge);
                                    //将这个订单状态改为5 说明已经操作
                                    is.updateTender(tender.gettId(),5);
                                    System.out.println("操作成功");
                                }else if(days>=30 && (days/30)>=1){//大于30天按照月来算
                                    double SumMOney =userSumMoney(bid.getRate(),bid.getRewardRate(),tender.getTenMoney(),String.valueOf(tender.getuId()),n);
                                    //修改用户代收利息剪掉当前
                                    Float tenMoney = is.selecttendertenMoney(Integer.valueOf(tender.getuId()),bid.getbId());
                                    is.updatetenderMoney((tenMoney-tender.getTenMoney()),Integer.valueOf(tender.getuId()),bid.getbId());
                                    is.updateuserMoney(SumMOney,Integer.valueOf(tender.getuId()));//将收益加上去
                                    Recharge recharge = new Recharge();
                                    recharge.setuId(Integer.valueOf(tender.getuId()));//用户id
                                    recharge.setReMoney((float)summoney);//金额
                                    recharge.setReTime(new Date());//时间
                                    recharge.setBankId(0);
                                    recharge.setBankName("利息金额");
                                    is.addusermoney(recharge);
                                    //将这个订单状态改为5 说明已经操作
                                    is.updateTender(tender.gettId(),5);
                                    System.out.println("总金额"+SumMOney);
                                    System.out.println("操作成功");
                                }
                            }
                            else if(bid.getClockLine().equals("9")  && tender.getbId() == bid.getbId()){
                                double n = 9.0;//三个月
                                Date jieshu = simpleDateFormat.parse( simpleDateFormat.format(tender.getTenTime()));//获取到他的投标时间
                                Date kais = simpleDateFormat.parse(simpleDateFormat.format(bid.getExprie()));//标结束时间
                                //86400000
                                double days = (int)((kais.getTime()-jieshu.getTime()) / (1000*3600*24));//求出时间
                                if(days>0 && days<=29){//判断这个人投了多久 小于29天按照天来算
                                    double SumMOney =usersummoney(bid.getRate(),bid.getRewardRate(),tender.getTenMoney(),days,String.valueOf(tender.getuId()),n);
                                    //修改用户代收利息剪掉当前
                                    Float tenMoney = is.selecttendertenMoney(Integer.valueOf(tender.getuId()),bid.getbId());
                                    is.updatetenderMoney((tenMoney-tender.getTenMoney()),Integer.valueOf(tender.getuId()),bid.getbId());
                                    is.updateuserMoney(SumMOney,Integer.valueOf(tender.getuId()));//将收益加上去
                                    Recharge recharge = new Recharge();
                                    recharge.setuId(Integer.valueOf(tender.getuId()));//用户id
                                    recharge.setReMoney((float)summoney);//金额
                                    recharge.setReTime(new Date());//时间
                                    recharge.setBankId(0);
                                    recharge.setBankName("利息金额");
                                    is.addusermoney(recharge);
                                    //将这个订单状态改为5 说明已经操作
                                    is.updateTender(tender.gettId(),5);
                                    System.out.println("操作成功");
                                }else if (days>=30 && (days/30)>=1){//大于30天按照月来算
                                    double SumMOney =userSumMoney(bid.getRate(),bid.getRewardRate(),tender.getTenMoney(),String.valueOf(tender.getuId()),n);
                                    //修改用户代收利息剪掉当前
                                    Float tenMoney = is.selecttendertenMoney(Integer.valueOf(tender.getuId()),bid.getbId());
                                    is.updatetenderMoney((tenMoney-tender.getTenMoney()),Integer.valueOf(tender.getuId()),bid.getbId());
                                    is.updateuserMoney(SumMOney,Integer.valueOf(tender.getuId()));//将收益加上去
                                    Recharge recharge = new Recharge();
                                    recharge.setuId(Integer.valueOf(tender.getuId()));//用户id
                                    recharge.setReMoney((float)summoney);//金额
                                    recharge.setReTime(new Date());//时间
                                    recharge.setBankId(0);
                                    recharge.setBankName("利息金额");
                                    is.addusermoney(recharge);
                                    //将这个订单状态改为5 说明已经操作
                                    is.updateTender(tender.gettId(),5);
                                    System.out.println("总金额"+SumMOney);
                                    System.out.println("操作成功");
                                }
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
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        //每次检测有没有到最短还款时间，到期改贷款状态为待还款
        if (true){
            List<Loan> loanList = loanDao.listForStatusEq1();

            if (loanList.size() > 0){
                for (Loan loan : loanList){
                    System.out.println("算还款："+loan);

                    Calendar calendar = Calendar.getInstance();

                    SimpleDateFormat simpleDateFormatEX = new SimpleDateFormat("yyyy-MM-dd");

                    String thisDayStr = simpleDateFormat.format(calendar.getTime());

                    Date thisDay = new Date();

                    try {
                        thisDay = simpleDateFormat.parse(thisDayStr);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    Action action = actionDao.selectActionByLId(loan);

                    List<Map> perBidMap = perBidDao.selectPerBidByPerBidId(loan);
                    PerBid perBid = new PerBid();
                    perBid.setPerBid((Integer) perBidMap.get(0).get("perBid"));
                    perBid.setBidStatus((Integer) perBidMap.get(0).get("bidStatus"));

                    int compare = thisDay.compareTo(action.getMinRepayTime());

                    if (compare == 1 || compare == 0){
                        loan.setLoanStatue(2);
                        action.setAcStatus(2);
                        perBid.setBidStatus(3);

                        loanDao.updateLoanStatus(loan);
                        actionDao.updateStatusByAId(action);
                        perBidDao.updatePerBidStatus(perBid);
                    }
                }
            }
        }


        //每次检测有没有到最长还款时间，到期改贷款状态为坏账
        if (true){
            List<Loan> loanList = loanDao.listForStatusEq2();

            if (loanList.size() > 0){
                for (Loan loan : loanList){
                    System.out.println("算坏账："+loan);

                    Calendar calendar = Calendar.getInstance();

                    SimpleDateFormat simpleDateFormatEX = new SimpleDateFormat("yyyy-MM-dd");

                    String thisDayStr = simpleDateFormat.format(calendar.getTime());

                    Date thisDay = new Date();

                    try {
                        thisDay = simpleDateFormat.parse(thisDayStr);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    Action action = actionDao.selectActionByLId(loan);

                    List<Map> perBidMap = perBidDao.selectPerBidByPerBidId(loan);
                    PerBid perBid = new PerBid();
                    perBid.setPerBid((Integer) perBidMap.get(0).get("perBid"));
                    perBid.setBidStatus((Integer) perBidMap.get(0).get("bidStatus"));

                    int compare = thisDay.compareTo(action.getMaxRepayTime());

                    if (compare == 1 || compare == 0){
                        loan.setLoanStatue(6);
                        action.setAcStatus(4);
                        perBid.setBidStatus(2);

                        loanDao.updateLoanStatus(loan);
                        actionDao.updateStatusByAId(action);
                        perBidDao.updatePerBidStatus(perBid);
                    }
                }
            }
        }
    }


    public double usersummoney(Float ratee,Float rewardrate,Float tenmoney,double days,String p,double n){
        DecimalFormat decimalFormat = new DecimalFormat("#.00");
        double rate = ratee+rewardrate;//总利率
        double b = 100.00;//将12转换为0.12
        double c = 365.00;//一年
        double usertenmoney = (tenmoney*10000);//用户投的金额
        String d = decimalFormat.format(rate/b/c*usertenmoney);//将一天的钱转换成string
        double onedaymoney = Double.parseDouble(d);//一天的钱
        //计算用户先投的时间的钱
        String e = decimalFormat.format(onedaymoney*days);
        double daysmoney = Double.parseDouble(e);
        //计算定期三个月的钱
        double t = 12.00;//12月
        String tt = decimalFormat.format(rate/b/t*usertenmoney*n);
        double Marchmoney = Double.parseDouble(tt);
        //将全部金钱加起来
        summoney = Marchmoney+daysmoney;
        //获取到这个用户有多少钱然后加上收益
        Float ss = is.seleUsermoney(Integer.valueOf(p));
        String ppts = decimalFormat.format(ss+summoney);
        double sum = Double.parseDouble(ppts);
        //总资金
        double SumMOney = sum+usertenmoney;
        return SumMOney;
    }


    public double userSumMoney(Float ratee,Float rewardrate,Float tenmoney,String p,double n){
        DecimalFormat decimalFormat = new DecimalFormat("#.00");
        double rate = ratee+rewardrate;//总利率
        double b = 100.00;//将12转换为0.12
        double usertenmoney = (tenmoney*10000);//用户投的金额
        //计算定期三个月的钱
        double t = 12.00;//12月
        String tt = decimalFormat.format(rate/b/t*usertenmoney*n);
        double Marchmoney = Double.parseDouble(tt);
        //将全部金钱加起来
        summoney = Marchmoney;
        //获取到这个用户有多少钱然后加上收益
        Float ss = is.seleUsermoney(Integer.valueOf(p));
        String ppts = decimalFormat.format(ss+summoney);
        double sum = Double.parseDouble(ppts);
        //总资金
        double SumMOney = sum+usertenmoney;
        return SumMOney;
    }
}
