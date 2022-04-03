package com.atguigu.eduservice.service.impl;

import com.alibaba.excel.EasyExcel;
import com.atguigu.eduservice.entity.EduSubject;
import com.atguigu.eduservice.entity.excel.SubjectData;
import com.atguigu.eduservice.entity.subject.OneSubject;
import com.atguigu.eduservice.entity.subject.TwoSubject;
import com.atguigu.eduservice.listener.SubjectExcelListener;
import com.atguigu.eduservice.mapper.EduSubjectMapper;
import com.atguigu.eduservice.service.EduSubjectService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2022-04-02
 */
@Service
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject> implements EduSubjectService {

    @Override
    public void saveSubject(MultipartFile file, EduSubjectService eduSubjectService) {
        try {
            InputStream in = file.getInputStream();
            EasyExcel.read(in, SubjectData.class, new SubjectExcelListener(eduSubjectService)).sheet().doRead();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<OneSubject> getAllOneTwoSubject() {
        //最终要返回的list
        List<OneSubject> finalList = new ArrayList<>();
        //查找到所有一级分类
        QueryWrapper oneWrapper = new QueryWrapper();
        oneWrapper.eq("parent_id", "0");
        List<EduSubject> oneSubjectList = baseMapper.selectList(oneWrapper);
        //查找到所有二级分类
        QueryWrapper twoWrapper = new QueryWrapper();
        twoWrapper.ne("parent_id", "0");
        List<EduSubject> twoSubjectList = baseMapper.selectList(twoWrapper);
        //把一级分类按格式封装
        for (EduSubject oneSubject : oneSubjectList) {
            OneSubject one = new OneSubject();
            BeanUtils.copyProperties(oneSubject, one);
            //把二级分类按格式封装
            List<TwoSubject> twoFinalList = new ArrayList<>();
            for (EduSubject twoSubject : twoSubjectList) {
                String parentId = twoSubject.getParentId();
                if (parentId.equals(one.getId())){
                    TwoSubject two = new TwoSubject();
                    BeanUtils.copyProperties(twoSubject,two);
                    twoFinalList.add(two);
                }
             }
            one.setChildren(twoFinalList);
            finalList.add(one);
        }
            return finalList;
        }
    }
