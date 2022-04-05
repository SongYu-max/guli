package com.atguigu.cos.controller;

import com.atguigu.commonutils.R;
import com.atguigu.cos.service.CosService;
import com.qcloud.cos.model.COSObjectSummary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

/**
 * @author SongYu
 * @version 1.0.0
 * @ClassName CosController.java
 * @Description TODO
 * @createTime 2022年04月04日 15:19:00
 */
@RestController
@RequestMapping("/educos/filecos")
@CrossOrigin
public class CosController {

    @Autowired
    CosService cosService;

    //上传文件的方法
    @PostMapping("/upload")
    public R uploadOssFile(MultipartFile file) {
        //获取上传文件  MultipartFile
        //返回上传到oss的路径
        String url = cosService.uploadFile(file);
        return R.ok().data("url",url);
    }

    //获取文件列表
    @GetMapping("getlist")
    public R getlist(String filePath, String filename) {
        //获取上传文件  MultipartFile
        //返回上传到oss的路径
        List<COSObjectSummary> list = cosService.getlist(filePath,filename);
        return R.ok().data("list",list);
    }
    //文件下载                      //todo 下载目前还没测通
    @PostMapping("/loaddown")
    public R loaddownOssFile(String filename) {
        //获取上传文件  MultipartFile
        //返回上传到oss的路径
        String url = cosService.loaddownOssFile(filename);
        return R.ok().data("url",url);
    }


    //删除文件
    @DeleteMapping("/deleteFile")
    public R deleteFile(String filename) {
        //获取上传文件  MultipartFile
        //返回上传到oss的路径
        String url = cosService.deleteFile(filename);
        return R.ok().data("url",url);
    }

}
