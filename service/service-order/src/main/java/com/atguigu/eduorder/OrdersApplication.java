package com.atguigu.eduorder;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author SongYu
 * @version 1.0.0
 * @ClassName OrdersApplication.java
 * @Description TODO
 * @createTime 2022年05月04日 17:56:00
 */
@SpringBootApplication
@MapperScan("com.atguigu.eduorder.mapper")
@EnableFeignClients
@ComponentScan(basePackages = {"com.atguigu"})
public class OrdersApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrdersApplication.class,args);
    }
}
