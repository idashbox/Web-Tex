package ru.vsu.cs.webtex.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.vsu.cs.webtex.dto.GenreDto;
import ru.vsu.cs.webtex.model.Genre;
import ru.vsu.cs.webtex.repository.GenreRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GenreService {

    private final GenreRepository genreRepository;

    public List<GenreDto> getAll() {
        return genreRepository.findAll()
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public GenreDto getById(String id) {
        return genreRepository.findById(id)
                .map(this::toDto)
                .orElseThrow(() -> new RuntimeException("Genre not found"));
    }

    public GenreDto create(GenreDto dto) {
        Genre genre = toEntity(dto);
        return toDto(genreRepository.save(genre));
    }

    public GenreDto update(String id, GenreDto dto) {
        Genre genre = genreRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Genre not found"));

        genre.setName(dto.getName());
        return toDto(genreRepository.save(genre));
    }

    public void delete(String id) {
        genreRepository.deleteById(id);
    }

    private GenreDto toDto(Genre genre) {
        return GenreDto.builder()
                .id(genre.getId())
                .name(genre.getName())
                .build();
    }

    private Genre toEntity(GenreDto dto) {
        return Genre.builder()
                .id(dto.getId())
                .name(dto.getName())
                .build();
    }
}
