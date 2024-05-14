package com.air.health.common.controller;

import com.air.health.common.model.Result;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.UUID;

/**
 * @Title: KaptchaController
 * @Author Aircreach
 * @Version 1.0.0
 * @Package com.air.health.common.controller
 * @Date 2024/4/8 14:30
 * @description:
 */

@RestController
@RequestMapping("common")
public class CommonController {

    @Value("${app.fs.basePath}")
    String basePath;

    @PostMapping("/upload")
    public Result upload(MultipartFile file){
        //获取原始文件名
        String originalFileName = file.getOriginalFilename();

        // UUID 生成文件名 ==> 防止文件名重复,导致文件覆盖
        String UUId = UUID.randomUUID().toString();
        // subString 截去索引前字符串
        // lastIndexOf 返回查找 " . " 最后出现的位置索引
        String substring = originalFileName.substring(originalFileName.lastIndexOf("."));
        String fileName = UUId + substring;
        //创建指定目录
        File dir = new File(basePath);
        if (! dir.exists()){
            //目录不存在 ==> 创建目录
            dir.mkdirs();
        }
        try {
            //将文件转存到指定路径
            file.transferTo(new File(basePath + fileName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return Result.success(fileName);
    }

    /**
     * 文件下载
     * 返回值为 void
     * 若设定返回值,前端无法打包为 Json
     * 报错 NotWritableException
     * @param response
     * @param name
     * @return
     */
    @GetMapping("/download")
    public void  download(HttpServletResponse response , String name){
        try {
            //输入流 ==> 读取文件
            FileInputStream fileInputStream = new FileInputStream(new File(basePath + name));
            //输出流 ==> 写回浏览器(客户端)
            ServletOutputStream outputStream = response.getOutputStream();
            response.setContentType("image/jpeg");

            int len = 0;
            byte[] bytes = new byte[1024];      // TODO : IO 流内容 复习
            while ( (len = fileInputStream.read(bytes)) != -1){
                outputStream.write( bytes , 0 , len);
                outputStream.flush();
            }
            //关闭资源
            outputStream.close();
            fileInputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}