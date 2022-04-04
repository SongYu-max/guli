package com.atguigu.cos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author SongYu
 * @version 1.0.0
 * @ClassName CosApplication.java
 * @Description TODO
 * @createTime 2022年04月04日 15:04:00
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@ComponentScan(basePackages = {"com.atguigu"})
public class CosApplication {
    public static void main(String[] args) {
        SpringApplication.run(CosApplication.class,args);
    }
}
