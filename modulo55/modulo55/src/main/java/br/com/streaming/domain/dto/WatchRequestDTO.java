package br.com.streaming.domain.dto;

public record WatchRequestDTO(
    Long userId,
    Long movieId
) {}