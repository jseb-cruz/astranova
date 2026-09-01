package dev.team.astranova.core.entity;

import dev.team.astranova.core.constant.EnvironmentalConditions;
import dev.team.astranova.core.constant.ExplorationState;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PlanetTest {
    private final UUID mockId = UUID.randomUUID();
    private final ExplorationState mockExplorationState = ExplorationState.ESTATE_NOTEXPLORED;
    private final EnvironmentalConditions mockEnvironmentalConditions = EnvironmentalConditions.CONDITIONS_NORMAL;

    @Test
    void ShouldCreatePlanetWithValidData() {
        Planet planet = new Planet(
                mockId,
                "Test Planet",
                100.0,
                "Test Type",
                "Test Resources",
                mockExplorationState,
                mockEnvironmentalConditions
        );

        assertEquals(mockId, planet.planetId());
        assertEquals("Test Planet", planet.name());
        assertEquals(100.0, planet.distance());
        assertEquals("Test Type", planet.type());
        assertEquals("Test Resources", planet.available_resources());
        assertEquals(mockExplorationState, planet.status());
        assertEquals(mockEnvironmentalConditions, planet.conditions());
    }

    @Test
    void ShouldRejectNullPlanetId() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Planet(
                null,
                "Test Planet",
                100.0,
                "Test Type",
                "Test Resources",
                mockExplorationState,
                mockEnvironmentalConditions
        ));
        assertEquals("El id del planeta no puede ser nulo",
                exception.getMessage());
    }

    @Test
    void ShouldRejectNullName() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Planet(
                mockId,
                null,
                100.0,
                "Test Type",
                "Test Resources",
                mockExplorationState,
                mockEnvironmentalConditions
        ));
        assertEquals("El nombre del planeta no puede ser nulo o vacio",
                exception.getMessage());
    }

    @Test
    void ShouldRejectBlankName() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Planet(
                mockId,
                "   ",
                100.0,
                "Test Type",
                "Test Resources",
                mockExplorationState,
                mockEnvironmentalConditions
        ));
        assertEquals("El nombre del planeta no puede ser nulo o vacio",
                exception.getMessage());
    }

    @Test
    void ShouldRejectNegativeDistance() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Planet(
                mockId,
                "Test Planet",
                -100.0,
                "Test Type",
                "Test Resources",
                mockExplorationState,
                mockEnvironmentalConditions
        ));
        assertEquals("La distancia del planeta no puede ser negativa",
                exception.getMessage());
    }

    @Test
    void ShouldRejectNullType() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Planet(
                mockId,
                "Test Planet",
                100.0,
                null,
                "Test Resources",
                mockExplorationState,
                mockEnvironmentalConditions
        ));
        assertEquals("El tipo del planeta no puede ser nulo o vacio",
                exception.getMessage());
    }

    @Test
    void ShouldRejectNullResources() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Planet(
                mockId,
                "Test Planet",
                100.0,
                "Test Type",
                null,
                mockExplorationState,
                mockEnvironmentalConditions
        ));
        assertEquals("Los recursos del planeta no pueden ser nulos o vacios",
                exception.getMessage());
    }

    @Test
    void ShouldRejectBlankResources() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Planet(
                mockId,
                "Test Planet",
                100.0,
                "Test Type",
                "   ",
                mockExplorationState,
                mockEnvironmentalConditions
        ));
        assertEquals("Los recursos del planeta no pueden ser nulos o vacios",
                exception.getMessage());
    }


    @Test
    void ShouldRejectNullExplorationState() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Planet(
                mockId,
                "Test Planet",
                100.0,
                "Test Type",
                "Test Resources",
                null,
                mockEnvironmentalConditions
        ));
        assertEquals("El estado del planeta no puede ser nulo",
                exception.getMessage());
    }

    @Test
    void ShouldRejectNullEnvironmentalConditions() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Planet(
                mockId,
                "Test Planet",
                100.0,
                "Test Type",
                "Test Resources",
                mockExplorationState,
                null
        ));
        assertEquals("Las condiciones ambientales del planeta no pueden ser nulas",
                exception.getMessage());
    }
}



