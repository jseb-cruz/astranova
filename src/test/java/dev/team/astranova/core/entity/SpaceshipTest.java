package dev.team.astranova.core.entity;

import dev.team.astranova.core.constant.SpaceshipState;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SpaceshipTest {

    private final UUID mockId = UUID.randomUUID();
    private final SpaceshipState mockSpaceshipState = SpaceshipState.SSTATE_INACTIVE;

    @Test
    void ShouldCreateSpaceshipWithValidData() {
        Spaceship spaceship = new Spaceship(
                mockId,
                "Test Spaceship",
                "Test Model",
                10,
                mockSpaceshipState,
                null,
                100,
                null
        );

        assertEquals(mockId, spaceship.spaceshipId());
        assertEquals("Test Spaceship", spaceship.name());
        assertEquals("Test Model", spaceship.model());
        assertEquals(10, spaceship.capacity());
        assertEquals(mockSpaceshipState, spaceship.status());
        assertEquals(null, spaceship.last_date_maintenance());
        assertEquals(100, spaceship.autonomy());
        assertEquals(null, spaceship.fabrication_date());
    }

    @Test
    void ShouldCreateSpaceshipWithAllDates() {
        LocalDate maintenanceDate = LocalDate.now();
        LocalDate fabricationDate = LocalDate.now().minusYears(2);

        Spaceship spaceship = new Spaceship(
                mockId,
                "Test Spaceship",
                "Test Model",
                10,
                mockSpaceshipState,
                maintenanceDate,
                100,
                fabricationDate
        );

        assertEquals(maintenanceDate, spaceship.last_date_maintenance());
        assertEquals(fabricationDate, spaceship.fabrication_date());
    }

    @Test
    void ShouldRejectNullSpaceshipId() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Spaceship(
                null,
                "Test Spaceship",
                "Test Model",
                10,
                mockSpaceshipState,
                null,
                100,
                null
        ));
        assertEquals("El id de la nave espacial no puede ser nulo", exception.getMessage());
    }

    @Test
    void ShouldRejectNullName() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Spaceship(
                mockId,
                null,
                "Test Model",
                10,
                mockSpaceshipState,
                null,
                100,
                null
        ));
        assertEquals("El nombre de la nave espacial no puede ser nulo o vacio",
                exception.getMessage());
    }

    @Test
    void ShouldRejectBlankName() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Spaceship(
                mockId,
                "   ",
                "Test Model",
                10,
                mockSpaceshipState,
                null,
                100,
                null
        ));
        assertEquals("El nombre de la nave espacial no puede ser nulo o vacio",
                exception.getMessage());
    }

    @Test
    void ShouldRejectNullModel() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Spaceship(
                mockId,
                "Test Spaceship",
                null,
                10,
                mockSpaceshipState,
                null,
                100,
                null
        ));
        assertEquals("El modelo de la nave espacial no puede ser nulo o vacio",
                exception.getMessage());
    }

    @Test
    void ShouldRejectBlankModel() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Spaceship(
                mockId,
                "Test Spaceship",
                "   ",
                10,
                mockSpaceshipState,
                null,
                100,
                null
        ));
        assertEquals("El modelo de la nave espacial no puede ser nulo o vacio",
                exception.getMessage());
    }

    @Test
    void ShouldRejectNullCapacity() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Spaceship(
                mockId,
                "Test Spaceship",
                "Test Model",
                0,
                mockSpaceshipState,
                null,
                100,
                null
        ));
        assertEquals("La capacidad de la nave espacial debe ser mayor a 0",
                exception.getMessage());
    }

    @Test
    void ShouldRejectNullStatus() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Spaceship(
                mockId,
                "Test Spaceship",
                "Test Model",
                10,
                null,
                null,
                100,
                null
        ));
        assertEquals("El estado de la nave espacial no puede ser nulo",
                exception.getMessage());
    }
}