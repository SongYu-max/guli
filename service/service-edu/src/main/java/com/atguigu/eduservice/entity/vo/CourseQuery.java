package com.atguigu.eduservice.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author SongYu
 * @version 1.0.0
 * @ClassName CourseQuery.java
 * @Description TODO
 * @createTime 2022年04月09日 14:02:00
 */
@Data
public class CourseQuery {
    @ApiModelProperty(value = "课程名称,模糊查询")
    private String title;

    @ApiModelProperty(value = "课程状态 Draft未发布 Normal已发布")
    private String status;

}
