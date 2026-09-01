package dev.team.astranova.core.entity;

import dev.team.astranova.core.constant.AstronautState;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AstronautTest {
    private final UUID mockId = UUID.randomUUID();
    private final AstronautState mockAstronautState = AstronautState.ASTATE_ACTIVE;


    @Test
    void ShouldCreateAstronautWithValidData() {
        Astronaut astronaut = new Astronaut(
                mockId,
                "Test Nationality",
                "Test Range",
                "Test Specialty",
                "Test Experience",
                mockAstronautState
        );

        assertEquals(mockId, astronaut.userId());
        assertEquals("Test Nationality", astronaut.nationality());
        assertEquals("Test Range", astronaut.range());
        assertEquals("Test Specialty", astronaut.specialty());
        assertEquals("Test Experience", astronaut.experience());
        assertEquals(mockAstronautState, astronaut.status());
    }

    @Test
    void ShouldRejectNullUserId() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Astronaut(
                null,
                "Test Nationality",
                "Test Range",
                "Test Specialty",
                "Test Experience",
                mockAstronautState
        ));
        assertEquals("El id del astronauta no puede ser nulo",
                exception.getMessage());
    }

    @Test
    void ShouldRejectNullNationality() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Astronaut(
                mockId,
                null,
                "Test Range",
                "Test Specialty",
                "Test Experience",
                mockAstronautState
        ));
        assertEquals("La nacionalidad del astronauta no puede ser nula o vacia",
                exception.getMessage());
    }

    @Test
    void ShouldRejectBlankNationality() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Astronaut(
                mockId,
                "   ",
                "Test Range",
                "Test Specialty",
                "Test Experience",
                mockAstronautState
        ));
        assertEquals("La nacionalidad del astronauta no puede ser nula o vacia",
                exception.getMessage());
    }

    @Test
    void ShouldRejectNullRange() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Astronaut(
                mockId,
                "Test Nationality",
                null,
                "Test Specialty",
                "Test Experience",
                mockAstronautState
        ));
        assertEquals("El rango del astronauta no puede ser nulo o vacio",
                exception.getMessage());
    }

    @Test
    void ShouldRejectBlankRange() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Astronaut(
                mockId,
                "Test Nationality",
                "   ",
                "Test Specialty",
                "Test Experience",
                mockAstronautState
        ));
        assertEquals("El rango del astronauta no puede ser nulo o vacio",
                exception.getMessage());
    }

    @Test
    void ShouldRejectNullSpecialty() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Astronaut(
                mockId,
                "Test Nationality",
                "Test Range",
                null,
                "Test Experience",
                mockAstronautState
        ));
        assertEquals("La especialidad del astronauta no puede ser nula o vacia",
                exception.getMessage());
    }

    @Test
    void ShouldRejectBlankSpecialty() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Astronaut(
                mockId,
                "Test Nationality",
                "Test Range",
                "   ",
                "Test Experience",
                mockAstronautState
        ));
        assertEquals("La especialidad del astronauta no puede ser nula o vacia",
                exception.getMessage());
    }

    @Test
    void ShouldRejectNullExperience() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Astronaut(
                mockId,
                "Test Nationality",
                "Test Range",
                "Test Specialty",
                null,
                mockAstronautState
        ));
        assertEquals("La experiencia del astronauta no puede ser nula o vacia",
                exception.getMessage());
    }

    @Test
    void ShouldRejectBlankExperience() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Astronaut(
                mockId,
                "Test Nationality",
                "Test Range",
                "Test Specialty",
                "   ",
                mockAstronautState
        ));
        assertEquals("La experiencia del astronauta no puede ser nula o vacia",
                exception.getMessage());
    }

    @Test
    void ShouldRejectNullStatus() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Astronaut(
                mockId,
                "Test Nationality",
                "Test Range",
                "Test Specialty",
                "Test Experience",
                null
        ));
        assertEquals("El estado del astronauta no puede ser nulo",
                exception.getMessage());
    }
}