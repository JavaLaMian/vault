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
    public ModelAndView BidList(ModelAndView mv,HttpSession session, Model model){
        Admin admin = (Admin)session.getAttribute("admin");
        if(admin == null){
            mv.setViewName("backstage/admin_login");
        }else{
            List<Bid> list = is.Bidlist();
            model.addAttribute("list",list);
            mv.setViewName("backstage/BidList");
        }
        return mv;
    }
    //去往投标新增页面
    @RequestMapping("/Badd_Bid")
    public ModelAndView Badd_Bid(ModelAndView mv,HttpSession session){
        Admin admin = (Admin)session.getAttribute("admin");
        if(admin == null){
            mv.setViewName("backstage/admin_login");
        }else{
            mv.setViewName("backstage/Badd_Bid");
        }
        return mv;
    }
}
