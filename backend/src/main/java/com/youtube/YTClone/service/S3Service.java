package com.youtube.YTClone.service;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class S3Service implements FileService{

    public static final String BUCKET_NAME = "videoholder31";
    private final AmazonS3Client amazonS3Client;

    @Override
    public String uploadFile(MultipartFile multipartFile){
        //upload file to AWS S3

        //Prepare a key
        var fileNameExtension=StringUtils.getFilenameExtension(multipartFile.getOriginalFilename());
        var key=UUID.randomUUID().toString()+"."+fileNameExtension;
        var metaData=new ObjectMetadata();
        metaData.setContentLength(multipartFile.getSize());
        metaData.setContentType(multipartFile.getContentType());

        try {
            amazonS3Client.putObject(BUCKET_NAME,key,multipartFile.getInputStream()
            ,metaData);
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,"An Exception occured while uploading the file");

        }//CannedAccessControlList.PublicRead to read file publicly
            amazonS3Client.setObjectAcl(BUCKET_NAME,key, CannedAccessControlList.PublicRead);
     return amazonS3Client.getResourceUrl(BUCKET_NAME,key);
    }
}
