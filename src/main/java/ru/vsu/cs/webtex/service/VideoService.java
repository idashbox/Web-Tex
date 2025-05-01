package ru.vsu.cs.webtex.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.vsu.cs.webtex.dto.VideoDto;
import ru.vsu.cs.webtex.model.Video;
import ru.vsu.cs.webtex.repository.VideoRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VideoService {

    private final VideoRepository videoRepository;

    public List<VideoDto> getAll() {
        return videoRepository.findAll().stream().map(this::mapToDto).collect(Collectors.toList());
    }

    public VideoDto getById(String id) {
        return videoRepository.findById(id).map(this::mapToDto).orElse(null);
    }

    public VideoDto create(VideoDto dto) {
        Video video = mapToEntity(dto);
        return mapToDto(videoRepository.save(video));
    }

    public VideoDto update(String id, VideoDto dto) {
        Optional<Video> videoOptional = videoRepository.findById(id);
        if (videoOptional.isPresent()) {
            Video updated = videoOptional.get();
            updated.setTitle(dto.getTitle());
            updated.setDescription(dto.getDescription());
            updated.setGenreId(dto.getGenreId());
            return mapToDto(videoRepository.save(updated));
        }
        return null;
    }

    public void delete(String id) {
        videoRepository.deleteById(id);
    }

    private VideoDto mapToDto(Video video) {
        return VideoDto.builder()
                .id(video.getId())
                .title(video.getTitle())
                .description(video.getDescription())
                .genreId(video.getGenreId())
                .build();
    }

    private Video mapToEntity(VideoDto dto) {
        return Video.builder()
                .id(dto.getId())
                .title(dto.getTitle())
                .description(dto.getDescription())
                .genreId(dto.getGenreId())
                .build();
    }
}
