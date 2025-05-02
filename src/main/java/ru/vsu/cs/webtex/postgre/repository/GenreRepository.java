package ru.vsu.cs.webtex.postgre.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.vsu.cs.webtex.postgre.model.Genre;

public interface GenreRepository extends JpaRepository<Genre, String> { }
