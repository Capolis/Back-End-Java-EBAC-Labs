package br.com.streaming.domain.dto;

import java.time.LocalDateTime;

public record WatchHistoryResponseDTO(
    Long historyId,
    String movieTitle,
    LocalDateTime watchedAt
) {}