package com.atguigu.cos.service;

import com.qcloud.cos.model.COSObjectSummary;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author SongYu
 * @version 1.0.0
 * @ClassName CosService.java
 * @Description
 * @createTime 2022年04月04日 15:20:00
 */
public interface CosService {
    String uploadFile(MultipartFile file);

    String deleteFile(String filename);

    String loaddownOssFile(String filename);

    List<COSObjectSummary> getlist(String filePath, String filename);
}
