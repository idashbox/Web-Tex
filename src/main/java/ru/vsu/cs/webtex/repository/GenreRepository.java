package ru.vsu.cs.webtex.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.vsu.cs.webtex.model.Genre;

public interface GenreRepository extends MongoRepository<Genre, String> { }
