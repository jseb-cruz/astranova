package dev.team.astranova.adapter.repository.mysql;

import dev.team.astranova.adapter.repository.mysql.entity.PlanetJPA;
import dev.team.astranova.adapter.repository.mysql.repositories.SpringDataPlanetRepository;
import dev.team.astranova.core.dto.PlanetDTO;
import dev.team.astranova.core.entity.Planet;
import dev.team.astranova.core.port.PlanetRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class MysqlPlanetRepository implements PlanetRepository {

    private final SpringDataPlanetRepository springDataPlanetRepository;

    public MysqlPlanetRepository(SpringDataPlanetRepository springDataPlanetRepository) {
        this.springDataPlanetRepository = springDataPlanetRepository;
    }

    // Return all planets
    @Override
    public List<Planet> findAll() {
        return springDataPlanetRepository.findAll()
                .stream()
                .map(PlanetJPA::toDomain)
                .toList();
    }

    // Find a planet by id
    @Override
    public Optional<Planet> findById(UUID planetId) {
        return springDataPlanetRepository.findById(planetId)
                .map(PlanetJPA::toDomain);
    }

    // Create a new planet from a DTO
    @Override
    public Planet create(PlanetDTO planetRequest) {
        Planet planet = new Planet(
                UUID.randomUUID(),
                planetRequest.name(),
                planetRequest.distance(),
                planetRequest.type(),
                planetRequest.available_resources(),
                planetRequest.status(),
                planetRequest.conditions()
        );
        return springDataPlanetRepository.save(PlanetJPA.fromDomain(planet)).toDomain();
    }

    // Update existing planet; returns empty if not found
    @Override
    public Optional<Planet> update(UUID planetId, PlanetDTO planetRequest) {
        return springDataPlanetRepository.findById(planetId)
                .map(existing -> {
                    Planet updatedPlanet = new Planet(
                            existing.getPlanetId(),
                            planetRequest.name(),
                            planetRequest.distance(),
                            planetRequest.type(),
                            planetRequest.available_resources(),
                            planetRequest.status(),
                            planetRequest.conditions()
                    );
                    return springDataPlanetRepository.save(PlanetJPA.fromDomain(updatedPlanet)).toDomain();
                });
    }

    // Delete planet by id
    @Override
    public void delete(UUID planetId) {
        springDataPlanetRepository.deleteById(planetId);
    }
}