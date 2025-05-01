package ru.vsu.cs.webtex.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.vsu.cs.webtex.model.Bid;

public interface BidRepository extends MongoRepository<Bid, String> { }
