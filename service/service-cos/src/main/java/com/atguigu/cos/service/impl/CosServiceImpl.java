package com.atguigu.cos.service.impl;

import com.atguigu.cos.service.CosService;
import com.atguigu.cos.utils.CosConstantPropertiesUtils;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.exception.CosClientException;
import com.qcloud.cos.exception.CosServiceException;
import com.qcloud.cos.model.*;
import com.qcloud.cos.region.Region;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.InputStream;
import java.util.*;

import static com.qcloud.cos.demo.BucketRefererDemo.bucketName;
import static com.qcloud.cos.demo.BucketRefererDemo.cosClient;

/**
 * @author SongYu
 * @version 1.0.0
 * @ClassName CosServiceImpl.java
 * @Description
 * @createTime 2022年04月04日 15:21:00
 */
@Service
public class CosServiceImpl implements CosService {
    @Override
    public String uploadFile(MultipartFile file) {
        // 工具类获取值
        String region = CosConstantPropertiesUtils.REGION;
        String accessKeyId = CosConstantPropertiesUtils.ACCESS_KEY_ID;
        String accessKeySecret = CosConstantPropertiesUtils.ACCESS_KEY_SECRET;
        String bucketName = CosConstantPropertiesUtils.BUCKET_NAME;

        try {
            COSCredentials cred = new BasicCOSCredentials(accessKeyId, accessKeySecret);
            Region r = new Region(region);
            ClientConfig clientConfig = new ClientConfig(r);
            COSClient cosClient = new COSClient(cred, clientConfig);

//            //查询存储桶列表
//            List<Bucket> buckets = cosClient.listBuckets();
//            for (Bucket bucketElement : buckets) {
//                String bucketName1 = bucketElement.getName();
//                String bucketLocation = bucketElement.getLocation();
//                System.out.println(bucketName1+"==="+bucketLocation);
//            }
//
            //上传
            InputStream inputStream = file.getInputStream();
            cosClient.putObject(bucketName, file.getOriginalFilename(), inputStream, null);
            cosClient.shutdown();

            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String deleteFile(String filename) {
        // 工具类获取值
        String region = CosConstantPropertiesUtils.REGION;
        String accessKeyId = CosConstantPropertiesUtils.ACCESS_KEY_ID;
        String accessKeySecret = CosConstantPropertiesUtils.ACCESS_KEY_SECRET;
        String bucketName = CosConstantPropertiesUtils.BUCKET_NAME;

        try {
            COSCredentials cred = new BasicCOSCredentials(accessKeyId, accessKeySecret);
            Region r = new Region(region);
            ClientConfig clientConfig = new ClientConfig(r);
            COSClient cosClient = new COSClient(cred, clientConfig);
            cosClient.deleteObject(bucketName, filename);
            cosClient.shutdown();

            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String loaddownOssFile(String filename) {
        // 工具类获取值
        String region = CosConstantPropertiesUtils.REGION;
        String accessKeyId = CosConstantPropertiesUtils.ACCESS_KEY_ID;
        String accessKeySecret = CosConstantPropertiesUtils.ACCESS_KEY_SECRET;
        String bucketName = CosConstantPropertiesUtils.BUCKET_NAME;

        try {
//            // 方法1 获取下载输入流
//            GetObjectRequest getObjectRequest = new GetObjectRequest(bucketName, filename);
//            COSObject cosObject = cosClient.getObject(getObjectRequest);
//            COSObjectInputStream cosObjectInput = cosObject.getObjectContent();
//            // 下载对象的 CRC64
//            String crc64Ecma = cosObject.getObjectMetadata().getCrc64Ecma();
//            cosObjectInput.close();
            // 方法2 下载文件到本地的路径，例如 D 盘的某个目录
            String outputFilePath = "D:\\";
            File downFile = new File(outputFilePath);
            GetObjectRequest getObjectRequest = new GetObjectRequest(bucketName, filename);
            ObjectMetadata downObjectMeta = cosClient.getObject(getObjectRequest, downFile);

            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<COSObjectSummary> getlist(String filePath, String filename) {
        List<COSObjectSummary> list = new ArrayList<COSObjectSummary>();
        // 工具类获取值
        String region = CosConstantPropertiesUtils.REGION;
        String accessKeyId = CosConstantPropertiesUtils.ACCESS_KEY_ID;
        String accessKeySecret = CosConstantPropertiesUtils.ACCESS_KEY_SECRET;
        String bucketName = CosConstantPropertiesUtils.BUCKET_NAME;

        COSCredentials cred = new BasicCOSCredentials(accessKeyId, accessKeySecret);
        Region r = new Region(region);
        ClientConfig clientConfig = new ClientConfig(r);
        COSClient cosClient = new COSClient(cred, clientConfig);

        ListObjectsRequest listObjectsRequest = new ListObjectsRequest();
        // 设置bucket名称
        listObjectsRequest.setBucketName(bucketName);
        // prefix表示列出的object的key以prefix开始
        if (filePath != null) {
            filename = filePath + "/" + filename;
        }
        listObjectsRequest.setPrefix(filename);
        // deliter表示分隔符, 设置为/表示列出当前目录下的object, 设置为空表示列出所有的object
        listObjectsRequest.setDelimiter("/");
        // 设置最大遍历出多少个对象, 一次listobject最大支持1000
        listObjectsRequest.setMaxKeys(1000);
        ObjectListing objectListing = null;
        do {
            try {
                objectListing = cosClient.listObjects(listObjectsRequest);
            } catch (CosServiceException e) {
                e.printStackTrace();
            } catch (CosClientException e) {
                e.printStackTrace();
            }
            // common prefix表示表示被delimiter截断的路径, 如delimter设置为/, common prefix则表示所有子目录的路径
            List<String> commonPrefixs = objectListing.getCommonPrefixes();

            // object summary表示所有列出的object列表
            List<COSObjectSummary> cosObjectSummaries = objectListing.getObjectSummaries();
//            COSObjectSummary cos = new COSObjectSummary();
            for (COSObjectSummary cosObjectSummary : cosObjectSummaries) {
                COSObjectSummary cos = new COSObjectSummary();
                // 文件的路径key
                String key = cosObjectSummary.getKey();
                // 文件的etag
                String etag = cosObjectSummary.getETag();
                // 文件的长度
                long fileSize = (cosObjectSummary.getSize()) / 1024L;
                // 文件的存储类型
                String storageClasses = cosObjectSummary.getStorageClass();
                System.out.println(key + "=" + etag + "=" + fileSize + "=" + storageClasses);
                cos.setBucketName(bucketName);
                cos.setLastModified(new Date());
                cos.setKey(key);
                cos.setETag(etag);
                cos.setSize(fileSize);
                cos.setStorageClass(storageClasses);
                list.add(cos);
            }

            String nextMarker = objectListing.getNextMarker();
            listObjectsRequest.setMarker(nextMarker);
        } while (objectListing.isTruncated());
        return list;
    }
}
