package dev.team.astranova.entity;

import dev.team.astranova.constant.EnvironmentalConditions;
import dev.team.astranova.constant.ExplorationState;

import java.util.UUID;

public record Planet(

        UUID planetId,
        String name,
        double distance,
        String type,
        String available_resources,
        ExplorationState status,
        EnvironmentalConditions conditions


) {

    public Planet(UUID planetId, String name, double distance, String type, String available_resources, ExplorationState status, EnvironmentalConditions conditions) {

        if (planetId == null) {
            throw new IllegalArgumentException("El id del planeta no puede ser nulo");
        }

        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("El nombre del planeta no puede ser nulo o vacio");
        }

        if (distance < 0) {
            throw new IllegalArgumentException("La distancia del planeta no puede ser negativa");
        }

        if (type == null || type.isBlank()) {
            throw new IllegalArgumentException("El tipo del planeta no puede ser nulo o vacio");
        }

        if (available_resources == null || available_resources.isBlank()) {
            throw new IllegalArgumentException("Los recursos disponibles del planeta no pueden ser nulos o vacios");
        }


        if (status == null) {
            throw new IllegalArgumentException("El estado del planeta no puede ser nulo");
        }

        if (conditions == null) {
            throw new IllegalArgumentException("Las condiciones ambientales del planeta no pueden ser nulas");
        }

        this.planetId = planetId;
        this.name = name;
        this.distance = distance;
        this.type = type;
        this.available_resources = available_resources;
        this.status = status;
        this.conditions = conditions;
    }
}
