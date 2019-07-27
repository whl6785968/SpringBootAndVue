package com.sandalen.jwts.controller;

import com.sandalen.jwts.utils.UploadUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.IOException;

@Controller
public class UploadController {
    @ResponseBody
    @RequestMapping("/uploadPic")
    public String uploadPic(MultipartFile file) throws IOException {
        String path = UploadUtils.addMultipartFile(file, "F:\\vue-project\\");

        return path;
    }
}
