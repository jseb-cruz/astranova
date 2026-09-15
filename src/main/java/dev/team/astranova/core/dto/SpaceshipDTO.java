package dev.team.astranova.core.dto;

import dev.team.astranova.core.constant.SpaceshipState;

import java.time.LocalDate;

public record SpaceshipDTO(
        String name,
        String model,
        int capacity,
        SpaceshipState status,
        LocalDate last_date_maintenance,
        int autonomy,
        LocalDate fabrication_date
) {
}
