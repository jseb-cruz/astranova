package dev.team.astranova.core.port;

import dev.team.astranova.core.dto.PlanetDTO;
import dev.team.astranova.core.entity.Planet;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PlanetRepository {

    List<Planet> findAll();

    Optional<Planet> findById(UUID planetId);

    Planet create(PlanetDTO planetRequest);

    Planet update(UUID planetId, PlanetDTO planetRequest);

    void delete(UUID planetId);


}
