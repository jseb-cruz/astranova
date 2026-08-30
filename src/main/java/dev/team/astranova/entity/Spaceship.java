package dev.team.astranova.entity;

import dev.team.astranova.constant.SpaceshipStatus;

import java.time.LocalDate;
import java.util.UUID;

public record Spaceship(

        UUID spaceshipId,
        String name,
        String model,
        int capacity,
        SpaceshipStatus status,
        LocalDate last_date_maintenance,
        int autonomy,
        LocalDate fabrication_date



) {

    public Spaceship(UUID spaceshipId, String name, String model, int capacity, SpaceshipStatus status, LocalDate last_date_maintenance, int autonomy, LocalDate fabrication_date) {

        if (spaceshipId == null) {
            throw new IllegalArgumentException("El id de la nave espacial no puede ser nulo");
        }

        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("El nombre de la nave espacial no puede ser nulo o vacio");
        }

        if (model == null || model.isBlank()) {
            throw new IllegalArgumentException("El modelo de la nave espacial no puede ser nulo o vacio");
        }

        if (capacity <= 0) {
            throw new IllegalArgumentException("La capacidad de la nave espacial debe ser mayor a 0");
        }

        if (status == null) {
            throw new IllegalArgumentException("El estado de la nave espacial no puede ser nulo");
        }

        if (last_date_maintenance == null) {
            throw new IllegalArgumentException("La fecha del ultimo mantenimiento de la nave espacial no puede ser nula");
        }

        if (autonomy <= 0) {
            throw new IllegalArgumentException("La autonomia de la nave espacial debe ser mayor a 0");
        }

        if (fabrication_date == null) {
            throw new IllegalArgumentException("La fecha de fabricacion de la nave espacial no puede ser nula");
        }

        this.spaceshipId = spaceshipId;
        this.name = name;
        this.model = model;
        this.capacity = capacity;
        this.status = status;
        this.last_date_maintenance = last_date_maintenance;
        this.autonomy = autonomy;
        this.fabrication_date = fabrication_date;
    }

}