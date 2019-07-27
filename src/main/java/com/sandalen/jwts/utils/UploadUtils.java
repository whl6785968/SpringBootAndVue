package com.sandalen.jwts.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class UploadUtils {
    public static String addMultipartFile(MultipartFile file,String pathAppend) throws IOException {
        String uuid = UUID.randomUUID().toString();
        uuid = uuid.replace("-", "");

        if(file == null){
            return null;
        }else {
            String path = null;
            String type = null;

            String originalFilename = file.getOriginalFilename();
            type = originalFilename.substring(originalFilename.lastIndexOf("."));

            String realPath = "static/";
            realPath = pathAppend + realPath;

            File dir = new File(realPath);
            if(!dir.exists()){
                dir.mkdirs();
            }else {
                System.out.println("目录已经存在");
            }

            String newFileName = uuid;

            path = realPath + newFileName + type;
            System.out.println("最终文件上传路径为"+path);
            file.transferTo(new File(path));
            System.out.println("文件已经成功上传到指定目录");

            return "/static/"+uuid+type;


        }
    }
}
