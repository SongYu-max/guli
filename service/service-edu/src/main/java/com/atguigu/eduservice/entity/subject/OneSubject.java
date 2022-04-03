package com.atguigu.eduservice.entity.subject;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author SongYu
 * @version 1.0.0
 * @ClassName OneSubject.java
 * @Description TODO
 * @createTime 2022年04月03日 15:50:00
 */
@Data
public class OneSubject {
    private String id;
    private String title;
    private List<TwoSubject> children = new ArrayList<TwoSubject>();
}
