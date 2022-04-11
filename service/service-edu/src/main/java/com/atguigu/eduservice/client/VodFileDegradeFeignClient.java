package com.atguigu.eduservice.client;

import com.atguigu.commonutils.R;

/**
 * @author SongYu
 * @version 1.0.0
 * @ClassName VodFileDegradeFeignClient.java
 * @Description TODO
 * @createTime 2022年04月11日 23:22:00
 */
public class VodFileDegradeFeignClient implements VodClient{
    //feign接口出错后，会执行这里
    @Override
    public R removeAlyVideo(String id) {
        return R.error().message("删除视频出错啦。。。。！");
    }
}
