package com.atguigu.staservice.client;

import com.atguigu.commonutils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Component
@FeignClient("service-ucenter")
@RequestMapping("/educenter/member")
public interface UcenterClient {
    //查询某一天注册人数
    @GetMapping("countRegister/{day}")
    public R countRegister(@PathVariable("day") String day) ;
}
