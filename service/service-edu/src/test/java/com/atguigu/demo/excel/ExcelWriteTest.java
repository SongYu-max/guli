package com.atguigu.demo.excel;

import com.alibaba.excel.EasyExcel;

import java.util.ArrayList;
import java.util.List;

/**
 * @author SongYu
 * @version 1.0.0
 * @ClassName ExcelWriteTest.java
 * @Description TODO
 * @createTime 2022年04月02日 16:51:00
 */
public class ExcelWriteTest {
    public static void main(String[] args) {
//        //写操作
//        String fileName = "D:\\test.xlsx";
//        EasyExcel.write(fileName,DemoData.class).sheet("学生列表").doWrite(getData());

        //读操作
        String fileName = "D:\\test.xlsx";
        EasyExcel.read(fileName,DemoData.class,new ExcelListener()).sheet().doRead();

    }
    public static List<DemoData> getData(){
        List<DemoData> list = new ArrayList<DemoData>();
        for (int i = 0; i < 10; i++) {
            DemoData demoData = new DemoData();
            demoData.setSno(i);
            demoData.setName("张三"+1);
            list.add(demoData);
        }
        return list;
    }
}
