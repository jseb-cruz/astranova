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
        return null;
    }

    @Override
    public Mission update(UUID missionId, MissionDTO missionRequest) {
        return null;
    }

    @Override
    public void delete(UUID id) {
        List.removeIf(m -> m.missionId().equals(id));
    }
}
