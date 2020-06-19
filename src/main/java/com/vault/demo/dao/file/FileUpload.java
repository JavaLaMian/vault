package com.vault.demo.dao.file;

import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.UUID;

/**
 * Created by shkstart on 2019/11/15
 * @author LaIWeiChun
 */

@Repository
public class FileUpload {

    public String upload(MultipartFile file, String dirName,String dirNameEX, HttpServletRequest request,String picNameEx) throws IOException {
        String picName= UUID.randomUUID().toString();
//        if (picNameEx != null || "".equals(picNameEx)){
//            picNameEx = picNameEx.substring(0,picNameEx.length() - 4);
//            picName = picNameEx;
//        }
        String originalFilename = file.getOriginalFilename();
        String extName=originalFilename.substring(originalFilename.lastIndexOf("."));
        //String path = request.getSession().getServletContext().getRealPath("\\");
        File dirFile=new File(dirName);
        if (!dirFile.exists()){
            dirFile.mkdir();
        }

        File dirFileEX=new File(dirNameEX);
        if (!dirFileEX.exists()){
            dirFileEX.mkdir();
        }

        String newFileName=picName+extName;
        File targetFile=new File(dirName,newFileName);
        System.out.println("路径"+dirName);

        File targetFileEX=new File(dirNameEX,newFileName);
        System.out.println("路径"+dirNameEX);


        try {
            file.transferTo(targetFile);

        } catch (IOException e) {
            e.printStackTrace();
        }


        File oldFile = new File(dirName + "\\" + newFileName);
        File fileEX = new File(dirNameEX + "\\" + newFileName);
        FileInputStream in = new FileInputStream(oldFile);
        FileOutputStream out = new FileOutputStream(fileEX);;

        byte[] buffer=new byte[2097152];
        int readByte = 0;
        while((readByte = in.read(buffer)) != -1){
            out.write(buffer, 0, readByte);
        }

        in.close();
        out.close();

        return newFileName;
    }
}
