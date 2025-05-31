package ru.vsu.cs.webtex.postgre.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.vsu.cs.webtex.postgre.model.Genre;

import java.util.Optional;

public interface GenreRepository extends JpaRepository<Genre, String> {
    Optional<Genre> findByName(String name);
}
