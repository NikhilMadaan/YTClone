package com.youtube.YTClone.repository;

import com.youtube.YTClone.model.Video;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface VideoRepository extends MongoRepository<Video,String> {
}
