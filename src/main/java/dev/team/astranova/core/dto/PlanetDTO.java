package dev.team.astranova.core.dto;

import dev.team.astranova.core.constant.EnvironmentalConditions;
import dev.team.astranova.core.constant.ExplorationState;

public record PlanetDTO(
        String name,
        double distance,
        String type,
        String available_resources,
        ExplorationState status,
        EnvironmentalConditions conditions
) {

}
