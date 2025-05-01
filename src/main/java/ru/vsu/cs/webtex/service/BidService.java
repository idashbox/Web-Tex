package ru.vsu.cs.webtex.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.vsu.cs.webtex.dto.BidDto;
import ru.vsu.cs.webtex.model.Bid;
import ru.vsu.cs.webtex.repository.BidRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BidService {

    private final BidRepository bidRepository;

    public List<BidDto> getAll() {
        return bidRepository.findAll()
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public BidDto getById(String id) {
        return bidRepository.findById(id)
                .map(this::toDto)
                .orElseThrow(() -> new RuntimeException("Bid not found"));
    }

    public BidDto create(BidDto dto) {
        Bid bid = toEntity(dto);
        bid.setTimestamp(LocalDateTime.now());
        return toDto(bidRepository.save(bid));
    }

    public void delete(String id) {
        bidRepository.deleteById(id);
    }

    private BidDto toDto(Bid bid) {
        return BidDto.builder()
                .id(bid.getId())
                .content(bid.getContent())
                .videoId(bid.getVideoId())
                .timestamp(bid.getTimestamp())
                .build();
    }

    private Bid toEntity(BidDto dto) {
        return Bid.builder()
                .id(dto.getId())
                .content(dto.getContent())
                .videoId(dto.getVideoId())
                .timestamp(dto.getTimestamp()) // если при создании будет null, то заменим в create()
                .build();
    }
}
