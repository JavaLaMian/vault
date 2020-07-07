package com.vault.demo.controller.user;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeWapPayModel;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.vault.demo.bean.*;
import com.vault.demo.common.CardUtil;
import com.vault.demo.config.AlipayConfig;
import com.vault.demo.bean.*;
import com.vault.demo.service.integral.integralService;
import com.vault.demo.service.user.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController2 {
    @Resource
    private UserService service;
    @Resource
    private integralService integralService;

    @RequestMapping("/toAO")
    public String toAO(HttpSession session,Model model){
        Userimf userimf = (Userimf) session.getAttribute("user");
        Map map = service.daiShou(userimf);
        userimf.setEmail(null);
        MyIntegral myIntegral = service.selectMyIntegral2(userimf.getuId());
        Userimf user = service.logPadUser(userimf);
        UserBank userBank = service.getBC(user.getuId());
        Credit credit = service.getCredit(user.getuId());
        Map ren = new HashMap();
        ren.put("bank",userBank);
        ren.put("cred",credit);
        session.setAttribute("ren",ren); //将用户认证信息存入session以判断
        session.setAttribute("user",user);
        session.setAttribute("sig",myIntegral);
        model.addAttribute("map",map);
        model.addAttribute("bank",userBank);
        return "user/AccountOverview";
    }

    @RequestMapping("/toAS")
    public String toAS(HttpSession session,Model model){
        Userimf user = (Userimf) session.getAttribute("user");
        try {
            Credit credit = service.getCredit(user.getuId());
            UserBank userBank = service.getBC(user.getuId());
            model.addAttribute("credit",credit);
            model.addAttribute("userBank",userBank);
            WorryCall worryCall = service.getWorryCall(user);
            model.addAttribute("worryCall",worryCall);
        }catch (Exception e){
            model.addAttribute("credit",null);
            model.addAttribute("userBank",null);
            model.addAttribute("worryCall",null);
        }
        return "user/AccountSafe";
    }
    @RequestMapping("/toApply")
    public String toApply(Model model){
        model.addAttribute("applyType","apply");
        return "user/apply";
    }

    @RequestMapping("/viewApply")
    public String viewApply(HttpSession session, Model model){
        Userimf user = (Userimf) session.getAttribute("user");
        Credit credit = service.getCredit(user.getuId());
        UserBank userBank = service.getBC(user.getuId());
        if(credit == null && userBank == null){
            model.addAttribute("applyType","apply");
        }else {
            model.addAttribute("applyType","view");
        }
        model.addAttribute("credit",credit);
        model.addAttribute("userBank",userBank);
        return "user/apply";
    }
    @RequestMapping("/updateApply")
    public String updateApply(Model model){
        model.addAttribute("applyType","update");
        return "user/apply";
    }
    @RequestMapping("/upcard")
    @ResponseBody
    public Boolean upcard(UserBank userBank, HttpSession session){
        Userimf user = (Userimf) session.getAttribute("user");
        userBank.setuId(user.getuId());
        userBank.setBcUserName(user.getuName());
//        System.out.println(user.toString());
//        System.out.println(userBank.toString());
//        System.out.println(service.getBC(user.getuId()));

        if (null!=service.getBC(user.getuId())){
            service.upbindBank(userBank);
            System.out.println("up");
        }else {
            service.bindBank(userBank);
        }
        session.setAttribute("user",user);

        return false;
    }
    @RequestMapping("/unbindcard")
    @ResponseBody
    public Boolean unbindcard(UserBank userBank, HttpSession session){
        Userimf user = (Userimf) session.getAttribute("user");
        userBank.setuId(user.getuId());
        userBank.setBcUserName(user.getuName());
//        System.out.println(user.toString());
        System.out.println(userBank.toString());
        service.unbindBank(userBank);
        session.setAttribute("user",user);

        return false;
    }

    @RequestMapping("/checkold")
    @ResponseBody
    public boolean checkold(String dp,HttpSession session){
        Userimf user = (Userimf) session.getAttribute("user");
        if (user.getLoginPsw().equals(dp)){
            return true;
        }
        return false;
    }
    @RequestMapping("/smrz")
    @ResponseBody
    public Map smrz(String uName, UserBank userBank, Credit credit,String phe, HttpSession session) throws Exception {
        Userimf user = (Userimf) session.getAttribute("user");
        user.setuName(uName);
        user.setPhe(phe);
        userBank.setuId(user.getuId());
        userBank.setBcUserName(uName);
        credit.setuId(user.getuId());
        credit.setName(uName);
//        System.out.println(user.toString());
//        System.out.println(userBank.toString());
//        System.out.println(credit.toString());
        Map<String, Object> carInfo = CardUtil.getCarInfo(credit.getIdentity());
        user.setAge(Integer.parseInt(carInfo.get("age")+""));
        user.setSex((String) carInfo.get("sex"));
        service.upUser(user);
        service.bindBank(userBank);
        service.bindCredit(credit);
        session.setAttribute("user",user);

        Map map = new HashMap();
        map.put("msg","cg");

        Bounty bounty = new Bounty();
        bounty.setuId(user.getuId());
        bounty.setBoMoney(13888);
        bounty.setBoTime(new Date());
        bounty.setBoType(1);
        integralService.bountyAdd(bounty);

        return map;
    }
    @RequestMapping("/zfmm")
    @ResponseBody
    public String zfmm(String dealPwd,HttpSession session){
        Userimf userimf = (Userimf) session.getAttribute("user");
        userimf.setDealPsw(dealPwd);
        System.out.println(userimf);
        session.setAttribute("user",userimf);
        service.upUser(userimf);
        return "";
    }
    @RequestMapping("/tiXian")
    @ResponseBody
    public Map tiXian(String type,String money,String pwd,HttpSession session){
        Map map = new HashMap();
        Userimf userimf = (Userimf)session.getAttribute("user");
        String uPwd = userimf.getDealPsw();
        if(uPwd.equals(pwd)){
            int pd = service.userChongTi(type,money,userimf,true);
            if(pd == 1){
                Userimf user = service.pandEmail(userimf.getEmail(),"e");
                session.setAttribute("user",user);
                map.put("msg","cg");
            }else if(pd == 2){
                map.put("msg","余额不足");
            }else {
                map.put("msg","操作失败 请重试");
            }
        }else {
            map.put("msg","mmcw");
        }
        return map;
    }

    @RequestMapping("/dlmm")
    @ResponseBody
    public Boolean dlmm(String loginPsw,HttpSession session){
        Userimf userimf = (Userimf) session.getAttribute("user");
        userimf.setLoginPsw(loginPsw);
        System.out.println(userimf);
        service.upUser(userimf);
        session.setAttribute("user",userimf);
        return true;
    }

    @RequestMapping("/czTx")
    public void ChongZhi(String money,HttpServletResponse httpServletResponse){
        String commodity = "余额充值";//获取表单提交的商品名称
        String nowtime = String.valueOf(System.currentTimeMillis());//性能优化  获取当前时间

        // 商户订单号，商户网站订单系统中唯一订单号，必填
        String out_trade_no = nowtime;//采用时间戳当做订单号
        // 销售产品码 必填
        String product_code="FAST_INSTANT_TRADE_PAY";
        // 付款金额，必填
        String total_amount=new String(money);
        // 订单名称，必填
        String subject = new String(commodity);
        // 商品描述，可空
        String body = new String("为你的小母牛理财余额充值");
        //String passback_params = "merchantBizType%3d3C%26merchantBizNo%3d2016010101111";
        // 超时时间 可空
        String timeout_express="2m";

        // SDK 公共请求类，包含公共请求参数，以及封装了签名与验签，开发者无需关注签名与验签
        //调用RSA签名方式
        AlipayClient alipayClient =  new DefaultAlipayClient(AlipayConfig.gatewayUrl,AlipayConfig.app_id, AlipayConfig.merchant_private_key, AlipayConfig.FORMAT, AlipayConfig.charset, AlipayConfig.alipay_public_key,AlipayConfig.sign_type);  //获得初始化的AlipayClient
        AlipayTradePagePayRequest alipayRequest =  new  AlipayTradePagePayRequest(); //创建API对应的request
        //AlipayTradeWapPayRequest alipayRequest=new AlipayTradeWapPayRequest();

        // 封装请求支付信息
        AlipayTradeWapPayModel model = new AlipayTradeWapPayModel();
        model.setOutTradeNo(out_trade_no);//单号
        model.setSubject(subject);//订单名称，必填
        model.setTotalAmount(total_amount);//付款金额
        model.setBody(body);//商品描述
        model.setTimeoutExpress(timeout_express);//超过时间
        model.setProductCode(product_code);//销售产品码
        alipayRequest.setBizModel(model);
        // 设置异步通知地址
        alipayRequest.setNotifyUrl(AlipayConfig.notify_url);
        // 设置同步地址
        alipayRequest.setReturnUrl(AlipayConfig.return_url);

        // form表单生产
        String form = "";
        try {
            // 调用SDK生成表单
            form = alipayClient.pageExecute(alipayRequest).getBody();
            httpServletResponse.setContentType("text/html;charset=" + AlipayConfig.charset);
            httpServletResponse.getWriter().write(form);//直接将完整的表单html输出到页面
            httpServletResponse.getWriter().flush();
            httpServletResponse.getWriter().close();
        } catch (AlipayApiException | IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    //设置同步地址
    @GetMapping("/return_url")
    public String return_url(HttpServletRequest request,HttpSession session) throws UnsupportedEncodingException, AlipayApiException {
        System.out.println("进来了同步");
        Map<String,String> params = new HashMap<String,String>();
        Map<String,String[]> requestParams = request.getParameterMap();
        for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            //乱码解决，这段代码在出现乱码时使用
            //valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
            params.put(name, valueStr);
        }
        //商户订单号
        String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");
        //支付宝交易号
        String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");
        //付款金额
        String total_amount = new String(request.getParameter("total_amount").getBytes("ISO-8859-1"),"UTF-8");

        System.out.println("支付宝交易号:"+trade_no+"<br/>商户订单号:"+out_trade_no+"<br/>付款金额:"+total_amount);
        boolean signVerified = AlipaySignature.rsaCheckV1(params, AlipayConfig.alipay_public_key, AlipayConfig.charset, AlipayConfig.sign_type); //调用SDK验证签名
        System.out.println("同步SDK"+signVerified);
        //——请在这里编写您的程序（以下代码仅作参考）——
        if(signVerified) {//验证成功
            System.out.println("验证成功！");
            Userimf userimf = (Userimf)session.getAttribute("user");
            int pd = service.userChongTi("cz",total_amount,userimf,false);
            if(pd == 1){
                Userimf user = service.pandEmail(userimf.getEmail(),"e");
                session.setAttribute("user",user);
            }
            return "redirect:toAO";//跳转到订单信息页面
        }else {
            System.out.println("验证失败");
            return "index";
        }
    }

    @RequestMapping("/unbindCard")
    @ResponseBody
    public Boolean unbindCard(UserBank bank,HttpSession session){
        Userimf refereer = new Userimf();
        return false;
    }
    @RequestMapping("/emailUp")
    @ResponseBody
    public Boolean emailUp(String newEmail,HttpSession session){
        Userimf userimf = (Userimf) session.getAttribute("user");
        userimf.setEmail(newEmail);
        service.upUser(userimf);
        session.setAttribute("user",userimf);
        return true;
    }
    @RequestMapping("/bindWorryCall")
    @ResponseBody
    public Boolean bindWorryCall(WorryCall worryCall, HttpSession session){
        Userimf userimf = (Userimf) session.getAttribute("user");
        worryCall.setuId(userimf.getuId());
        service.bindWorryCall(worryCall);
        return true;
    }
    @RequestMapping("/WorryCallUp")
    @ResponseBody
    public Boolean upWorryCall(WorryCall worryCall,HttpSession session){
        Userimf user = (Userimf) session.getAttribute("user");
        worryCall.setuId(user.getuId());
        service.upWorryCall(worryCall);

        return true;
    }
    @RequestMapping("/bindReferee")
    @ResponseBody
    public Map bindReferee(String referee,HttpSession session){
        Map map = new  HashMap();
        Userimf refereer = new Userimf();
        refereer.setAccount(referee);
        Userimf user = (Userimf) session.getAttribute("user");

        if (user.getAccount().equals(referee)){
            map.put("msg","推荐人不能写自己");
            return map;
        }

        if (service.bindReferee(refereer,user)){
            user.setRefereer(referee);

            Bounty bounty = new Bounty();
            bounty.setuId(user.getuId());
            bounty.setBoMoney(10);
            bounty.setBoTime(new Date());
            bounty.setBoType(3);
            integralService.bountyAdd(bounty);

            session.setAttribute("user",user);
            map.put("msg","success");
            return map;
        }else {
            map.put("msg","该用户不存在，请重试");
            return map;
        }
    }
}
