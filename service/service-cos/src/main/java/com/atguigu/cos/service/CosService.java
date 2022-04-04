package com.atguigu.cos.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author SongYu
 * @version 1.0.0
 * @ClassName CosService.java
 * @Description TODO
 * @createTime 2022年04月04日 15:20:00
 */
public interface CosService {
    String uploadFile(MultipartFile file);

    String deleteFile(String filename);

    String loaddownOssFile(String filename);

    String getlist(String filePath, String filename);
}
