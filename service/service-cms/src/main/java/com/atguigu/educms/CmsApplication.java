package com.atguigu.educms;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author SongYu
 * @version 1.0.0
 * @ClassName CmsApplication.java
 * @Description TODO
 * @createTime 2022年04月14日 18:52:00
 */
@SpringBootApplication
@MapperScan("com.atguigu.educms.mapper")
@ComponentScan({"com.atguigu"})
public class CmsApplication {
    public static void main(String[] args) {
        SpringApplication.run(CmsApplication.class,args);
    }
}
