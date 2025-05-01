package ru.vsu.cs.webtex.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document("bids")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Bid {
    @Id
    private String id;
    private String content;
    private String videoId;
    private LocalDateTime timestamp;
}
