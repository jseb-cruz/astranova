package dev.team.astranova.adapter.repository.inmemory;

import dev.team.astranova.core.dto.PlanetDTO;
import dev.team.astranova.core.entity.Planet;
import dev.team.astranova.core.port.PlanetRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class InMemoryPlanetRepository implements PlanetRepository {
    ArrayList<Planet> List = new ArrayList<>();
    @Override
    public List<Planet> findAll() {
        return List;
    }

    @Override
    public Optional<Planet> findById(UUID planetId) {
        for (Planet planet : List){
            if (planet.planetId().equals(planetId)) {
                return Optional.of(planet);
            }
        }
        return Optional.empty();
    }

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

        List.add(planet);
        return planet;
    }

    @Override
    public Planet update(UUID planetId, PlanetDTO planetRequest) {
        for (int i = 0; i < List.size(); i++) {
            Planet existing = List.get(i);
            if (existing.planetId().equals(planetId)) {
                Planet updatedPlanet = new Planet(
                        existing.planetId(),
                        planetRequest.name(),
                        planetRequest.distance(),
                        planetRequest.type(),
                        planetRequest.available_resources(),
                        planetRequest.status(),
                        planetRequest.conditions()
                );
                List.set(i, updatedPlanet);
                return updatedPlanet;
            }
        }
        return null;
        }

    @Override
    public void delete(UUID planetId) {
        List.removeIf(planet -> planet.planetId().equals(planetId));
    }
}
