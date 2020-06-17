package com.vault.demo.controller.loan;

import com.vault.demo.bean.Credit;
import com.vault.demo.bean.Loan;
import com.vault.demo.bean.UserBank;
import com.vault.demo.bean.Userimf;
import com.vault.demo.dao.BankDao;
import com.vault.demo.dao.UserimfDao;
import com.vault.demo.dao.WorryCallDao;
import com.vault.demo.dao.file.FileUpload;
import com.vault.demo.service.loan.LoanService;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@RequestMapping("/loan")
@Controller
public class loanController {

    @Resource
    UserimfDao userimfDao;

    @Resource
    LoanService loanService;

    @Resource
    BankDao bankDao;

    @Resource
    WorryCallDao worryCallDao;

    @RequestMapping("/main")
    public String loanmain(){
        return "loan/loanMain";
    }
    //去登录
    @RequestMapping("/tologin")
    public String toLogin(){
        return "loan/loanLogin";
    }
    @RequestMapping("/tologin2")
    public String toLogin2(){
        return "loan/loanLogin2";
    }

    //登录
    @RequestMapping("/login")
    public String login(String account , String password , HttpSession session){
        Userimf userimf = new Userimf();

        if (account.length() < 9){//字符总长小于9，是为邮箱登录
            userimf.setEmail(account);
        }else if (account.substring(0,9).equals("xiaomuniu")){//字符前9为‘xiaomuniu’，是为账号登录
            userimf.setAccount(account);
        }else {//大于9且字符前9不为‘xiaomuniu’，是为邮箱登录
            userimf.setEmail(account);
        }

        userimf.setLoginPsw(password);

        Userimf userimfEX = userimfDao.selectOneByLogin(userimf);

        session.setAttribute("user",userimfEX);
        session.setAttribute("userLoan",loanService.LoanNow(userimfEX));//获取用户的贷款信息

        return "redirect:/loan/main2";
    }

    @RequestMapping("/checkUserAccountAndPwd")
    @ResponseBody
    public String checkUserAccountAndPwd(String account,String password){
        System.out.println("账号"+account+"密码:"+password);

        Userimf userimf = new Userimf();

        if (account.length() < 9){//字符总长小于9，是为邮箱登录
            userimf.setEmail(account);
        }else if (account.substring(0,9).equals("xiaomuniu")){//字符前9为‘xiaomuniu’，是为账号登录
            userimf.setAccount(account);
        }else {//大于9且字符前9不为‘xiaomuniu’，是为邮箱登录
            userimf.setEmail(account);
        }

        userimf.setLoginPsw(password);

        Userimf userimfEX = userimfDao.selectOneByLogin(userimf);

        System.out.println(userimfEX);
        if (userimfEX == null){
            System.out.println("登录失败");

            return "NO";
        }else {
            return "YES";
        }
    }

    @RequestMapping("/loginOut")
    public String loginOut(HttpSession session){
        session.removeAttribute("user");

        return "redirect:/loan/main";
    }

    //借贷中心
    @RequestMapping("/main2")
    public String main2(HttpSession session) {
        if (checkSessionIsEmpty(session)){//检测用户是否登录
            return "redirect:/loan/main";
        }

        return "loan/loanJie";
    }

    //去借款申请页面
    @RequestMapping("/toloanJie")
    public String toloanJie(@Param( "step") Integer step, Model model, HttpSession session, String loanTypeStr , Loan loan) {
        if (checkSessionIsEmpty(session)){//检测用户是否登录
            return "redirect:/loan/main";
        }

        if ("".equals(loanTypeStr)) {

        }else if ("xinyong".equals(loanTypeStr)){ //如果是信用贷款类型
            Credit credit = loanService.selectCredit(((Userimf)session.getAttribute("user")));
            UserBank userBank = bankDao.getBC(((Userimf)session.getAttribute("user")).getuId());
            Userimf userimf = (Userimf)session.getAttribute("user");
            List worryCallList = worryCallDao.selectWorryByUId(userimf);

            if (credit == null
                    || credit.getName() == null || "".equals(credit.getName())
                    || credit.getDepart() == null || "".equals(credit.getDepart())
                    || credit.getWages() == null || "".equals(credit.getWages())
                    || credit.getIdentity() == null || "".equals(credit.getIdentity())
                    || userBank == null
                    || userimf.getDealPsw() == null || "".equals(userimf.getDealPsw())
                    || userimf.getPhe() == null || "".equals(userimf.getPhe())
                    ||  worryCallList.size() <= 0){
                model.addAttribute("loanNotPushType","请完善您的个人详细信息（真实姓名、身份证、银行卡、职业、收入、紧急联系人、联系电话、支付密码）");
//                model.addAttribute("Name",true);
//                model.addAttribute("Name",true);
//                model.addAttribute("Name",true);
//                model.addAttribute("Name",true);
//                model.addAttribute("Name",true);
//                model.addAttribute("Name",true);

                return "loan/creditNotPush";
            }
        }

        System.out.println("step："+step);
        System.out.println("loan："+loan);

        if(step==null){
            step = 1 ;

        }else if (step == 2){
            float loanWantMoney = loan.getLoanWantMoney();

            loanWantMoney = (float) (loanWantMoney * 0.0001);

            System.out.println(loanWantMoney);

            loan.setLoanWantMoney(loanWantMoney);
            loan.setApplicationTime(new Date());
            loan.setLoanStatue(LoanService.CHECK);

            loanService.insertLoan(loan);

            Credit credit = loanService.selectCredit(((Userimf)session.getAttribute("user")));

            model.addAttribute("loan",loan);
            model.addAttribute("credit",credit);

        }

        Userimf userimf = (Userimf)session.getAttribute("user");

        Loan loanEX = loanService.LoanNow(userimf);

        if (loanEX != null){        //如果目前用户的贷款有正在审核的情况
            if (loanEX.getLoanStatue() == 0){
                step = 2;
                System.out.println("loanEX：" + loanEX);
                model.addAttribute("loan",loanEX);
                Credit credit = loanService.selectCredit(((Userimf)session.getAttribute("user")));
                model.addAttribute("credit",credit);
            }
        }

        model.addAttribute("step",step);
        return "loan/loanJieApply";
    }

    @RequestMapping("/toChooseLoanType")
    public String toChooseLoanType(HttpSession session){
        if (checkSessionIsEmpty(session)){//检测用户是否登录
            return "redirect:/loan/main";
        }

        return "loan/chooseLoanType";
    }

    @RequestMapping("/toCreditRegister")
    public String toCreditRegister(HttpSession session){
        if (checkSessionIsEmpty(session)){//检测用户是否登录
            return "redirect:/loan/main";
        }

        return "user/creditRegister";
    }

    @RequestMapping("/registerCredit")
    public String registerCredit(Credit credit, HttpSession session, MultipartFile positiveIDPhotoEX, MultipartFile negativeIDPhotoEX, HttpServletRequest request){
        if (checkSessionIsEmpty(session)){//检测用户是否登录
            return "redirect:/loan/main";
        }

        String realPath =  request.getSession().getServletContext().getRealPath("");
        String dirPath = "D:\\vault\\file\\identity\\";
        //上传文件
        String positiveIDPhotoEXFileName = FileUpload.upload(positiveIDPhotoEX,dirPath,request);
        String negativeIDPhotoEXFileName = FileUpload.upload(negativeIDPhotoEX,dirPath,request);
        credit.setPositiveIDPhoto(positiveIDPhotoEX.getOriginalFilename());
        credit.setNegativeIDPhoto(negativeIDPhotoEX.getOriginalFilename());


        return "";
    }

    @RequestMapping("/loanJie")
    public String loanJie(HttpSession session){
        if (checkSessionIsEmpty(session)){//检测用户是否登录
            return "redirect:/loan/main";
        }

        return "";
    }

    @RequestMapping("/toloanHuan")
    public String toloanHuan(HttpSession session) {
        if (checkSessionIsEmpty(session)){//检测用户是否登录
            return "redirect:/loan/main";
        }

        return "loan/loanHuan";
    }
    @RequestMapping("/toloanRecord")
    public String toloanRecord(HttpSession session) {
        if (checkSessionIsEmpty(session)){//检测用户是否登录
            return "redirect:/loan/main";
        }

        return "loan/loanRecord";
    }
    @RequestMapping("/toloanPersonage")
    public String toloanPersonage(HttpSession session) {
        if (checkSessionIsEmpty(session)){//检测用户是否登录
            return "redirect:/loan/main";
        }

        return "loan/loanPersonage";
    }

    public Boolean checkSessionIsEmpty(HttpSession session){
        Userimf userimf = (Userimf)session.getAttribute("user");

        if (userimf == null){
            return true;
        }else {
            return false;
        }
    }

    @RequestMapping("/test")
    @ResponseBody
    public String test(){
        loanService.TestInsertLoan();

        List loanList = loanService.TestAllLoan();

        return loanList.toString();
    }
}
