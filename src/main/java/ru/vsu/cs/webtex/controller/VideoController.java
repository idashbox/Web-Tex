package ru.vsu.cs.webtex.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.vsu.cs.webtex.dto.VideoDto;
import ru.vsu.cs.webtex.service.VideoService;

import java.util.List;

@RestController
@RequestMapping("/videos")
@RequiredArgsConstructor
public class VideoController {

    private final VideoService videoService;

    @GetMapping
    public ResponseEntity<List<VideoDto>> getAll() {
        return ResponseEntity.ok(videoService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<VideoDto> getById(@PathVariable String id) {
        VideoDto dto = videoService.getById(id);
        return dto != null ? ResponseEntity.ok(dto) : ResponseEntity.notFound().build(); // вынести в сервис, выкинуть ошибку
    }

    @PostMapping
    public ResponseEntity<VideoDto> create(@RequestBody VideoDto dto) {
        return ResponseEntity.ok(videoService.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<VideoDto> update(@PathVariable String id, @RequestBody VideoDto dto) {
        VideoDto updated = videoService.update(id, dto);
        return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }
//скрипт для монго
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        videoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
