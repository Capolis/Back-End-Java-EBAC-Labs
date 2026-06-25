package br.com.streaming.domain.dto;

import br.com.streaming.domain.enums.PlanType;

public record UserCreateDTO(
    Integer age,
    String password,
    PlanType planType,
    Boolean allowsAdultContent
) {}