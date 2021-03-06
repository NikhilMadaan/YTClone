package com.youtube.YTClone.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Set;
@Document(value="video")
@Data
public class Video {

    @Id
    private String id;
    private String title;
    private String description;
    private String userId;
    private String likes;
    private String dislikes;
    private Set<String> tags;
    private String videoUrl;
    private VideoStatus videoStatus;
    private Integer viewCount;
    private String thumbnailUrl;
    private List<Comment> commentList;


}
