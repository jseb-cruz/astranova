package dev.team.astranova.core.service;

import dev.team.astranova.core.dto.PlanetDTO;
import dev.team.astranova.core.entity.Planet;
import dev.team.astranova.core.port.PlanetRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class PlanetService {
    private final PlanetRepository planetRepository;

    public PlanetService(PlanetRepository planetRepository) {
        this.planetRepository = planetRepository;
    }

    public List<Planet> findAll() {
        return planetRepository.findAll();
    }

    public Optional<Planet> findById(UUID planetId) {
        return planetRepository.findById(planetId);
    }

    public Planet create(PlanetDTO planetRequest) {
        return planetRepository.create(planetRequest);
    }

    public Optional<Planet> update(UUID planetId, PlanetDTO planetRequest) {
        return planetRepository.update(planetId, planetRequest);
    }

    public void delete(UUID planetId) {
        planetRepository.delete(planetId);
    }
}

