package com.youtube.YTClone.service;

import com.youtube.YTClone.dto.UploadVideoResponse;
import com.youtube.YTClone.dto.VideoDto;
import com.youtube.YTClone.model.Video;
import com.youtube.YTClone.repository.VideoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class VideoService {


    private final S3Service s3Service;
    private final VideoRepository videoRepository;

    public UploadVideoResponse uploadVideo(MultipartFile multipartFile){

        //upload to aws s3
        //Save video to DB
        String videoUrl=s3Service.uploadFile(multipartFile);
        var video = new Video();
        video.setVideoUrl(videoUrl);
       var savedVideo= videoRepository.save(video);
       return new UploadVideoResponse(savedVideo.getId(),savedVideo.getVideoUrl());


    }

    public VideoDto editVideo(VideoDto videoDto) {
        //FInd the video by video ID
        System.out.println(videoDto);
       var saveVideo= getVideoById(videoDto.getId());

        //Map the videoDto with video
        saveVideo.setTitle(videoDto.getTitle());
        saveVideo.setDescription((videoDto.getDescription()));
        saveVideo.setTags(videoDto.getTags());
        saveVideo.setThumbnailUrl((videoDto.getThumbnailUrl()));
        saveVideo.setVideoStatus(videoDto.getVideoStatus());

        //Save the video to the DB

        videoRepository.save(saveVideo);

        return videoDto;
    }

    public String uploadThumbnail(MultipartFile file, String videoId) {
        var savedVideo=getVideoById(videoId);
        String thumbnailUrl=s3Service.uploadFile(file);
        savedVideo.setThumbnailUrl(thumbnailUrl);
        videoRepository.save(savedVideo);

        return thumbnailUrl;
    }

    Video getVideoById(String videoId){
       return videoRepository.findById(videoId).orElseThrow(()-> new IllegalArgumentException("Cannot find video by id"+videoId));
    }
}
