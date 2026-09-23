package dev.team.astranova.adapter.repository.mysql;

import dev.team.astranova.adapter.repository.mysql.entity.MissionJPA;
import dev.team.astranova.adapter.repository.mysql.repositories.SpringDataMissionRepository;
import dev.team.astranova.core.dto.MissionDTO;
import dev.team.astranova.core.entity.Mission;
import dev.team.astranova.core.port.MissionRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class MysqlMissionRepository implements MissionRepository {

    private final SpringDataMissionRepository springDataMissionRepository;

    public MysqlMissionRepository(SpringDataMissionRepository springDataMissionRepository) {
        this.springDataMissionRepository = springDataMissionRepository;
    }

    // Return all missions
    @Override
    public List<Mission> findAll() {
        return springDataMissionRepository.findAll()
                .stream()
                .map(MissionJPA::toDomain)
                .toList();
    }

    // Find a mission by id
    @Override
    public Optional<Mission> findById(UUID missionId) {
        return springDataMissionRepository.findById(missionId)
                .map(MissionJPA::toDomain);
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
        return springDataMissionRepository.save(MissionJPA.fromDomain(mission)).toDomain();
    }

    // Update existing mission; returns empty if not found
    @Override
    public Optional<Mission> update(UUID missionId, MissionDTO missionRequest) {
        return springDataMissionRepository.findById(missionId)
                .map(existing -> {
                    Mission updatedMission = new Mission(
                            existing.getMissionId(),
                            existing.getSpaceshipId(),
                            existing.getPlanetId(),
                            missionRequest.name(),
                            missionRequest.startDate(),
                            missionRequest.endDate(),
                            missionRequest.objective(),
                            missionRequest.state(),
                            missionRequest.description(),
                            missionRequest.usedRecurses(),
                            missionRequest.result()
                    );
                    return springDataMissionRepository.save(MissionJPA.fromDomain(updatedMission)).toDomain();
                });
    }

    // Delete mission by id
    @Override
    public void delete(UUID missionId) {
        springDataMissionRepository.deleteById(missionId);
    }
}