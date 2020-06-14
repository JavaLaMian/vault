package com.vault.demo.controller.backstage;

import com.vault.demo.bean.Admin;
import com.vault.demo.bean.Bid;
import com.vault.demo.service.backstage.adxmn.selevicexmn;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/XMN")
public class backstageController {
    @Resource
    selevicexmn is;
    //首页
    @RequestMapping("/backstage")
    public ModelAndView backstage(ModelAndView mv, HttpSession session){
        Admin admin = (Admin)session.getAttribute("admin");
        if(admin == null){
            mv.setViewName("backstage/admin_login");
        }else{
            mv.setViewName("backstage/Bindex");
        }
        return mv;
    }
    @RequestMapping("/text")
    public ModelAndView text(ModelAndView mv){
        mv.setViewName("backstage/text");
        return mv;
    }
    //个人信息页面
    @RequestMapping("/profile")
    public ModelAndView profile(ModelAndView mv){
        mv.setViewName("backstage/Bprofile");
        return mv;
    }
    //修改密码页面
    @RequestMapping("/updatePwd")
    public ModelAndView updatePwd(ModelAndView mv){
        mv.setViewName("backstage/BupdatePwd");
        return mv;
    }
    //跳往404页面
    @RequestMapping("/B404")
    public ModelAndView B404(ModelAndView mv){
        mv.setViewName("backstage/404");
        return mv;
    }
    //理财订单页面
    @RequestMapping("/LiCaiDOC")
    public ModelAndView LiCaiDOC(ModelAndView mv){
        mv.setViewName("backstage/Blicaidoc");
        return mv;
    }
    //投标类别页面
    @RequestMapping("/BidList")
    public ModelAndView BidList(ModelAndView mv, Model model){
        List<Bid> list = is.Bidlist();
        model.addAttribute("list",list);
        mv.setViewName("backstage/BidList");
        return mv;
    }
    //去往投标新增页面
    @RequestMapping("/Badd_Bid")
    public ModelAndView Badd_Bid(ModelAndView mv){
        mv.setViewName("backstage/Badd_Bid");
        return mv;
    }
    //新增投标 专享标和新手标
    @RequestMapping("/addBid")
    public ModelAndView addBid(ModelAndView mv, Bid bid,String begTime,String gebTime) throws ParseException {
        //时间转换
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");//这个是你要转成后的时间的格式
        Date date1 = sdf.parse(begTime);//上线时间
        Date date2 = sdf.parse(gebTime);//封标时间
        bid.setBidTime(date1);
        bid.setExprie(date2);
        if(bid.getBidType() != bid.getNORM()){//新手标
            if(bid.getDeposit() != 2){//定期
                addbid1(bid,date1,1);
                is.addBid(bid);
            }else {//活期
                addbid1(bid,date1,2);
                is.addBid(bid);
            }
        }else {//优享标
            if(bid.getDeposit() != 2){//定期
                addbid1(bid,date1,3);
                is.addBid(bid);
            }else {//活期
                addbid1(bid,date1,4);
                is.addBid(bid);
            }
        }
        mv.setViewName("redirect:/XMN/BidList");
        return mv;
    }
    @RequestMapping("/updataBid")
    public ModelAndView updataBid(Bid bid,ModelAndView mv,Model model){
        List<Bid> list = is.selectgetBid(bid);
        model.addAttribute("list",list);
        mv.setViewName("backstage/Bidupdata");
        return mv;
    }
    //调用新增标期的方法    1 新手标 定期  2 新手标 活期   3优享标 定期   4 优享标活期
    public void addbid1(Bid bid,Date date1,int ok){
        if(ok == 1){
            //状态
            int time =date1.compareTo(new Date());  //预售和上线判断
            System.out.println(time);
            if(time == -1){//预售（READY=1）
                bid.setBidStatus(Bid.getREADY());
            }else {//在售（ON=0）
                bid.setBidStatus(Bid.getNO());
            }
            //问答
            bid.setQuestion("null");
            bid.setAnswer("null");
            //用户交易次数限制 默认3次
            bid.setDealCount(3);
            //最长投标时间
            bid.setMaxTime(12);
            //转让期 1 为一个月
            bid.setTransLine("1");
        }else if(ok ==2){
            //状态
            int time =date1.compareTo(new Date());  //预售和上线判断
            System.out.println(time);
            if(time == -1 ){//预售（READY=1）
                bid.setBidStatus(Bid.getREADY());
            }else {//在售（ON=0）
                bid.setBidStatus(Bid.getNO());
            }
            //问答
            bid.setQuestion("null");
            bid.setAnswer("null");
            //用户交易次数限制 默认3次
            bid.setDealCount(3);
            //最长投标时间
            bid.setMaxTime(12);
            //没有锁定期时间
            bid.setClockLine("0");
            //转让期 1 为一个月
            bid.setTransLine("1");
        } else if(ok == 3){
            //状态
            int time =date1.compareTo(new Date());  //预售和上线判断
            System.out.println(time);
            if(time == -1){//预售（READY=1）
                bid.setBidStatus(Bid.getREADY());
            }else {//在售（ON=0）
                bid.setBidStatus(Bid.getNO());
            }
            //问答
            bid.setQuestion("null");
            bid.setAnswer("null");
            //没有新手利率
            bid.setRewardRate((float) 0.00);
            //用户交易次数限制 默认3次
            bid.setDealCount(3);
            //最长投标时间
            bid.setMaxTime(12);
            //转让期 1 为一个月
            bid.setTransLine("1");
        } else if(ok == 4){
            //状态
            int time =date1.compareTo(new Date());  //预售和上线判断
            System.out.println(time);
            if(time == -1){//预售（READY=1）
                bid.setBidStatus(Bid.getREADY());
            }else {//在售（ON=0）
                bid.setBidStatus(Bid.getNO());
            }
            //问答
            bid.setQuestion("null");
            bid.setAnswer("null");
            //没有新手利率
            bid.setRewardRate((float) 0.00);
            //用户交易次数限制 默认3次
            bid.setDealCount(3);
            //最长投标时间
            bid.setMaxTime(12);
            //没有锁定期时间
            bid.setClockLine("0");
            //转让期 1 为一个月
            bid.setTransLine("1");
        }

    }
}
