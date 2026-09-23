package dev.team.astranova.adapter.repository.inmemory;

import dev.team.astranova.core.dto.MissionDTO;
import dev.team.astranova.core.entity.Mission;
import dev.team.astranova.core.port.MissionRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Simple in-memory MissionRepository for tests.
 */
public class InMemoryMissionRepository implements MissionRepository {

    private final ArrayList<Mission> missions = new ArrayList<>();

    @Override
    public List<Mission> findAll() {
        return missions;
    }

    @Override
    public Optional<Mission> findById(UUID missionId) {
        return missions.stream()
                .filter(m -> m.missionId().equals(missionId))
                .findFirst();
    }

    @Override
    public Mission create(MissionDTO missionRequest) {

        Mission mission = new Mission(
                UUID.randomUUID(),
                UUID.randomUUID(),
                UUID.randomUUID(),
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

    @Override
    public Mission update(UUID missionId, MissionDTO missionRequest) {
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
                return updatedMission;
            }
        }
        return null;
        }

    @Override
    public void delete(UUID missionId) {
        missions.removeIf(m -> m.missionId().equals(missionId));
    }
}
