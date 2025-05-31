package ru.vsu.cs.webtex.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import ru.vsu.cs.webtex.model.BidMongo;
import ru.vsu.cs.webtex.model.GenreMongo;
import ru.vsu.cs.webtex.model.VideoMongo;
import ru.vsu.cs.webtex.postgre.model.Bid;
import ru.vsu.cs.webtex.postgre.model.Genre;
import ru.vsu.cs.webtex.postgre.model.Video;
import ru.vsu.cs.webtex.postgre.repository.BidRepository;
import ru.vsu.cs.webtex.postgre.repository.GenreRepository;
import ru.vsu.cs.webtex.postgre.repository.VideoRepository;
import ru.vsu.cs.webtex.repository.BidMongoRepository;
import ru.vsu.cs.webtex.repository.GenreMongoRepository;
import ru.vsu.cs.webtex.repository.VideoMongoRepository;

import java.time.ZoneOffset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

//@Service
//public class MongoToPostgresMigrator {
//    @Autowired
//    private MongoTemplate mongoTemplate;
//    @Autowired private GenreRepository genreRepository;
//    @Autowired private VideoRepository videoRepository;
//    @Autowired private BidRepository bidRepository;
//    @Autowired private GenreMongoRepository genreMongoRepository;
//    @Autowired private VideoMongoRepository videoMongoRepository;
//    @Autowired private BidMongoRepository bidMongoRepository;
//
//    public void migrate() {
//        Map<String, Genre> genreMap = new HashMap<>();
//        for (GenreMongo gm : mongoTemplate.findAll(GenreMongo.class)) {
//            Genre g = new Genre();
//            g.setName(gm.getName());
//            genreRepository.save(g);
//            genreMap.put(gm.getId(), g);
//        }
//
//        Map<String, Video> videoMap = new HashMap<>();
//        for (VideoMongo vm : mongoTemplate.findAll(VideoMongo.class)) {
//            Video v = new Video();
//            v.setTitle(vm.getTitle());
//            v.setDescription(vm.getDescription());
//            v.setGenre(genreMap.get(vm.getGenreId()));
//            videoRepository.save(v);
//            videoMap.put(vm.getId(), v);
//        }
//
//        for (BidMongo bm : mongoTemplate.findAll(BidMongo.class)) {
//            Bid b = new Bid();
//            b.setContent(bm.getContent());
//            b.setVideo(videoMap.get(bm.getVideoId()));
//            b.setTimestamp(bm.getTimestamp().toInstant());
//            bidRepository.save(b);
//        }
//
//        System.out.println("Таблицы мигрированы из Mongo в Postgres");
//    }
//    @Bean
//    public CommandLineRunner migrateData() {
//        return args -> {
//            System.out.println("----- ЖАНРЫ из Mongo -----");
//            List<GenreMongo> mongoGenres = genreMongoRepository.findAll();
//            Map<String, Genre> genreMap = new HashMap<>();
//
//            for (GenreMongo gm : mongoGenres) {
//                Genre pgGenre = new Genre();
//                pgGenre.setName(gm.getName());
//                genreRepository.save(pgGenre);
//                genreMap.put(gm.getId(), pgGenre);
//                System.out.println(gm);
//            }
//
//            System.out.println("----- ВИДЕО из Mongo -----");
//            List<VideoMongo> mongoVideos = videoMongoRepository.findAll();
//            Map<String, Long> mongoIdToPostgresId = new HashMap<>();
//
//            for (VideoMongo vm : mongoVideos) {
//                Video pgVideo = new Video();
//                pgVideo.setTitle(vm.getTitle());
//                pgVideo.setDescription(vm.getDescription());
//                pgVideo.setGenre(genreMap.get(vm.getGenreId()));
//                videoRepository.save(pgVideo);
//                mongoIdToPostgresId.put(vm.getId(), pgVideo.getId());
//                System.out.println(vm);
//            }
//
//            System.out.println("----- ЗАЯВКИ из Mongo -----");
//            List<BidMongo> mongoBids = bidMongoRepository.findAll();
//
//            for (BidMongo bm : mongoBids) {
//                Bid pgBid = new Bid();
//                pgBid.setContent(bm.getContent());
//                pgBid.setTimestamp(bm.getTimestamp().toInstant());
//
//                Long postgresVideoId = mongoIdToPostgresId.get(bm.getVideoId());
//                if (postgresVideoId != null) {
//                    videoRepository.findById(String.valueOf(postgresVideoId)).ifPresentOrElse(
//                            pgBid::setVideo,
//                            () -> System.err.println("Видео не найдено в Postgres по ID: " + postgresVideoId)
//                    );
//                } else {
//                    System.err.println("Не удалось сопоставить видео Mongo ID: " + bm.getVideoId());
//                }
//
//                bidRepository.save(pgBid);
//                System.out.println(bm);
//            }
//
//            System.out.println("Данные Bids мигрированы из Mongo в Postgres");
//        };
//    }

//}
