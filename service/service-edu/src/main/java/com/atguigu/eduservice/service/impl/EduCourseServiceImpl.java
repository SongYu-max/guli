package com.atguigu.eduservice.service.impl;

import com.atguigu.eduservice.entity.EduCourse;
import com.atguigu.eduservice.entity.EduCourseDescription;
import com.atguigu.eduservice.entity.vo.CourseInfoVo;
import com.atguigu.eduservice.mapper.EduCourseMapper;
import com.atguigu.eduservice.service.EduCourseDescriptionService;
import com.atguigu.eduservice.service.EduCourseService;
import com.atguigu.servicebase.exceptionhander.GuliException;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2022-04-03
 */
@Service
public class EduCourseServiceImpl extends ServiceImpl<EduCourseMapper, EduCourse> implements EduCourseService {

    @Autowired
    EduCourseDescriptionService eduCourseDescriptionService;

    @Override
    public String addCourseInfo(CourseInfoVo courseInfoVo) {
        EduCourse eduservice = new EduCourse();
        BeanUtils.copyProperties(courseInfoVo, eduservice);
        int rows = baseMapper.insert(eduservice);
        if (rows <= 0) {
            throw new GuliException(20001, "添加课程失败。");
        }
        String description = courseInfoVo.getDescription();
        EduCourseDescription eduCourseDescription = new EduCourseDescription();
        eduCourseDescription.setDescription(description);
        String id = eduservice.getId();
        eduCourseDescription.setId(id);
        eduCourseDescriptionService.save(eduCourseDescription);
        return id;
    }
}
