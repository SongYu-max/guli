package com.atguigu.eduservice.entity.vo;

import lombok.Data;

/**
 * @author SongYu
 * @version 1.0.0
 * @ClassName CoursePublishVo.java
 * @Description TODO
 * @createTime 2022年04月07日 23:48:00
 */
@Data
public class CoursePublishVo {
    private String id;
    private String title;
    private String cover;
    private Integer lessonNum;
    private String subjectLevelOne;
    private String subjectLevelTwo;
    private String teacherName;
    private String price;//只用于显示
}
