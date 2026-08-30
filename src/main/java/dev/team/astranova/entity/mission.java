package dev.team.astranova.entity;

import dev.team.astranova.constant.MissionState;

import java.time.LocalDate;
import java.util.UUID;

public record mission(
        UUID missionId,
        UUID spaceshipId,
        UUID planetId,
        String name,
        LocalDate startDate,
        LocalDate endDate,
        String objetive,
        MissionState state,
        String description,
        String usedRecurses,
        String result
) {
}
