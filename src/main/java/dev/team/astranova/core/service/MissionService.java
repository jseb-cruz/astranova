package dev.team.astranova.core.service;

import dev.team.astranova.core.dto.MissionDTO;
import dev.team.astranova.core.entity.Mission;
import dev.team.astranova.core.port.MissionRepository;
import dev.team.astranova.core.port.PlanetRepository;
import dev.team.astranova.core.port.SpaceshipRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class MissionService {
    private final MissionRepository missionRepository;
    private final SpaceshipRepository spaceshipRepository;
    private final PlanetRepository planetRepository;

    public MissionService(
            MissionRepository missionRepository,
            SpaceshipRepository spaceshipRepository,
            PlanetRepository planetRepository
    ) {
        this.missionRepository = missionRepository;
        this.spaceshipRepository = spaceshipRepository;
        this.planetRepository = planetRepository;
    }

    public List<Mission> findAll() {
        return missionRepository.findAll();
    }

    public Optional<Mission> findById(UUID missionId) {
        if (missionId == null) {
            throw new IllegalArgumentException("El id de la misión no puede ser nulo");
        }
        return missionRepository.findById(missionId);
    }

    public Mission create(UUID spaceshipId, UUID planetId, MissionDTO missionRequest) {
        if (spaceshipId == null) {
            throw new IllegalArgumentException("El id de la nave no puede ser nulo");
        }
        if (planetId == null) {
            throw new IllegalArgumentException("El id del planeta no puede ser nulo");
        }
        if (missionRequest == null) {
            throw new IllegalArgumentException("La solicitud de misión no puede ser nula");
        }
        if (spaceshipRepository.findById(spaceshipId).isEmpty()) {
            throw new IllegalArgumentException("No existe la nave asociada a la misión");
        }
        if (planetRepository.findById(planetId).isEmpty()) {
            throw new IllegalArgumentException("No existe el planeta asociado a la misión");
        }
        return missionRepository.create(spaceshipId, planetId, missionRequest);
    }

    public Optional<Mission> update(UUID missionId, MissionDTO missionRequest) {
        if (missionId == null) {
            throw new IllegalArgumentException("El id de la misión no puede ser nulo");
        }
        if (missionRequest == null) {
            throw new IllegalArgumentException("La solicitud de misión no puede ser nula");
        }
        if (missionRepository.findById(missionId).isEmpty()) {
            throw new IllegalArgumentException("No existe la misión a actualizar");
        }
        return missionRepository.update(missionId, missionRequest);
    }

    public void delete(UUID missionId) {
        if (missionId == null) {
            throw new IllegalArgumentException("El id de la misión no puede ser nulo");
        }
        if (missionRepository.findById(missionId).isEmpty()) {
            throw new IllegalArgumentException("No existe la misión a eliminar");
        }
        missionRepository.delete(missionId);
    }
}
