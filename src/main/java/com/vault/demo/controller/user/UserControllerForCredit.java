package com.vault.demo.controller.user;

import com.vault.demo.bean.Car;
import com.vault.demo.bean.House;
import com.vault.demo.bean.Userimf;
import com.vault.demo.bean.Warrant;
import com.vault.demo.dao.file.FileUpload;
import com.vault.demo.service.loan.LoanService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Date;

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
        model.addAttribute("car",loanService.selectCarByUId((Userimf)session.getAttribute("user")));
        model.addAttribute("warrant",loanService.selectWarrantByUId((Userimf) session.getAttribute("user")));

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

        try {
            fileName = fileUpload.upload(photoFile,dirPathEx,dirPath,request,null);//优先存到项目发布目录了，如果要优先存到项目根目录，第二三参数对调，第二参数是优先存的参数
        }catch (Exception e){
            e.printStackTrace();

            System.out.println("文件存储出错了");

            return "redirect:toCreditRegisterPage";
        }

        System.out.println(fileName + "\n" + fileNameEx);

        car.setPhoto("/photoCar/"+fileName);
        car.setuId(((Userimf)session.getAttribute("user")).getuId());
        car.setMoney((float) (car.getMoney() * 0.0001));
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

        //找到项目发布路径
        File path = new File(ResourceUtils.getURL("classpath:").getPath());
        File upload = new File(path.getAbsolutePath(),"static/photoHouse/");

        //找到项目发布路径根目录
        String dirPathEx = upload.getAbsolutePath();

        System.out.println(dirPath + "\n" + dirPathEx);

        String fileName = null;

        try {
            fileName = fileUpload.upload(photoFile,dirPathEx,dirPath,request,null);//优先存到项目发布目录了，如果要优先存到项目根目录，第二三参数对调，第二参数是优先存的参数
        }catch (Exception e){
            e.printStackTrace();

            System.out.println("文件存储出错了");

            return "redirect:toCreditRegisterPage";
        }

        System.out.println(fileName);

        house.setPhoto("/photoHouse/"+fileName);
        house.setuId(((Userimf)session.getAttribute("user")).getuId());
        house.setMoney((float) (house.getMoney() * 0.0001));
        house.setStatus(4);//审核中

        loanService.insertHouse(house);

//        Resource resource = new ClassPathResource("");
//        System.out.println(resource.getFile().getAbsolutePath());

        return "redirect:toCreditRegisterPage";
    }

    public boolean insertJob(String dept,Double wages){
        return false;
    }

    @RequestMapping("/toUploadWarrant")
    public String toUploadWarrant(Warrant warrant, HttpSession session, HttpServletRequest request, MultipartFile wPositiveIDPhotoFile, MultipartFile wNegativeIDPhotoFile, MultipartFile wContractPhotoFile) throws FileNotFoundException {
        //找到项目根目录
        String dirPath = System.getProperty("user.dir");
        dirPath = dirPath + "\\src\\main\\resources\\static\\photoWarrant";
//        System.out.println(ClassUtils.getDefaultClassLoader().getResource("").getPath());

        //找到项目发布路径
        File path = new File(ResourceUtils.getURL("classpath:").getPath());
        File upload = new File(path.getAbsolutePath(),"static/photoWarrant/");

        //找到项目发布路径根目录
        String dirPathEx = upload.getAbsolutePath();

        System.out.println(dirPath + "\n" + dirPathEx);

        String wPositiveIDPhoto = null;
        String wNegativeIDPhoto = null;
        String wContractPhoto = null;

        try {
            wPositiveIDPhoto = fileUpload.upload(wPositiveIDPhotoFile,dirPathEx,dirPath,request,null);//优先存到项目发布目录了，如果要优先存到项目根目录，第二三参数对调，第二参数是优先存的参数
            wNegativeIDPhoto = fileUpload.upload(wNegativeIDPhotoFile,dirPathEx,dirPath,request,null);//优先存到项目发布目录了，如果要优先存到项目根目录，第二三参数对调，第二参数是优先存的参数
            wContractPhoto = fileUpload.upload(wContractPhotoFile,dirPathEx,dirPath,request,null);//优先存到项目发布目录了，如果要优先存到项目根目录，第二三参数对调，第二参数是优先存的参数
        }catch (Exception e){
            e.printStackTrace();

            System.out.println("文件存储出错了");

            return "redirect:toCreditRegisterPage";
        }

        warrant.setwPositiveIDPhoto("/photoWarrant/" + wPositiveIDPhoto);
        warrant.setwNegativeIDPhoto("/photoWarrant/" + wNegativeIDPhoto);
        warrant.setwContractPhoto("/photoWarrant/" + wContractPhoto);
        warrant.setRegisterTime(new Date());

        System.out.println(warrant);

        loanService.insertWarrant(warrant);

        return "redirect:toCreditRegisterPage";
    }
}
