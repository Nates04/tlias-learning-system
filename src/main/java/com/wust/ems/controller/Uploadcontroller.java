package com.wust.ems.controller;

import com.wust.ems.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Slf4j
@RestController
public class Uploadcontroller {

    @PostMapping("/upload")
    public Result upload(String username,Integer age,MultipartFile image) throws IOException {
        log.info("上传文件为：{}，{}，{}",username,age,image);
        //将文件储存在本地磁盘中,获取文件名,用绝对路径可以自动创建文件，相对目录只是创建目录
        File file=new File("D:\\binchen\\java\\javaproject\\school\\emsall\\ems\\src\\main\\java\\com\\wust\\ems\\File\\"+image.getOriginalFilename());
        if(!file.exists()){
            file.mkdirs();
        }
        image.transferTo(file);
        return Result.success();
    }

}
