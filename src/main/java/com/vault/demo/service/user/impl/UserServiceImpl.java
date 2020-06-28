package com.vault.demo.service.user.impl;

import com.vault.demo.bean.*;
import com.vault.demo.dao.BankDao;
import com.vault.demo.dao.UserimfDao;
import com.vault.demo.dao.WorryCallDao;
import com.vault.demo.dao.loan.CreditDao;
import com.vault.demo.dao.test.BidDao;
import com.vault.demo.service.integral.integralService;
import com.vault.demo.service.user.UserService;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserimfDao dao;
    @Resource
    private BankDao bdao;
    @Resource
    private CreditDao cdao;
    @Resource
    private BidDao bidDao;
    @Resource
    private WorryCallDao wcDao;
    @Resource
    private integralService iDao;

    @Override
    public int addUserImf(Userimf user) {
        int lie = dao.addUser(user);
        dao.updateUserAccount(user.getuId(),"xiaomuniu"+user.getuId());

        MyIntegral myIntegral = new MyIntegral();
        myIntegral.setuId(user.getuId());
        myIntegral.setTotal(10);
        myIntegral.setChange(10);
        myIntegral.setChangeType("新人注册奖励");
        myIntegral.setConversionTime(new Date());
        iDao.conversionAdd(myIntegral);

        Bounty bounty = new Bounty();
        bounty.setBoMoney(100);
        bounty.setuId(user.getuId());
        bounty.setBoTime(new Date());
        bounty.setBoType(4);
        iDao.bountyAdd(bounty);
        return lie;
    }

    @Override
    public void upUser(Userimf userimf) {
        dao.upUser(userimf);
    }

    @Override
    public String getEmailMa(String shou,String type) throws EmailException {
        if("".equals(shou)){
            return "";
        }
        //type 发送邮件的类型 zc 注册
        HtmlEmail email = new HtmlEmail();
        String ma = "";
        for(int i =0;i < 6;i++){
            ma += ""+(int)(Math.random()*10);
        }
        System.out.println(ma);
        String text = "";
        if("zc".equals(type)){
            text = "欢迎注册小刘理财，您的邮箱验证码为 "+ma+",若非本人操作请忽略";
        }else if("dl".equals(type)){
            text = "用户您好，您的邮箱登陆验证码为 "+ma;
        }else if("zh".equals(type)){
            text = "您正在修改该邮箱绑定账号的密码，验证码为 "+ma+",若非本人操作请忽略";
        }else if("smrz".equals(type)){
            text = "您正在进行实名认证，验证码为 "+ma+",若非本人操作请忽略";
        }else if("jymm_rz".equals(type)){
            text = "您正在设置交易密码，验证码为 "+ma+",若非本人操作请忽略";
        }else if("jymm_up".equals(type)){
            text = "您正在重设交易密码，验证码为 "+ma+",若非本人操作请忽略";
        }else if("dlmm_up".equals(type)){
            text = "您正在重设登陆密码，验证码为 "+ma+",若非本人操作请忽略";
        }else if("eamil_up".equals(type)){
            text = "您正在重设关联邮箱，验证码为 "+ma+",若非本人操作请忽略";
        }else if("eamil_bind".equals(type)){
            text = "您正在重设关联邮箱，验证码为 "+ma+",若非本人操作请忽略";
        }else if("upcard".equals(type)){
            text = "您正在更改绑定银行卡，验证码为 "+ma+",若非本人操作请忽略";
        }

        email.setHostName("smtp.163.com");//邮箱的SMTP服务器，一般123邮箱的是smtp.123.com,qq邮箱为smtp.qq.com
        email.setCharset("utf-8");//设置发送的字符类型 ALNFMBFNJZMHNLTA
        email.addTo(shou);//设置收件人wgmecaupoemqdhgj
        email.setFrom("wy2229077248@163.com","验证中心");//发送人的邮箱为自己的，用户名可以随便填
        email.setAuthentication("wy2229077248@163.com","ALNFMBFNJZMHNLTA");//设置发送人到的邮箱和用户名和授权码(授权码是自己设置的)
        email.setSubject("小刘理财");//设置发送主题
        email.setMsg(text);//设置发送内容
        email.send();//进行发送
        return ma;
    }

    @Override
    public Userimf pandEmail(String email,String type) {
        Userimf user = new Userimf();
        if("e".equals(type)){
            user.setEmail(email);//根据邮箱判断
        }
        else if("a".equals(type)){
            user.setAccount(email);//根据账号判断
        }
        return dao.selectByUserimf(user);
    }

    @Override
    public Userimf logPadUser(Userimf userimf) {
        return dao.selectOneByLogin(userimf);
    }

    @Override
    public int updetaPwd(String email, String pwd) {
        return dao.updateUserPwd(pwd,email);
    }

    @Override
    public void bindCredit(Credit credit) {
        cdao.insert(credit);
    }

    @Override
    public void bindBank(UserBank userBank) {
        bdao.bindBank(userBank);
    }

    @Override
    public void unbindBank(UserBank userBank) {
        bdao.unBank(userBank);
    }

    @Override
    public void upbindBank(UserBank userBank) {
        bdao.upBank(userBank);
    }

    @Override
    public UserBank getBC(int uId) {
        return bdao.getBC(uId);
    }

    @Override
    public Credit getCredit(int uId) {
        Userimf userimf = new Userimf();
        userimf.setuId(uId);
        return cdao.selectCreditByUserId(userimf);
    }

    @Override
    public Map daiShou(Userimf user) {
        int uid = user.getuId();
        Map map = new HashMap();
        List<Map> mlist = bidDao.comUserList(uid);

        BigDecimal zon = new BigDecimal("0"); //当前用户投标总额
        if(mlist.size() != 0){
            for(int i = 0;i < mlist.size();i++){
                Map map1 = mlist.get(i);
                BigDecimal tou = new BigDecimal(map1.get("tenMoney")+"");
                zon = zon.add(tou);
            }
            BigDecimal wan = new BigDecimal("10000");
            zon = zon.multiply(wan);//定标转换万
            map.put("money",""+zon);
            map.put("list",mlist);
        }else {
            map.put("money",""+zon);
            map.put("list",null);
        }
        BigDecimal yu = new BigDecimal(""+user.getAvaBalance()); //当前用户余额
        map.put("zonMon",""+zon.add(yu));//计算总资产

        List<Map> rlist = cdao.getRechargeMax(uid);
        List<Map> wlist = cdao.getWithdrawMax(uid);
        Map map1 = rlist.get(0);//累计充值与提现
        if(map1 == null){
            map.put("rmax","0.0");
        }else {
            map.put("rmax",map1.get("rmon")+"");
        }

        Map map2 = wlist.get(0);//累计充值与提现
        if(map2 == null){
            map.put("wmax","0.0");
        }else {
            map.put("wmax",wlist.get(0).get("wmon")+"");
        }
        return map;
    }

    @Override
    public void bindWorryCall(WorryCall worryCall) {
        wcDao.saveWorryCall(worryCall);
    }

    @Override
    public void upWorryCall(WorryCall worryCall) {
        wcDao.upWorryCall(worryCall);
    }

    @Override
    public WorryCall getWorryCall(Userimf userimf) {
        return wcDao.selectWorryByUId(userimf);
    }

    @Override
    public int userChongTi(String type, String money, Userimf userimf) {
        BigDecimal user = new BigDecimal(""+userimf.getAvaBalance());
        BigDecimal bian = new BigDecimal(""+money);
        UserBank bank = getBC(userimf.getuId());
        if("cz".equals(type)){
            user = user.add(bian);
            float bh = user.floatValue();
            bidDao.moneyUserId(bh,userimf.getuId());

            Recharge recharge = new Recharge();
            recharge.setuId(userimf.getuId());
            recharge.setReTime(new Date());
            recharge.setBankId(bank.getBankId());
            recharge.setBankName(bank.getBankName());
            recharge.setReMoney(bian.floatValue());
            return cdao.addRecharge(recharge);
        }else {
            if(!(user.compareTo(bian)== -1)){//用户余额大于提现额
                user = user.subtract(bian);
                float bh = user.floatValue();
                bidDao.moneyUserId(bh,userimf.getuId());

                Withdraw withdraw = new Withdraw();
                withdraw.setuId(userimf.getuId());
                withdraw.setWithTime(new Date());
                withdraw.setBankId(bank.getBankId());
                withdraw.setBankName(bank.getBankName());
                withdraw.setWithMoney(bian.floatValue());

                return cdao.addWithdraw(withdraw);
            }else {
                return 2;
            }
        }
    }

    @Override
    public List<Map> useZhiJinList(int uId) {
        List<Map> mlist = dao.selectUserZhijin(uId);
        return mlist;
    }

    @Override
    public List<Bounty> yhList(int uId) {
        return dao.selectBounty(uId,0);
    }

    @Override
    public Boolean bindReferee(Userimf refereerUser,Userimf curr) {
        if (dao.selectByUserimf(refereerUser)!=null){
            dao.upUser(curr);
            return true;
        }
        return false;
    }

    @Override
    public Map getChuJie(Userimf user) {
        List<String> slist = new ArrayList<>();
        List mlist = new ArrayList();

        List<Tender> tlist = bidDao.selTenderByTD(user.getuId());
        Map map = new HashMap();
        int s = tlist.size();
        for(int i = 0;i < 7;i++){
            if(i < s){
                slist.add(i,getNowDate(tlist.get(i).getTenTime()));
                BigDecimal bian = new BigDecimal(""+tlist.get(i).getTenMoney());
                BigDecimal wan = new BigDecimal("10000");
                bian = bian.multiply(wan);
                mlist.add(i,bian.floatValue());
            }else {
                slist.add(i,"----");
                mlist.add(i,0.0);
            }
        }
        map.put("time",slist);
        map.put("value",mlist);
        return map;
    }

    private static String getNowDate(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(date);
        return dateString;
    }
}
