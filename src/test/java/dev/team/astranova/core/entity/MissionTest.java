package dev.team.astranova.core.entity;

import dev.team.astranova.core.constant.MissionState;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MissionTest {
    private final UUID mockId = UUID.randomUUID();
    private final UUID mockSpaceshipId = UUID.randomUUID();
    private final UUID mockPlanetId = UUID.randomUUID();
    private final MissionState mockMissionState = MissionState.MSTATE_PLANNED;

    @Test
    void ShouldCreateMissionWithValidData() {
        Mission mission = new Mission(
                mockId,
                mockSpaceshipId,
                mockPlanetId,
                "Test Mission",
                null,
                null,
                "Test Objective",
                mockMissionState,
                "Test Description",
                "Test Used Resources",
                "Test Result"
        );

        assertEquals(mockId, mission.missionId());
        assertEquals(mockSpaceshipId, mission.spaceshipId());
        assertEquals(mockPlanetId, mission.planetId());
        assertEquals("Test Mission", mission.name());
        assertEquals(null, mission.startDate());
        assertEquals(null, mission.endDate());
        assertEquals("Test Objective", mission.objective());
        assertEquals(mockMissionState, mission.state());
        assertEquals("Test Description", mission.description());
        assertEquals("Test Used Resources", mission.usedRecurses());
        assertEquals("Test Result", mission.result());
    }

    @Test
    void ShouldRejectNullMissionId() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Mission(
                null,
                mockSpaceshipId,
                mockPlanetId,
                "Test Mission",
                null,
                null,
                "Test Objective",
                mockMissionState,
                "Test Description",
                "Test Used Resources",
                "Test Result"
        ));
        assertEquals("El id de la misión no puede ser nulo", exception.getMessage());
    }

    @Test
    void ShouldRejectNullSpaceshipId() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Mission(
                mockId,
                null,
                mockPlanetId,
                "Test Mission",
                null,
                null,
                "Test Objective",
                mockMissionState,
                "Test Description",
                "Test Used Resources",
                "Test Result"
        ));
        assertEquals("El id de la nave espacial no puede ser nulo", exception.getMessage());
    }

    @Test
    void ShouldRejectNullPlanetId() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Mission(
                mockId,
                mockSpaceshipId,
                null,
                "Test Mission",
                null,
                null,
                "Test Objective",
                mockMissionState,
                "Test Description",
                "Test Used Resources",
                "Test Result"
        ));
        assertEquals("El id del planeta no puede ser nulo", exception.getMessage());
    }

    @Test
    void ShouldRejectNullName() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Mission(
                mockId,
                mockSpaceshipId,
                mockPlanetId,
                null,
                null,
                null,
                "Test Objective",
                mockMissionState,
                "Test Description",
                "Test Used Resources",
                "Test Result"
        ));
        assertEquals("El nombre de la misión no puede ser nulo", exception.getMessage());
    }

    @Test
    void ShouldAllowNullDatesWhenMissionIsPlanned() {
        Mission mission = new Mission(
                mockId,
                mockSpaceshipId,
                mockPlanetId,
                "Test Mission",
                null,
                null,
                "Test Objective",
                mockMissionState,
                "Test Description",
                "Test Used Resources",
                "Test Result"
        );

        assertEquals(null, mission.startDate());
        assertEquals(null, mission.endDate());
    }

    @Test
    void ShouldRejectNullStartDateWhenMissionIsInProgress() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Mission(
                mockId,
                mockSpaceshipId,
                mockPlanetId,
                "Test Mission",
                null,
                java.time.LocalDate.now().plusDays(1),
                "Test Objective",
                MissionState.MSTATE_INPROGRESS,
                "Test Description",
                "Test Used Resources",
                "Test Result"
        ));
        assertEquals("La fecha de inicio de la misión no puede ser nula", exception.getMessage());
    }

    @Test
    void ShouldRejectNullEndDateWhenMissionIsInProgress() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Mission(
                mockId,
                mockSpaceshipId,
                mockPlanetId,
                "Test Mission",
                java.time.LocalDate.now(),
                null,
                "Test Objective",
                MissionState.MSTATE_INPROGRESS,
                "Test Description",
                "Test Used Resources",
                "Test Result"
        ));
        assertEquals("La fecha de finalización de la misión no puede ser nula", exception.getMessage());
    }

    @Test
    void ShouldRejectNullObjective() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Mission(
                mockId,
                mockSpaceshipId,
                mockPlanetId,
                "Test Mission",
                null,
                null,
                null,
                mockMissionState,
                "Test Description",
                "Test Used Resources",
                "Test Result"
        ));
        assertEquals("El objetivo de la misión no puede ser nulo", exception.getMessage());
    }

    @Test
    void ShouldRejectNullState() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Mission(
                mockId,
                mockSpaceshipId,
                mockPlanetId,
                "Test Mission",
                null,
                null,
                "Test Objective",
                null,
                "Test Description",
                "Test Used Resources",
                "Test Result"
        ));
        assertEquals("El estado de la misión no puede ser nulo", exception.getMessage());
    }

    @Test
    void ShouldRejectNullDescription() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Mission(
                mockId,
                mockSpaceshipId,
                mockPlanetId,
                "Test Mission",
                null,
                null,
                "Test Objective",
                mockMissionState,
                null,
                "Test Used Resources",
                "Test Result"
        ));
        assertEquals("La descripción de la misión no puede ser nula", exception.getMessage());
    }

    @Test
    void ShouldRejectNullUsedResources() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Mission(
                mockId,
                mockSpaceshipId,
                mockPlanetId,
                "Test Mission",
                null,
                null,
                "Test Objective",
                mockMissionState,
                "Test Description",
                null,
                "Test Result"
        ));
        assertEquals("Los recursos utilizados de la misión no pueden ser nulos", exception.getMessage());
    }

    @Test
    void ShouldRejectNullResult() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Mission(
                mockId,
                mockSpaceshipId,
                mockPlanetId,
                "Test Mission",
                null,
                null,
                "Test Objective",
                mockMissionState,
                "Test Description",
                "Test Used Resources",
                null
        ));
        assertEquals("El resultado de la misión no puede ser nulo", exception.getMessage());
    }
}