package br.com.streaming.domain.dto;

import br.com.streaming.domain.enums.PlanType;
import br.com.streaming.domain.enums.ProfileType;

public record UserResponseDTO(
    Long id,
    Integer age,
    ProfileType profileType,
    Boolean isSubscriber,
    PlanType planType
) {}