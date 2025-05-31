package ru.vsu.cs.webtex.service;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vsu.cs.webtex.dto.GenreDto;
import ru.vsu.cs.webtex.model.GenreMongo;
import ru.vsu.cs.webtex.postgre.model.Genre;
import ru.vsu.cs.webtex.postgre.repository.GenreRepository;
import ru.vsu.cs.webtex.repository.GenreMongoRepository;

import java.util.List;
import java.util.Optional;
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

    @Transactional
    public GenreDto create(GenreDto genreDto) {
        // Пытаемся найти существующий жанр
        Optional<Genre> existingGenre = genreRepository.findByName(genreDto.getName());

        if (existingGenre.isPresent()) {
            return toDto(existingGenre.get());
        }

        try {
            // Пытаемся сохранить новый жанр
            Genre genre = new Genre();
            genre.setName(genreDto.getName());
            genre = genreRepository.save(genre);
            return toDto(genre);
        } catch (DataIntegrityViolationException | ObjectOptimisticLockingFailureException e) {
            // Обрабатываем коллизию: если параллельный поток уже создал жанр
            Optional<Genre> conflictedGenre = genreRepository.findByName(genreDto.getName());
            if (conflictedGenre.isPresent()) {
                return toDto(conflictedGenre.get());
            }
            throw new RuntimeException("Failed to create genre", e);
        }
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
