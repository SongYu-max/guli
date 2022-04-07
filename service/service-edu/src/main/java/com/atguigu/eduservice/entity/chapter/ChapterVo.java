package com.atguigu.eduservice.entity.chapter;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author SongYu
 * @version 1.0.0
 * @ClassName ChapterVo.java
 * @Description
 * @createTime 2022年04月05日 14:30:00
 */
@Data
public class ChapterVo {

    private String id;

    private String title;

    //表示小节
    private List<VideoVo> children = new ArrayList<>();
}
