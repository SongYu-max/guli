package com.atguigu.eduservice.controller;


import com.atguigu.commonutils.R;
import com.atguigu.eduservice.entity.EduChapter;
import com.atguigu.eduservice.entity.EduVideo;
import com.atguigu.eduservice.service.EduVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2022-04-03
 */
@RestController
@RequestMapping("/eduservice/video")
@CrossOrigin
public class EduVideoController {
    @Autowired
    private EduVideoService eduVideoService;

    //增加
    @PostMapping("addVideo")
    public R addVideo(@RequestBody EduVideo eduVideo){
        boolean flag = eduVideoService.save(eduVideo);
        if (flag){
            return R.ok();
        }else{
            return R.error();
        }
    }
    //修改
    @PostMapping("updateVideo")
    public R updateVideo(@RequestBody EduVideo eduVideo){
        boolean flag = eduVideoService.updateById(eduVideo);
        if (flag){
            return R.ok();
        }else{
            return R.error();
        }
    }
    //删除
    //TODO 后面这个方法需要完善，删小节的时候，同时把里面的视频删除
    @DeleteMapping("{videoId}")
    public R deleteVideo(@PathVariable String videoId){
        eduVideoService.removeById(videoId);
        return R.ok();
    }
    //根据小节id查询
    @GetMapping("getVideoInfo/{videoId}")
    public R getVideoInfo(@PathVariable String videoId){
        EduVideo eduVideo = eduVideoService.getById(videoId);
        return R.ok().data("video",eduVideo);
    }
}

