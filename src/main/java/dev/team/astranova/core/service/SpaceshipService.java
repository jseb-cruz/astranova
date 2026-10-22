package dev.team.astranova.core.service;

import dev.team.astranova.core.dto.SpaceshipDTO;
import dev.team.astranova.core.entity.Spaceship;
import dev.team.astranova.core.port.SpaceshipRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class SpaceshipService {
    private final SpaceshipRepository spaceshipRepository;

    public SpaceshipService(SpaceshipRepository spaceshipRepository) {
        this.spaceshipRepository = spaceshipRepository;
    }

    public List<Spaceship> findAll() {
        return spaceshipRepository.findAll();
    }

    public Optional<Spaceship> findById(UUID spaceshipId) {
        return spaceshipRepository.findById(spaceshipId);
    }

    public Spaceship create(SpaceshipDTO spaceshipRequest) {
        return spaceshipRepository.create(spaceshipRequest);
    }

    public Optional<Spaceship> update(UUID spaceshipId, SpaceshipDTO spaceshipRequest) {
        return spaceshipRepository.update(spaceshipId, spaceshipRequest);
    }

    public void delete(UUID spaceshipId) {
        spaceshipRepository.delete(spaceshipId);
    }
}
