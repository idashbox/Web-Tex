package ru.vsu.cs.webtex.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("videos")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Video {
    @Id
    private String id;

    private String title;
    //посмотреть анотацию
    @TextIndexed
    private String description;

    private String genreId;
}
