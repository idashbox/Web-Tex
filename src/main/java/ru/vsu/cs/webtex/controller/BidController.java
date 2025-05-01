package ru.vsu.cs.webtex.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.vsu.cs.webtex.dto.BidDto;
import ru.vsu.cs.webtex.service.BidService;

import java.util.List;

@RestController
@RequestMapping("/bids")
@RequiredArgsConstructor
public class BidController {

    private final BidService bidService;

    @GetMapping
    public ResponseEntity<List<BidDto>> getAllBids() {
        return ResponseEntity.ok(bidService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BidDto> getBid(@PathVariable String id) {
        return ResponseEntity.ok(bidService.getById(id));
    }

    @PostMapping
    public ResponseEntity<BidDto> createBid(@RequestBody BidDto dto) {
        return ResponseEntity.ok(bidService.create(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBid(@PathVariable String id) {
        bidService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
