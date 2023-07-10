package com.example.aws_s3.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author ZHONGXI FENG
 * @projectName connect_to_s3
 * @create 7/9/2023-10:44 PM
 * @description
 */

@Configuration
public class AWS_S3_Config {
    @Value("${access-key}")
    private String accessKey;
    @Value("${secret-key}")
    private String accessSecretKey;

    @Bean
    public AmazonS3Client amazonS3(){
        BasicAWSCredentials basicAWSCredentials = new BasicAWSCredentials(accessKey, accessSecretKey);
        AmazonS3Client amazonS3=
                (AmazonS3Client)AmazonS3ClientBuilder
                .standard()
                .withCredentials(new AWSStaticCredentialsProvider(basicAWSCredentials))
                .withRegion(Regions.US_EAST_1)
                .build();
        return amazonS3;
    }
}
