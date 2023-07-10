package com.example.aws_s3.ResponseData;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import javax.management.ObjectName;
import java.util.HashMap;

/**
 * @author ZHONGXI FENG
 * @projectName connect_to_s3
 * @create 7/9/2023-11:59 PM
 * @description
 */

@Data
public class ResponseData {
    public HashMap<String, Object> dataMap;
    public HttpStatus httpStatus;

    public ResponseData(){
        dataMap = new HashMap<>();
        httpStatus = HttpStatus.OK;
    }
    public void add(String key,Object value){
        dataMap.put(key,value);
    }
}
