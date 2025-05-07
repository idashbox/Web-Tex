package ru.vsu.cs.webtex.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("video")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class VideoMongo {
    @Id
    private String id;

    private String title;
    @TextIndexed
    private String description;

    private String genreId;

}
