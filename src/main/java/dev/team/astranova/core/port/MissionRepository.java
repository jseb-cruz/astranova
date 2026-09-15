package dev.team.astranova.core.port;

import dev.team.astranova.core.dto.MissionDTO;
import dev.team.astranova.core.entity.Mission;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Port interface defining mission persistence operations.
 */
public interface MissionRepository {

    List<Mission> findAll();
    Optional<Mission> findById(UUID missionId);
    Mission create(MissionDTO missionRequest);
    Mission update(UUID missionId, MissionDTO missionRequest);
    void delete(UUID missionId);
}
