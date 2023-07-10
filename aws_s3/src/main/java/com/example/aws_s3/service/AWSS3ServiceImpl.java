package com.example.aws_s3.service;

import com.amazonaws.services.dynamodbv2.xspec.M;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.example.aws_s3.ResponseData.ResponseData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.File;
import java.net.URL;
import java.util.List;

/**
 * @author ZHONGXI FENG
 * @projectName connect_to_s3
 * @create 7/9/2023-10:57 PM
 * @description
 */
@Service
@Slf4j
public class AWSS3ServiceImpl implements AWSS3Service{
    private final AmazonS3Client amazonS3;

    @Autowired
    public AWSS3ServiceImpl(AmazonS3Client amazonS3) {
        this.amazonS3 = amazonS3;
    }

    @Override
    public ResponseData uploadFile(String bucketName, String filePathInBucket, String uploadedFilePath) {
        PutObjectResult putObjectResult = amazonS3.putObject(bucketName, filePathInBucket, new File(uploadedFilePath));
        String eTag = putObjectResult.getETag();
        ResponseData responseData = new ResponseData();
        responseData.add("etag",eTag);
        responseData.setHttpStatus(HttpStatus.OK);
        return responseData;
    }

    @Override
    public ResponseData createBucket(String bucketName) {
        if(amazonS3.doesBucketExist(bucketName)) {
            log.info("Bucket name is not available."
                    + " Try again with a different Bucket name.");
            throw new RuntimeException("Bucket ["+bucketName+"] existed");
        }
        amazonS3.createBucket(bucketName);
        ResponseData responseData = new ResponseData();
        responseData.add("bucketName",bucketName);
        responseData.setHttpStatus(HttpStatus.OK);
        return responseData;
    }

    @Override
    public ResponseData getBucketList() {
        List<Bucket> buckets = amazonS3.listBuckets();
//        for(Bucket bucket : buckets) {
//            System.out.println(bucket.getName());
//        }
        ResponseData responseData = new ResponseData();
        responseData.add("bucketList",buckets);
        return responseData;
    }

    @Override
    public ResponseData deleteBucket(String bucketName) {
        amazonS3.deleteBucket("bucketName");
        ResponseData responseData = new ResponseData();
        responseData.add("Delete message","Delete ["+bucketName+"]"+"successfully");
        responseData.setHttpStatus(HttpStatus.OK);
        return responseData;
    }

    @Override
    public ResponseData getFileURLFromBucket(String bucketName, String fileName) {
        URL url = amazonS3.getUrl(bucketName, fileName);
        ObjectMetadata objectMetadata = amazonS3.getObjectMetadata(bucketName, fileName);
        ResponseData responseData = new ResponseData();
        responseData.add("url",url);
        responseData.add("metaData",objectMetadata);
        responseData.setHttpStatus(HttpStatus.OK);
        return responseData;
    }
}
