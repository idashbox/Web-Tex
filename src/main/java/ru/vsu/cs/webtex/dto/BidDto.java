package ru.vsu.cs.webtex.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BidDto {
    private String id, content, videoId;
    private LocalDateTime timestamp;
}
