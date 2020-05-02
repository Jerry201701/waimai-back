package com.diane.manage.controller;

import com.diane.common.http.HttpResult;
import com.diane.manage.util.CosUploadUtil;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;

@RestController

public class UploadFileController {

    @RequestMapping(value = "/upload/single/file", method = RequestMethod.POST)
    public String uploadSingleFile(@RequestParam("multipartfiles") MultipartFile multipartFile) {

        String fileName = multipartFile.getOriginalFilename();
        int i=fileName.lastIndexOf(".");
        String suffix=fileName.substring(i);
        String prefix=fileName.substring(0,i);
        System.out.println(suffix);
        String path="test/".concat(fileName);
        // 获取文件后缀
        try {
            final File tempFile = File.createTempFile(prefix,suffix);
            multipartFile.transferTo(tempFile);
            CosUploadUtil.uploadPicture(tempFile,path);
            tempFile.delete();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }


    @GetMapping(value = "/upload/create/secret")
    public HttpResult createSecret(){
//        Map<String,Object> map=new HashMap<>();
//        JSONObject json= CosUploadUtil.createTempSecret();
//        JSONObject jsonObject=json.getJSONObject("credentials");
//        map.put("tmpSecretId",jsonObject.getString("tmpSecretId"));
//        map.put("tmpSecretKey",jsonObject.getString("tmpSecretKey"));
//        map.put("sessionToken",jsonObject.getString("sessionToken"));
//        map.put("expiredTime",json.getLong("expiredTime"));

        return HttpResult.ok(CosUploadUtil.JsonToMap());

    }

    @GetMapping(value = "/wei/create/secret")
    public HttpResult createWeiSecret(){

        return HttpResult.ok(CosUploadUtil.JsonToMap());
    }


    @PostMapping(value = "/manage/company")
    public void cosUploadPicture(@RequestParam(value = "multipartfiles") MultipartFile multipartFile){
        System.out.println(multipartFile.getOriginalFilename());

    }


}
