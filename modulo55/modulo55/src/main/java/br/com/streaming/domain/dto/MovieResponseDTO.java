package br.com.streaming.domain.dto;

import java.util.Set;

import br.com.streaming.domain.enums.PlanType;

public record MovieResponseDTO(
    Long id,
    String title,
    Integer ageRating,
    PlanType minimumPlan,
    Set<String> genres
) {}