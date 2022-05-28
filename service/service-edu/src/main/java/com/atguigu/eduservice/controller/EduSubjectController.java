package com.atguigu.eduservice.controller;


import com.atguigu.commonutils.R;
import com.atguigu.eduservice.entity.subject.OneSubject;
import com.atguigu.eduservice.service.EduSubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2022-04-02
 */
@RestController
@RequestMapping("/eduservice/subject")
//@CrossOrigin
public class EduSubjectController {

    @Autowired
    EduSubjectService eduSubjectService;

    //添加课程分类
    //获取上传过来的路径，把文件内容读取出来
    @PostMapping("addSubject")
    public R addSubject(MultipartFile file) {
        //上传过来excel文件
        eduSubjectService.saveSubject(file, eduSubjectService);
        return R.ok();
    }

    @GetMapping("getAllSubject")
    public R getAllSubject(){
        List<OneSubject> list = eduSubjectService.getAllOneTwoSubject();
        return R.ok().data("list",list);
    }
}

