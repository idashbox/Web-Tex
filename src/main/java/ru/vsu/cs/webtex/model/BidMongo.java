package ru.vsu.cs.webtex.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document("bid")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class BidMongo {
    @Id
    private String id;
    private String content;
    private String videoId;
    private LocalDateTime timestamp;
}
