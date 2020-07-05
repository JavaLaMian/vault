package com.vault.demo.controller.backstage;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.vault.demo.bean.*;
import com.vault.demo.service.backstage.adxmn.selevicexmn;
import com.vault.demo.service.backstage.car.BackCarService;
import com.vault.demo.service.backstage.credit.BackCreditService;
import com.vault.demo.service.backstage.house.BackHouseService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/XMN")
public class backstageController {
    @Resource
    selevicexmn is;
    @Resource
    BackCreditService bcs;
    @Resource
    BackCarService bcas;
    @Resource
    BackHouseService bhs;
    //首页
    @RequestMapping("/backstage")
    public ModelAndView backstage(ModelAndView mv,Model model){
        List<Map> list = is.Bidlistall();
        model.addAttribute("list",list);
        mv.setViewName("backstage/Bindex");
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
    public ModelAndView BidList(ModelAndView mv, Model model,Pager pager){
        //分页
        pager.titleSize = 10;//每页显示页数
        //查询出总条数
        pager.page(is.slectBidtotalTitle());
        int a = is.slectBidtotalTitle();
        if(a != 0){
            List<Bid> list = is.Bidlistpage((pager.thisPage-1)*pager.titleSize,pager.titleSize);
            System.out.println("第"+(pager.thisPage-1)+"页"+pager.titleSize+"记录数");
            model.addAttribute("list",list);
            model.addAttribute("pager",pager);
            System.out.println(pager.thisPage);
            mv.setViewName("backstage/BidList");
            return mv;
        }else {
            List<Bid> list = is.Bidlist();
            model.addAttribute("list",list);
            mv.setViewName("backstage/BidList");
            return mv;
        }
    }
    //去往贷款审核页面
    @RequestMapping("/Loan_List")
    public ModelAndView toLoanList(ModelAndView mv){
        mv.setViewName("backstage/loan_list");
        return mv;
    }
    //去往投标新增页面
    @RequestMapping("/Badd_Bid")
    public ModelAndView Badd_Bid(ModelAndView mv){
        mv.setViewName("backstage/Badd_Bid");
        return mv;
    }
    //理财首页轮播图
    @RequestMapping("/homeImg")
    public ModelAndView homeImg(ModelAndView mv){
        mv.setViewName("backstage/homeImg");
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
    //请修改页面
    @RequestMapping("/updataBid")
    public ModelAndView updataBid(Bid bid, ModelAndView mv, Model model, HttpSession session){
        List<Bid> list = is.selectgetBid(bid);
        for(int i=0;i<list.size();i++){
            bid = list.get(i);
        }
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");//开始时间
        String bidTime  = sdf.format(bid.getBidTime());
        String exprie  = sdf.format(bid.getExprie());
        session.setAttribute("bidTime",bidTime);
        session.setAttribute("exprie",exprie);

        model.addAttribute("list",list);
        mv.setViewName("backstage/Bidupdata");
        return mv;
    }
    //修改页面
    @RequestMapping("/BupdateBid")
    public ModelAndView BupdateBid(ModelAndView mv,Bid bid,String begTime,String gebTime) throws ParseException {
        //时间转换
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");//这个是你要转成后的时间的格式
        Date date1 = sdf.parse(begTime);//上线时间
        Date date2 = sdf.parse(gebTime);//封标时间
        bid.setBidTime(date1);
        bid.setExprie(date2);
        is.updateBid(bid);
        mv.setViewName("redirect:/XMN/BidList");
        return mv;
    }
    //删除投标信息
    @RequestMapping("/dateBid")
    public ModelAndView dateBid(ModelAndView mv,Bid bid){
        int ok = is.dateBid(bid);
        if(ok != 1){
            mv.setViewName("backstage/404");
        }else {
            mv.setViewName("redirect:/XMN/BidList");

        }
        return mv;
    }
    //去往用户信用审核页面
    @RequestMapping("/loanlist")
    public ModelAndView loanlist(ModelAndView mv){
        mv.setViewName("backstage/CreditList");
        return mv;
    }
    @ResponseBody
    @RequestMapping("/creditData")
    public JSONObject getCreditData() {
        JSONObject object = new JSONObject();
        List<Map> credits = bcs.getCreditAll();
        object.put("total", credits.size());
        object.put("rows", JSON.toJSON(credits));
        return object;
    }

    //根据id去用户审核页面
    @RequestMapping("/updateCredit")
    public ModelAndView updateCredit(ModelAndView mv,Model model,int creId){
        Credit credit = bcs.selCreditById(creId);
        model.addAttribute("credit",credit);
        model.addAttribute("user",bcs.selUserById(credit.getuId()));
        mv.setViewName("backstage/Creditupdate");
        return mv;
    }
    //审核信用信息
    @RequestMapping("/updateCreditOK")
    public String updateCreditOK(ModelAndView mv,String an,int creId){
        Credit credit = bcs.selCreditById(creId);
       // 0等待审核 1审核中 2审核完毕
        int ok = 2;
        credit.setCreditLV(an);//获取信用等级
        credit.setCreditUpdateTime(new Date());
        credit.setType(ok);
        is.updateCredit(credit);
        return "redirect:/XMN/loanlist";
    }
    //去往积分订单查询页面
    @RequestMapping("/userintegral")
    public ModelAndView userintegral(ModelAndView mv,Model model){
        List<Map> list = is.integralCoin();
        model.addAttribute("list",list);
        mv.setViewName("backstage/integralCion");
        return mv;
    }
    @RequestMapping("/integralList")
    public String integralList(Model model){
        List<Integral> integralList = is.integralList();
        model.addAttribute("integralList",integralList);
        return "backstage/integralList";
    }
    @RequestMapping("/Admin_information")
    public ModelAndView toAdminInformation(ModelAndView mv,Model model){
        return mv;
    }
    //打款追踪
    @RequestMapping("/Payment_track")
    public ModelAndView toPaPaymentTrack(ModelAndView mv){
        mv.setViewName("backstage/Paymenttrack");
        return mv;
    }
    @RequestMapping("/carProperty")
    public ModelAndView toCarProperty(ModelAndView mv){
        mv.setViewName("backstage/UserCarExamine");
        return mv;
    }
    @ResponseBody
    @RequestMapping("/getCarData")
    public JSONObject getCarData(){
        JSONObject object = new JSONObject();
        List<Map> cars = bcas.selCarAll();
        object.put("total", cars.size());
        object.put("rows", JSON.toJSON(cars));
        return object;
    }

    @RequestMapping("/toCarExamine")
    public String toCarExamine(Model m,int cId){
        Car car = bcas.selCarById(cId);
        Credit credit = bcas.selCreditById(car.getuId());
        Userimf userimf = bcs.selUserById(car.getuId());
        m.addAttribute("user",userimf);
        m.addAttribute("credit",credit);
        m.addAttribute("car",car);
        return "backstage/Car_Examine";
    }
    @RequestMapping("/carExamine")
    public String carExamine(int cId,int an){
        Car car = bcas.selCarById(cId);
        car.setStatus(an);
        bcas.updCarStatus(car);
        return "redirect:/XMN/carProperty";
    }
    @RequestMapping("/houseProperty")
    public ModelAndView toHouseProperty(ModelAndView mv){
        mv.setViewName("backstage/UserHouseExamine");
        return mv;
    }
    @ResponseBody
    @RequestMapping("/getHouseData")
    public JSONObject getHouseData(){
        JSONObject object = new JSONObject();
        List<Map> houses = bhs.selHosueAll();
        object.put("total", houses.size());
        object.put("rows", JSON.toJSON(houses));
        return object;
    }
    @RequestMapping("/toHouseExamine")
    public String toHouseExamine(Model m,int hId){
        House house = bhs.selHouseById(hId);
        Credit credit = bcas.selCreditById(house.getuId());
        Userimf userimf = bcs.selUserById(house.getuId());
        m.addAttribute("house",house);
        m.addAttribute("user",userimf);
        m.addAttribute("credit",credit);
        return "backstage/House_Examine";
    }
    @RequestMapping("/houseExamine")
    public String houseExamine(int hId,int an){
        House house = bhs.selHouseById(hId);
        house.setStatus(an);
        bhs.updHouseStatus(house);
        return "redirect:/XMN/houseProperty";
    }
//    @ResponseBody
//    @RequestMapping("/dashChartBarsCnt")
//    public JSONObject dashChartBarsCnt(){
//        JSONObject jsonObject = new JSONObject();
//
//    }
    //调用新增标期的方法    1 新手标 定期  2 新手标 活期   3优享标 定期   4 优享标活期
    public void addbid1(Bid bid,Date date1,int ok){
        if(ok == 1){
            //状态
            int time =date1.compareTo(new Date());  //预售和上线判断
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
