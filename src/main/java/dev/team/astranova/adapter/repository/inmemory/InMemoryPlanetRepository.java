package dev.team.astranova.adapter.repository.inmemory;

import dev.team.astranova.core.dto.PlanetDTO;
import dev.team.astranova.core.entity.Planet;
import dev.team.astranova.core.port.PlanetRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class InMemoryPlanetRepository implements PlanetRepository {
    private final ArrayList<Planet> planets = new ArrayList<>();

    @Override
    public List<Planet> findAll() {
        return planets;
    }

    @Override
    public Optional<Planet> findById(UUID planetId) {
        return planets.stream()
                .filter(planet -> planet.planetId().equals(planetId))
                .findFirst();
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

        planets.add(planet);
        return planet;
    }

    @Override
    public Planet update(UUID planetId, PlanetDTO planetRequest) {
        for (Planet planet : planets) {
            if (planet.planetId().equals(planetId)) {
                Planet updatedPlanet = new Planet(
                        planetId,
                        planetRequest.name(),
                        planetRequest.distance(),
                        planetRequest.type(),
                        planetRequest.available_resources(),
                        planetRequest.status(),
                        planetRequest.conditions()
                );
                planets.set(planets.indexOf(planet), updatedPlanet);
                return updatedPlanet;
            }
        }
        return null;
    }

    @Override
    public void delete(UUID planetId) {
        planets.removeIf(planet -> planet.planetId().equals(planetId));
    }
}
