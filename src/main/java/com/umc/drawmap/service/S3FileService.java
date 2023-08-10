package com.umc.drawmap.service;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class S3FileService {
    private final AmazonS3Client amazonS3Client;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    public String upload(MultipartFile file) { // 더 수정하기 (컨트롤러 , DTO, DB)

        // S3 에 저장 되는 파일 이름은  업로드 시각 + 유저 이름 으로 하자.
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userId = ((UserDetails)authentication.getPrincipal()).getUsername();

        try {
            String nowTime = new Date().toString();


            String fileName = nowTime + userId;

            ObjectMetadata fileMetaData = new ObjectMetadata();

            fileMetaData.setContentType(file.getContentType());
            fileMetaData.setContentLength(file.getSize());

            PutObjectResult putObjectResult = amazonS3Client.putObject(bucket, fileName, file.getInputStream(), fileMetaData);
            return "https://draw-map.s3.amazonaws.com/"+ fileName;
        } catch (IOException ex) {
            ex.printStackTrace();
        } return "Fail";
    }

}