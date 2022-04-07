package com.atguigu.cos.utils;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author SongYu
 * @version 1.0.0
 * @ClassName CosConstantPropertiesUtils.java
 * @Description
 * @createTime 2022年04月04日 15:22:00
 */
@Component
public class CosConstantPropertiesUtils implements InitializingBean {

    //读取配置文件内容

    @Value("${tencent.cos.file.region}")
    private String region;

    @Value("${tencent.cos.file.keyid}")
    private String keyId;

    @Value("${tencent.cos.file.keysecret}")
    private String keySecret;

    @Value("${tencent.cos.file.bucketname}")
    private String bucketName;

    //定义公开静态常量
    public static String REGION;
    public static String ACCESS_KEY_ID;
    public static String ACCESS_KEY_SECRET;
    public static String BUCKET_NAME;

    @Override
    public void afterPropertiesSet() throws Exception {
        REGION = region;
        ACCESS_KEY_ID = keyId;
        ACCESS_KEY_SECRET = keySecret;
        BUCKET_NAME = bucketName;
    }
}
