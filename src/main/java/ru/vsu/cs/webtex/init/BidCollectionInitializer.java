package ru.vsu.cs.webtex.init;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.mongodb.core.CollectionOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BidCollectionInitializer implements CommandLineRunner {

    private final MongoTemplate mongoTemplate;

    @Override
    public void run(String... args) {
        if (!mongoTemplate.collectionExists("bids")) {
            mongoTemplate.createCollection("bids", CollectionOptions.empty()
                    .capped().size(1024 * 1024).maxDocuments(1000));
        }
    }
}
