package br.com.streaming.domain.dto;

import br.com.streaming.domain.enums.PlanType;

public record PlanMigrationDTO(
    PlanType newPlan
) {}