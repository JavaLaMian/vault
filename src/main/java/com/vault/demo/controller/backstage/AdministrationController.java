package com.vault.demo.controller.backstage;

import com.vault.demo.bean.Admin;
import com.vault.demo.dao.backstage.AdminDao;
import com.vault.demo.service.backstage.admin.AdminService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.UUID;

@Controller
@RequestMapping("/admin")
public class AdministrationController {
    @Resource
    private AdminService adminService;

    @RequestMapping("/toLogin")
    public String toLogin(){
        return "backstage/admin_login";
    }

    @ResponseBody
    @RequestMapping("/login")
    public String login(String account,String pwd,HttpSession session){
        System.out.println(account+" "+pwd);
        Admin admin = adminService.checkLogin(account,pwd);
        if(admin!=null){
            session.setAttribute("admin",admin);
            return "success";
        }else{
            return "shibai";
        }
    }
    @ResponseBody
    @RequestMapping("/repwd")
    public String rePwd(int aid,String newpwd){
        adminService.setPwd(aid,newpwd);
        return "success";
    }
    @ResponseBody
    @RequestMapping("/checkold")
    public String checkOldPwd(String oldpwd,int aid){
        Admin admin = adminService.getAdminById(aid);
        if(oldpwd==admin.getPwd()){
            return "true";
        }else{
            return "false";
        }
    }

    @RequestMapping("/homeImg")
    public String zhuCe(MultipartFile[] files,String xuhao,HttpServletRequest request) throws IOException {
        if (xuhao!=null&&xuhao.length()>0){
            for (int i=0;i<xuhao.length();i++){
                MultipartFile file = files[i];
                char fir= xuhao.charAt(i);//main+fir
                //String picName = UUID.randomUUID().toString();
                // 截取文件的扩展名(如.jpg)
                String dirName = "D:\\kaifazhe\\JAVA\\vault\\src\\main\\resources\\static\\images\\main\\";
                File dirFile = new File(dirName);
                if(!dirFile.exists()){
                    dirFile.mkdirs();
                }
                //新文件名
                String newFileName = "main"+fir+".png";
                System.out.println(newFileName+"|new");
                File targetFile = new File(dirName , newFileName);
                // 保存文件
                file.transferTo(targetFile);
            }
        }
        return "redirect:/XMN/homeImg";
    }
}
