package dev.team.astranova.core.dto;

import dev.team.astranova.core.constant.MissionState;

import java.time.LocalDate;
import java.util.UUID;


public record MissionDTO(
        String spaceshipName,
        String planetName,
        String name,
        LocalDate startDate,
        LocalDate endDate,
        String objective,
        MissionState state,
        String description,
        String usedRecurses,
        String result
) {
}
