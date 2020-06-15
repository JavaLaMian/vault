package com.vault.demo.dao.file;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * Created by shkstart on 2019/11/15
 * @author LaIWeiChun
 */
public class FileUpload {

    public static String upload(MultipartFile file, String dirName, HttpServletRequest request){
        String picName= UUID.randomUUID().toString();
        String originalFilename = file.getOriginalFilename();
        String extName=originalFilename.substring(originalFilename.lastIndexOf("."));
        //String path = request.getSession().getServletContext().getRealPath("\\");
        File dirFile=new File(dirName);
        if (!dirFile.exists()){
            dirFile.mkdir();
        }       
        String newFileName=picName+extName;
        File targetFile=new File(dirName,newFileName);
        System.out.println("路径"+dirName);
        try {           
            file.transferTo(targetFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return newFileName;
    }
}
