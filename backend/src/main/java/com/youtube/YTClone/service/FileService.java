package com.youtube.YTClone.service;

import org.springframework.web.multipart.MultipartFile;

//The purpose of this interface is so that in future if we dont want to use the S3 to upload the video
//then we can replace the server
public interface FileService {

    String uploadVideo(MultipartFile multipartFile);
}
