package dev.team.astranova.adapter.repository.inmemory;

import dev.team.astranova.core.dto.MissionDTO;
import dev.team.astranova.core.entity.Mission;
import dev.team.astranova.core.port.MissionRepository;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Simple in-memory MissionRepository for tests.
 */
public class InMemoryMissionRepository implements MissionRepository {

    private final ArrayList<Mission> List = new ArrayList<>();

    @Override
    public List<Mission> findAll() {
        return List;
    }

    @Override
    public Optional<Mission> findById(UUID MissionId) {
        for (Mission mission : List) {
            if (mission.missionId().equals(MissionId)) {
                return Optional.of(mission);
            }
        }
        return Optional.empty();
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

        List.add(mission);
        return mission;
    }

    @Override
    public Mission update(UUID missionId, MissionDTO missionRequest) {
        for (int i = 0; i < List.size(); i++) {
            Mission existing = List.get(i);
            if (existing.missionId().equals(missionId)) {
                Mission updated = new Mission(
                        missionId,
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

                List.set(i, updated);
                return updated;
            }
        }
        return null;
    }

    @Override
    public void delete(UUID id) {
        List.removeIf(m -> m.missionId().equals(id));
    }
}
