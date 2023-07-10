package com.example.aws_s3.controller;

import com.example.aws_s3.ResponseData.ResponseData;
import com.example.aws_s3.service.AWSS3Service;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ZHONGXI FENG
 * @projectName connect_to_s3
 * @create 7/10/2023-3:07 AM
 * @description
 */
@RestController
public class AWSS3Controller {
    private final AWSS3Service awss3Service;
    public String filePathInBucket = "myfile/1.jpg";
    public String uploadedFilePath = "C:\\Users\\ZHONGXI\\Desktop\\1.jpg" ;

    @Autowired
    public AWSS3Controller(AWSS3Service awss3Service) {
        this.awss3Service = awss3Service;
    }


    @PostMapping("/upload")
    public ResponseEntity<ResponseData> uploadFile(@RequestParam String bucketName){
        ResponseData responseData = awss3Service.uploadFile(bucketName, filePathInBucket, uploadedFilePath);
        ResponseEntity<ResponseData> responseDataResponseEntity = new ResponseEntity<>(responseData,HttpStatusCode.valueOf(200));
        return responseDataResponseEntity;
    }

    @PostMapping("/bucket")
    public ResponseEntity<ResponseData> createBucket(@RequestParam("bucketName") String bucketName){
        ResponseData bucket = awss3Service.createBucket(bucketName);
        return new ResponseEntity<>(bucket,HttpStatusCode.valueOf(200));
    }

    @GetMapping("/bucket")
    public ResponseEntity<ResponseData> getBucketList(){
        ResponseData bucketList = awss3Service.getBucketList();
        return new ResponseEntity<>(bucketList,HttpStatusCode.valueOf(HttpStatus.SC_OK));
    }

    @GetMapping("/fileEtg")
    public ResponseEntity<ResponseData> getFileEtag(@RequestParam("bucketName") String bucketName,@RequestParam("filePath") String filePath){
        ResponseData fileURLFromBucket = awss3Service.getFileURLFromBucket(bucketName, filePath);
        return new ResponseEntity<>(fileURLFromBucket,HttpStatusCode.valueOf(HttpStatus.SC_OK));
    }
}
