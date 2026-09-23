package dev.team.astranova.adapter.repository.inmemory;

import dev.team.astranova.core.dto.MissionDTO;
import dev.team.astranova.core.entity.Mission;
import dev.team.astranova.core.port.MissionRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * In-memory mission repository. Stores missions in an internal list for testing.
 */
public class InMemoryMissionRepository implements MissionRepository {

    // Internal storage list
    private final ArrayList<Mission> missions = new ArrayList<>();

    // Return all missions (internal list reference)
    @Override
    public List<Mission> findAll() {
        return missions;
    }

    // Find a mission by id
    @Override
    public Optional<Mission> findById(UUID missionId) {
        return missions.stream()
                .filter(mission -> mission.missionId().equals(missionId))
                .findFirst();
    }

    // Create a new mission linked to an existing spaceship and planet
    @Override
    public Mission create(UUID spaceshipId, UUID planetId, MissionDTO missionRequest) {
        Mission mission = new Mission(
                UUID.randomUUID(),
                spaceshipId,
                planetId,
                missionRequest.name(),
                missionRequest.startDate(),
                missionRequest.endDate(),
                missionRequest.objective(),
                missionRequest.state(),
                missionRequest.description(),
                missionRequest.usedRecurses(),
                missionRequest.result()
        );
        missions.add(mission);
        return mission;
    }

    // Update existing mission; returns empty if not found
    @Override
    public Optional<Mission> update(UUID missionId, MissionDTO missionRequest) {
        for (Mission mission : missions) {
            if (mission.missionId().equals(missionId)) {
                Mission updatedMission = new Mission(
                        mission.missionId(),
                        mission.spaceshipId(),
                        mission.planetId(),
                        missionRequest.name(),
                        missionRequest.startDate(),
                        missionRequest.endDate(),
                        missionRequest.objective(),
                        missionRequest.state(),
                        missionRequest.description(),
                        missionRequest.usedRecurses(),
                        missionRequest.result()
                );
                missions.set(missions.indexOf(mission), updatedMission);
                return Optional.of(updatedMission);
            }
        }
        return Optional.empty();
    }

    // Delete mission by id
    @Override
    public void delete(UUID missionId) {
        missions.removeIf(m -> m.missionId().equals(missionId));
    }
}