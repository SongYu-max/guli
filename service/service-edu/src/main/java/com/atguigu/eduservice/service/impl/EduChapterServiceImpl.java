package com.atguigu.eduservice.service.impl;

import com.atguigu.eduservice.entity.EduChapter;
import com.atguigu.eduservice.entity.EduVideo;
import com.atguigu.eduservice.entity.chapter.ChapterVo;
import com.atguigu.eduservice.entity.chapter.VideoVo;
import com.atguigu.eduservice.mapper.EduChapterMapper;
import com.atguigu.eduservice.service.EduChapterService;
import com.atguigu.eduservice.service.EduVideoService;
import com.atguigu.servicebase.exceptionhander.GuliException;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2022-04-03
 */
@Service
public class EduChapterServiceImpl extends ServiceImpl<EduChapterMapper, EduChapter> implements EduChapterService {

    @Autowired
    private EduVideoService eduVideoService;

    @Override
    public List<ChapterVo> getChapterVideoByCourseId(String courseId) {
        List<ChapterVo> finalList = new ArrayList<>();
        QueryWrapper wrapperChapter = new QueryWrapper();
        wrapperChapter.eq("course_id", courseId);
        List<EduChapter> chapterList = baseMapper.selectList(wrapperChapter);
        QueryWrapper wrapperVideo = new QueryWrapper();
        wrapperVideo.eq("course_id", courseId);
        List<EduVideo> videoList = eduVideoService.list(wrapperVideo);
        for (EduChapter eduChapter : chapterList) {
            ChapterVo chapterVo = new ChapterVo();
            BeanUtils.copyProperties(eduChapter, chapterVo);

            List<VideoVo> videoVoList = new ArrayList<>();

            for (EduVideo eduVideo : videoList) {
                if (eduVideo.getChapterId().equals(eduChapter.getId())) {
                    VideoVo videoVo = new VideoVo();
                    BeanUtils.copyProperties(eduVideo, videoVo);
                    videoVoList.add(videoVo);
                }
            }
            chapterVo.setChildren(videoVoList);
            finalList.add(chapterVo);
        }
        return finalList;
    }

    @Override
    public boolean deleteChapter(String chapterId) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("chapter_id", chapterId);
        int count = eduVideoService.count(wrapper);
        if (count > 0) {
            throw new GuliException(20001, "不能删除");
        } else {
            int result = baseMapper.deleteById(chapterId);
            return result > 0;
        }
    }
}
