package ru.vsu.cs.webtex.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.vsu.cs.webtex.model.GenreMongo;

public interface GenreMongoRepository extends MongoRepository<GenreMongo, String> { }
