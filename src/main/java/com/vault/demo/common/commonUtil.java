package com.vault.demo.common;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Component
public class commonUtil {

    public String fileUpload(MultipartFile file,String savePath ){
        // 所以为每个文件生成一个新的文件file名(asda-df43t-f34t3-23r2-34t2)
        String picName = UUID.randomUUID().toString();
        // 截取文件的扩展名(如.jpg)
        String oriName = file.getOriginalFilename();
        System.out.println("--上传文件名-->>"+oriName);
        // .png  .jpg
        String extName = oriName.substring(oriName.lastIndexOf("."));


        //创建文件夹
        File dirFile = new File(savePath);
        if(!dirFile.exists()){
            dirFile.mkdirs();
        }
        //拼接新文件名
        String newFileName = picName + extName;
        File targetFile = new File(savePath , newFileName);
        // 保存文件        pp.setPsrc("/img/"+newFileName);
        try {
            file.transferTo(targetFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return newFileName;
    }
}
