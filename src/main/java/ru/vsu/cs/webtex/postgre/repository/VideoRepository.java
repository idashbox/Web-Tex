package ru.vsu.cs.webtex.postgre.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.vsu.cs.webtex.postgre.model.Video;


public interface VideoRepository extends JpaRepository<Video, String> {
}
