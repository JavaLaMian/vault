package com.vault.demo.controller.user;

import com.vault.demo.bean.Car;
import com.vault.demo.bean.House;
import com.vault.demo.bean.Userimf;
import com.vault.demo.dao.file.FileUpload;
import com.vault.demo.service.loan.LoanService;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ClassUtils;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileNotFoundException;

@RequestMapping("/user")
@Controller
public class UserControllerForCredit {
    @Resource
    LoanService loanService;

    @Resource
    FileUpload fileUpload;

    @RequestMapping("/toCreditRegisterPage")
    public String toCreditRegisterPage(HttpSession session, Model model){
        if (((Userimf)session.getAttribute("user")) == null){//判断用户是否登录，
            return "redirect:/user/tologin";
        }

        model.addAttribute("user",(Userimf)session.getAttribute("user"));
        model.addAttribute("credit",loanService.LoanNow((Userimf)session.getAttribute("user")));
        model.addAttribute("house",loanService.selectHouseByUId((Userimf)session.getAttribute("user")));
        model.addAttribute("car",loanService.selectCarByUId((Userimf)session.getAttribute("user")));

        return "user/creditRegister";
    }

    @RequestMapping("/toUploadCar")
    public String toUpload(HttpServletRequest request, HttpSession session, MultipartFile photoFile, Car car) throws FileNotFoundException {
        //找到项目根目录
        String dirPath = System.getProperty("user.dir");
        dirPath = dirPath + "\\src\\main\\resources\\static\\photoCar";
//        System.out.println(ClassUtils.getDefaultClassLoader().getResource("").getPath());

        File path = new File(ResourceUtils.getURL("classpath:").getPath());

        File upload = new File(path.getAbsolutePath(),"static/photoCar/");

        //找到项目发布路径根目录
        String dirPathEx = upload.getAbsolutePath();

        System.out.println(dirPath + "\n" + dirPathEx);

        String fileName = null;
        String fileNameEx = null;

        MultipartFile photoFileEx = photoFile;

        try {
            fileName = fileUpload.upload(photoFile,dirPathEx,dirPath,request,null);//存到项目根目录
        }catch (Exception e){
            e.printStackTrace();

            System.out.println("文件存储出错了");

            return "redirect:toCreditRegisterPage";
        }

        System.out.println(fileName + "\n" + fileNameEx);

        car.setPhoto("/photoCar/"+fileName);
        car.setuId(((Userimf)session.getAttribute("user")).getuId());
        car.setStatus(4);//审核中

        loanService.insertCar(car);

//        Resource resource = new ClassPathResource("");
//        System.out.println(resource.getFile().getAbsolutePath());
        return "redirect:toCreditRegisterPage";
    }

    @RequestMapping("/toUploadHouse")
    public String toUpload(HttpServletRequest request, HttpSession session, MultipartFile photoFile, House house) throws FileNotFoundException {
        //找到项目根目录
        String dirPath = System.getProperty("user.dir");
        dirPath = dirPath + "\\src\\main\\resources\\static\\photoHouse";
//        System.out.println(ClassUtils.getDefaultClassLoader().getResource("").getPath());

        File path = new File(ResourceUtils.getURL("classpath:").getPath());

        File upload = new File(path.getAbsolutePath(),"static/photoHouse/");

        //找到项目发布路径根目录
        String dirPathEx = upload.getAbsolutePath();

        System.out.println(dirPath + "\n" + dirPathEx);

        String fileName = null;

        MultipartFile photoFileEx = photoFile;

        try {
            fileName = fileUpload.upload(photoFile,dirPathEx,dirPath,request,null);//存到项目根目录
        }catch (Exception e){
            e.printStackTrace();

            System.out.println("文件存储出错了");

            return "redirect:toCreditRegisterPage";
        }

        System.out.println(fileName);

        house.setPhoto("/photoHouse/"+fileName);
        house.setuId(((Userimf)session.getAttribute("user")).getuId());
        house.setStatus(4);//审核中

        loanService.insertHouse(house);

//        Resource resource = new ClassPathResource("");
//        System.out.println(resource.getFile().getAbsolutePath());
        return "redirect:toCreditRegisterPage";
    }
}
