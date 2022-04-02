package com.atguigu.demo.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @author SongYu
 * @version 1.0.0
 * @ClassName DemoData.java
 * @Description TODO
 * @createTime 2022年04月02日 16:28:00
 */
@Data
public class DemoData {

    @ExcelProperty(value = "学生序号", index = 0)
    private Integer sno;
    @ExcelProperty(value = "学生姓名", index =1)
    private String name;
}
