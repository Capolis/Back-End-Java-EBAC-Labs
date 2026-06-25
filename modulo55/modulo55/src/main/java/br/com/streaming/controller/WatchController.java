package br.com.streaming.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.streaming.domain.dto.WatchHistoryResponseDTO;
import br.com.streaming.domain.dto.WatchRequestDTO;
import br.com.streaming.service.WatchService;

@RestController
@RequestMapping("/api/watch")
public class WatchController {

    private final WatchService watchService;

    public WatchController(WatchService watchService) {
        this.watchService = watchService;
    }

    @PostMapping
    public ResponseEntity<String> watchMovie(@RequestBody WatchRequestDTO request) {
        String responseMessage = watchService.watchMovie(request);
        return ResponseEntity.ok(responseMessage);
    }

    @GetMapping("/history/{userId}")
    public ResponseEntity<List<WatchHistoryResponseDTO>> getWatchHistory(@PathVariable Long userId) {
        List<WatchHistoryResponseDTO> history = watchService.getUserHistory(userId);
        return ResponseEntity.ok(history);
    }
}