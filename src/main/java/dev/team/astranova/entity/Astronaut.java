package dev.team.astranova.entity;

import dev.team.astranova.constant.AstronautState;
import java.util.UUID;

public record Astronaut(

        UUID  userId,
        String nationality,
        String range,
        String specialty,
        String experience,
        AstronautState status
) {


    public Astronaut(UUID userId, String nationality, String range, String specialty, String experience, AstronautState status) {

        if (userId == null) {
            throw new IllegalArgumentException("El id del astronauta no puede ser nulo");
        }

        if (nationality == null || nationality.isBlank()) {
            throw new IllegalArgumentException("La nacionalidad del astronauta no puede ser nula o vacia");
        }

        if (range == null || range.isBlank()) {
            throw new IllegalArgumentException("El rango del astronauta no puede ser nulo o vacio");
        }

        if (specialty == null || specialty.isBlank()) {
            throw new IllegalArgumentException("La especialidad del astronauta no puede ser nula o vacia");
        }

        if (experience == null || experience.isBlank()) {
            throw new IllegalArgumentException("La experiencia del astronauta no puede ser nula o vacia");
        }

        if (status == null) {
            throw new IllegalArgumentException("El estado del astronauta no puede ser nulo");
        }


        this.userId = userId;
        this.nationality = nationality;
        this.range = range;
        this.specialty = specialty;
        this.experience = experience;
        this.status = status;

    }
}