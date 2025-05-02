package ru.vsu.cs.webtex.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.vsu.cs.webtex.dto.VideoDto;
import ru.vsu.cs.webtex.postgre.model.Genre;
import ru.vsu.cs.webtex.postgre.model.Video;
import ru.vsu.cs.webtex.postgre.repository.GenreRepository;
import ru.vsu.cs.webtex.postgre.repository.VideoRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VideoService {

    private final VideoRepository videoRepository;
    private final GenreRepository genreRepository;

    public List<VideoDto> getAll() {
        return videoRepository.findAll().stream().map(this::mapToDto).collect(Collectors.toList());
    }

    public Optional<VideoDto> getById(String id) {
        return videoRepository.findById(id).map(this::mapToDto);
    }

    public VideoDto create(VideoDto dto) {
        if (dto.getTitle() == null || dto.getDescription() == null || dto.getGenreId() == null) {
            throw new RuntimeException("Title, description, and genre ID are required");
        }
        Video video = mapToEntity(dto);
        return mapToDto(videoRepository.save(video));
    }

    public VideoDto update(String id, VideoDto dto) {
        Genre genre = genreRepository.findById(String.valueOf(dto.getGenreId()))
                .orElseThrow(() -> new RuntimeException("Genre not found with ID " + dto.getGenreId()));
        Optional<Video> videoOptional = videoRepository.findById(id);
        if (videoOptional.isPresent()) {
            Video updated = videoOptional.get();
            updated.setTitle(dto.getTitle());
            updated.setDescription(dto.getDescription());
            updated.setGenre(genre);
            return mapToDto(videoRepository.save(updated));
        }
        throw new RuntimeException("Video not found with ID " + id);
    }

    public void delete(String id) {
        videoRepository.deleteById(id);
    }

    private VideoDto mapToDto(Video video) {
        return VideoDto.builder()
                .id(video.getId())
                .title(video.getTitle())
                .description(video.getDescription())
                .genreId(video.getGenre() != null ? video.getGenre().getId() : null)
                .build();
    }

    private Video mapToEntity(VideoDto dto) {
        if (dto.getGenreId() == null) {
            throw new RuntimeException("Genre ID is required");
        }
        Genre genre = genreRepository.findById(String.valueOf(dto.getGenreId()))
                .orElseThrow(() -> new RuntimeException("Genre not found with ID " + dto.getGenreId()));

        return Video.builder()
                .id(dto.getId())
                .title(dto.getTitle())
                .description(dto.getDescription())
                .genre(genre)
                .build();
    }
}
