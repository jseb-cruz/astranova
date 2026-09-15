package dev.team.astranova.core.dto;

import dev.team.astranova.core.constant.AstronautState;

public record AstronautDTO(
        String nationality,
        String range,
        String specialty,
        String experience,
        AstronautState state
) {
}
