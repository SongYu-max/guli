package com.atguigu.educenter.controller;


import com.atguigu.commonutils.JwtUtils;
import com.atguigu.commonutils.R;
import com.atguigu.educenter.entity.UcenterMember;
import com.atguigu.educenter.entity.vo.RegisterVo;
import com.atguigu.educenter.service.UcenterMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 会员表 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2022-04-22
 */
@RestController
@RequestMapping("/educenter/member")
@CrossOrigin
public class UcenterMemberController {
    @Autowired
    UcenterMemberService ucenterMemberService;

    //登陆
    @PostMapping("login")
    public R loginUser(@RequestBody UcenterMember member){
        //返回一个token值
        //返回token值，使用jwt生成
        String token = ucenterMemberService.login(member);
        return R.ok().data("token",token);
    }


    //注册
    @PostMapping("register")
    public R logonUser(@RequestBody RegisterVo register){
        //返回一个token值
        //返回token值，使用jwt生成
        ucenterMemberService.register(register);
        return R.ok();
    }

    //跟据token获取用户信息
    @GetMapping("getMemberInfo")
    public R getMemberInfo(HttpServletRequest request){
        String memberId = JwtUtils.getMemberIdByJwtToken(request);
        UcenterMember member = ucenterMemberService.getById(memberId);
        return R.ok().data("userInfo",member);
    }
}

