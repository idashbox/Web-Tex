package ru.vsu.cs.webtex.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.vsu.cs.webtex.model.BidMongo;

public interface BidMongoRepository extends MongoRepository<BidMongo, String> { }
