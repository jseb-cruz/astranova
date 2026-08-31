package dev.team.astranova.core.entity;

import dev.team.astranova.core.constant.MissionState;

import java.time.LocalDate;
import java.util.UUID;

public record Mission(
        UUID missionId,
        UUID spaceshipId,
        UUID planetId,
        String name,
        LocalDate startDate,
        LocalDate endDate,
        String objective,
        MissionState state,
        String description,
        String usedRecurses,
        String result
) {
    public Mission(UUID missionId, UUID spaceshipId, UUID planetId, String name, LocalDate startDate, LocalDate endDate, String objective, MissionState state, String description, String usedRecurses, String result) {

        if (missionId == null) {
            throw new IllegalArgumentException("El id de la misión no puede ser nulo");
        }
        if (spaceshipId == null) {
            throw new IllegalArgumentException("El id de la nave espacial no puede ser nulo");
        }
        if (planetId == null) {
            throw new IllegalArgumentException("El id del planeta no puede ser nulo");
        }
        if (name == null) {
            throw new IllegalArgumentException("El nombre de la misión no puede ser nulo");
        }
        if (state == null) {
            throw new IllegalArgumentException("El estado de la misión no puede ser nulo");
        }
        if (state != MissionState.MSTATE_PLANNED) {  // Si la misión no está en estado planificado, se requiere que las fechas de inicio y finalización sean no nulas
            if (startDate == null) {
                throw new IllegalArgumentException("La fecha de inicio de la misión no puede ser nula");
            }
            if (endDate == null) {
                throw new IllegalArgumentException("La fecha de finalización de la misión no puede ser nula");
            }
        }
        if (objective == null) {
            throw new IllegalArgumentException("El objetivo de la misión no puede ser nulo");
        }
        if (description == null) {
            throw new IllegalArgumentException("La descripción de la misión no puede ser nula");
        }
        if (usedRecurses == null) {
            throw new IllegalArgumentException("Los recursos utilizados de la misión no pueden ser nulos");
        }
        if (result == null) {
            throw new IllegalArgumentException("El resultado de la misión no puede ser nulo");
        }

        this.missionId = missionId;
        this.spaceshipId = spaceshipId;
        this.planetId = planetId;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.objective = objective;
        this.state = state;
        this.description = description;
        this.usedRecurses = usedRecurses;
        this.result = result;
    }
}
