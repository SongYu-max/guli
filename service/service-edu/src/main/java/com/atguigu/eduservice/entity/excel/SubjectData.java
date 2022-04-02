package com.atguigu.eduservice.entity.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @author SongYu
 * @version 1.0.0
 * @ClassName SubjectData.java
 * @Description TODO
 * @createTime 2022年04月03日 00:09:00
 */
@Data
public class SubjectData {
    @ExcelProperty(index = 0)
    private String oneSubjectName;
    @ExcelProperty(index = 1)
    private String twoSubjectName;

}
