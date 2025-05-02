package ru.vsu.cs.webtex.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.vsu.cs.webtex.dto.BidDto;
import ru.vsu.cs.webtex.postgre.model.Bid;
import ru.vsu.cs.webtex.postgre.model.Video;
import ru.vsu.cs.webtex.postgre.repository.BidRepository;
import ru.vsu.cs.webtex.postgre.repository.VideoRepository;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BidService {

    private final BidRepository bidRepository;
    private final VideoRepository videoRepository;

    public List<BidDto> getAll() {
        return bidRepository.findAll().stream().map(this::toDto).collect(Collectors.toList());
    }

    public Optional<BidDto> getById(String id) {
        return bidRepository.findById(id).map(this::toDto);
    }

    public BidDto create(BidDto dto) {
        if (dto.getContent() == null || dto.getVideoId() == null || dto.getTimestamp() == null) {
            throw new RuntimeException("Content, video ID, and timestamp are required");
        }
        Bid bid = toEntity(dto);
        return toDto(bidRepository.save(bid));
    }

    public void delete(String id) {
        bidRepository.deleteById(id);
    }

    private BidDto toDto(Bid bid) {
        return BidDto.builder()
                .id(bid.getId())
                .content(bid.getContent())
                .videoId(bid.getVideo() != null ? bid.getVideo().getId() : null)
                .timestamp(bid.getTimestamp())
                .build();
    }

    private Bid toEntity(BidDto dto) {
        if (dto.getVideoId() == null) {
            throw new RuntimeException("Video ID is required");
        }
        Video video = videoRepository.findById(String.valueOf(dto.getVideoId()))
                .orElseThrow(() -> new RuntimeException("Video not found with ID " + dto.getVideoId()));

        return Bid.builder()
                .id(dto.getId())
                .content(dto.getContent())
                .video(video)
                .timestamp(dto.getTimestamp().atZone(ZoneId.systemDefault()).toInstant())
                .build();
    }
}
