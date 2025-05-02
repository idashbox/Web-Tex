package ru.vsu.cs.webtex.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BidDto {
    private Long id, videoId;
    private String content;
    private Instant timestamp;
}
