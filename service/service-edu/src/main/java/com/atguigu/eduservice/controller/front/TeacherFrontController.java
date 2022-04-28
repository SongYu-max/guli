package com.atguigu.eduservice.controller.front;

import com.atguigu.commonutils.R;
import com.atguigu.eduservice.entity.EduCourse;
import com.atguigu.eduservice.entity.EduTeacher;
import com.atguigu.eduservice.service.EduCourseService;
import com.atguigu.eduservice.service.EduTeacherService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author SongYu
 * @version 1.0.0
 * @ClassName TeacherFrontController.java
 * @Description TODO
 * @createTime 2022年04月27日 22:09:00
 */
@RestController
@CrossOrigin
@RequestMapping("/eduservice/teacherfront")
public class TeacherFrontController {
    @Autowired
    private EduTeacherService eduTeacherService;
    @Autowired
    private EduCourseService eduCourseService;

    //分页查询讲师
    @PostMapping("getTeacherFrontList/{page}/{limit}")
    public R getTeacherFrontList(@PathVariable long page, @PathVariable long limit) {
        Page<EduTeacher> pageTeacher = new Page<EduTeacher>(page,limit);
        Map<String, Object> map = eduTeacherService.getTeacherFrontList(pageTeacher);
        return R.ok().data(map);
    }
    //讲师详情的功能
    @GetMapping("getTeacherFrontInfo/{teacherId}")
    public R getTeacherFrontInfo(@PathVariable long teacherId) {
        //教师详情信息
        EduTeacher teacherInfo = eduTeacherService.getById(teacherId);
        //教师课程信息
        List<EduCourse> courseList = eduCourseService.getCourseInfoByTeacherId(teacherId);
        return R.ok().data("teacher",teacherInfo).data("courseList",courseList);

    }
}
