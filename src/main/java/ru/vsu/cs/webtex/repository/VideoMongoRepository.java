package ru.vsu.cs.webtex.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.vsu.cs.webtex.model.VideoMongo;

import java.util.List;

public interface VideoMongoRepository extends MongoRepository<VideoMongo, String> {
    List<VideoMongo> findByDescriptionContainingIgnoreCase(String desc);
}
