package dev.team.astranova.core.port;

import dev.team.astranova.core.dto.AstronautDTO;
import dev.team.astranova.core.entity.Astronaut;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AstronautRepository {
    List<Astronaut> findAll();

    Optional<Astronaut> findById(UUID astronautId);

    Astronaut create(UUID userId, AstronautDTO astronautRequest);

    Optional<Astronaut> update(UUID astronautId, AstronautDTO astronautRequest);

    void delete(UUID astronautId);

}
