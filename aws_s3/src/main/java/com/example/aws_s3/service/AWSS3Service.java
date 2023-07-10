package com.example.aws_s3.service;

import org.springframework.stereotype.Service;
import com.example.aws_s3.ResponseData.ResponseData;
import java.util.List;

/**
 * @author ZHONGXI FENG
 * @projectName connect_to_s3
 * @create 7/9/2023-10:54 PM
 * @description
 */


public interface AWSS3Service {
    public ResponseData uploadFile(String bucketName, String filePathInBucket, String uploadedFilePath);
    public ResponseData createBucket(String bucketName);
    public ResponseData getBucketList();

    public ResponseData deleteBucket(String bucketName);

    public ResponseData getFileURLFromBucket(String bucketName, String fileName);


}
