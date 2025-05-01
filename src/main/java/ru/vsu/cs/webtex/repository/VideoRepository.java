package ru.vsu.cs.webtex.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.vsu.cs.webtex.model.Video;

import java.util.List;

public interface VideoRepository extends MongoRepository<Video, String> {
    List<Video> findByDescriptionContainingIgnoreCase(String desc);
}
