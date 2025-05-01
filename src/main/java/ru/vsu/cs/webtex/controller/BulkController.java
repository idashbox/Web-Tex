package ru.vsu.cs.webtex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.vsu.cs.webtex.dto.VideoDto;
import ru.vsu.cs.webtex.model.Video;
import ru.vsu.cs.webtex.service.VideoService;

@RestController
@RequestMapping("/api/bulk")
public class BulkController {

    @Autowired
    private VideoService videoService;

    @PostMapping("/videos")
    public ResponseEntity<String> bulkInsertVideos(@RequestParam int count) {
        for (int i = 0; i < count; i++) {
            // Создаём VideoDto, который соответствует структуре данных
            VideoDto videoDto = VideoDto.builder()
                    .title("Title " + i)
                    .description("Description for video " + i)
                    .genreId("1")
                    .build();
            videoService.create(videoDto);  // Используем метод create для добавления в базу данных
        }
        return ResponseEntity.ok("Bulk insert completed");
    }
}

