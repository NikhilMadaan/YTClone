package com.youtube.YTClone.service;

import com.youtube.YTClone.model.Video;
import com.youtube.YTClone.repository.VideoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class VideoService {


    private final S3Service s3Service;
    private final VideoRepository videoRepository;

    public void uploadVideo(MultipartFile multipartFile){

        //upload to aws s3
        //Save video to DB
        String videoUrl=s3Service.uploadVideo(multipartFile);
        var video=new Video();
        video.setVideoUrl(videoUrl);
        videoRepository.save(video);

    }
}
