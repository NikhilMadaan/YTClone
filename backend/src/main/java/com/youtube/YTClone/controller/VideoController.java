package com.youtube.YTClone.controller;

import com.youtube.YTClone.dto.UploadVideoResponse;
import com.youtube.YTClone.dto.VideoDto;
import com.youtube.YTClone.model.Video;
import com.youtube.YTClone.service.VideoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/videos")
@RequiredArgsConstructor
public class VideoController {

    private final VideoService videoService;
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UploadVideoResponse uploadVideo(@RequestParam("file")MultipartFile file){
     return videoService.uploadVideo(file);


    }
    @PostMapping("/thumbnail")
    @ResponseStatus(HttpStatus.CREATED)
    public String uploadThumbnail(@RequestParam("file")MultipartFile file, @RequestParam("videoId")String videoId){

        System.out.println(1);
        return videoService.uploadThumbnail(file,videoId);

    }
    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public VideoDto editVideoMetadata(@RequestBody VideoDto videoDto){
        System.out.println("1");
        System.out.println(videoDto);
        return videoService.editVideo(videoDto);

    }
    @GetMapping("/{videoId}")
    @ResponseStatus(HttpStatus.OK)
    public VideoDto getVideoDetails(@PathVariable String videoId){
      return  videoService.getVideoDetails(videoId);
    }

}

